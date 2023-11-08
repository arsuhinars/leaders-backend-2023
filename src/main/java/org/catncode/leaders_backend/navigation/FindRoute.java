package org.catncode.leaders_backend.navigation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.util.shapes.GHPoint;
import org.catncode.leaders_backend.navigation.dto.Point;
import org.catncode.leaders_backend.navigation.dto.Root;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class FindRoute {
    private static final String API_URL = "https://graphhopper.com/api/1/geocode";
    private static final String API_KEY = "03f13d82-1328-449a-b6ec-7d547ee548c0";
    private static final String PBF_FILE = "src/main/resources/south-fed-district-latest.osm.pbf";

    public static GraphHopper createGraphHopperInstance() {
        GraphHopper hopper = new GraphHopper();
        hopper.setOSMFile(PBF_FILE);
        // specify where to store graphhopper files
        hopper.setGraphHopperLocation("target/routing-graph-cache");

        // see docs/core/profiles.md to learn more about profiles
        hopper.setProfiles(new Profile("car").setVehicle("car").setTurnCosts(false));

        // this enables speed mode for the profile we called car
        hopper.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));

        // now this can take minutes if it imports or a few seconds for loading of course this is dependent on the area you import
        hopper.importOrLoad();
        return hopper;
    }

    public String addQuery(String uri, String q1, String q2){
        if(uri.contains("?")){
            return uri + "&" + q1 + "=" + q2;
        }else{
            return uri + "?" + q1 + "=" + q2;
        }
    }

    public String setAddress(String uri, String address){
        uri = addQuery(uri, "q", address);
        return addQuery(uri, "key", API_KEY);
    }

    public String toUri(String address){
        return address.replace(" ","%20");
    }

    public Point getPoint(GraphHopper hopper, String address) {
        ObjectMapper mapper = new ObjectMapper();
        FindRoute findRoute = new FindRoute();
        String urli;
        address = findRoute.toUri(address);
        urli = findRoute.setAddress(API_URL, address);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;

        request = HttpRequest.newBuilder()
                    .uri(URI.create(urli))
                    .build();

        HttpResponse response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body().toString();
        Root root;
        try {
            root = mapper.readValue(json, Root.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return root.getHits().get(0).getPoint();
    }

    // points, distance in meters and time in millis of the full path
    // PointList pointList = path.getPoints();
    // double distance = path.getDistance();
    // long timeInMs = path.getTime();
    public ResponsePath aToB(GraphHopper hopper, String address1, String address2){
        // simple configuration of the request object
        FindRoute findRoute =  new FindRoute();
        Point point1 = findRoute.getPoint(hopper, address1);
        Point point2 = findRoute.getPoint(hopper, address2);

        GHRequest req = new GHRequest(point1.getLat(), point1.getLng(), point2.getLat(), point2.getLng()).
                // note that we have to specify which profile we are using even when there is only one like here
                        setProfile("car");
        GHResponse rsp = hopper.route(req);

        // handle errors
        if (rsp.hasErrors())
            throw new RuntimeException(rsp.getErrors().toString());

        // use the best path, see the GHResponse class for more possibilities.
        ResponsePath responsePath = rsp.getBest();

        return responsePath;
    }

    // points, distance in meters and time in millis of the full path
    // PointList pointList = path.getPoints();
    // double distance = path.getDistance();
    // long timeInMs = path.getTime();
    public ResponsePath routeFewPoints(GraphHopper hopper, List<String> addresses){
        List<Point> points = new ArrayList<>();
        FindRoute findRoute = new FindRoute();

        for(String address: addresses){
            points.add(findRoute.getPoint(hopper, address));
        }

        GHRequest req = new GHRequest().
                        setProfile("car");
        for(Point point: points){
            req.addPoint(new GHPoint(point.getLat(), point.getLng()));
        }

        GHResponse rsp = hopper.route(req);

        // handle errors
        if (rsp.hasErrors())
            throw new RuntimeException(rsp.getErrors().toString());

        // use the best path, see the GHResponse class for more possibilities.
        ResponsePath responsePath = rsp.getBest();

        return responsePath;
    }
}
