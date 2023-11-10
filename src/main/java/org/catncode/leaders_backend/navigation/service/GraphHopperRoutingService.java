package org.catncode.leaders_backend.navigation.service;

import com.graphhopper.GHRequest;
import com.graphhopper.GraphHopper;
import org.catncode.leaders_backend.core.exception.ApiException;
import org.catncode.leaders_backend.navigation.config.GraphHopperConfig;
import org.catncode.leaders_backend.navigation.dto.PathDistance;
import org.catncode.leaders_backend.navigation.entity.Location;
import org.springframework.stereotype.Service;

@Service
public class GraphHopperRoutingService implements RoutingService {
    private final GraphHopper graphHopper;

    public GraphHopperRoutingService(GraphHopper graphHopper) {
        this.graphHopper = graphHopper;
    }

    @Override
    public PathDistance findPath(Location start, Location end) {
        var request = new GHRequest(
                start.getLatitude(), start.getLongitude(),
                end.getLatitude(), end.getLongitude()
        );
        request.setProfile(GraphHopperConfig.DEFAULT_PROFILE_NAME);
        request.setLocale("ru");

        var response = graphHopper.route(request);
        if (response.hasErrors()) {
            var error = response.getErrors().toString();
            System.out.println("GraphHopper routing error: " + error);
            throw new ApiException(error);
        }

        var path = response.getBest();

        return new PathDistance(path.getDistance(), path.getTime() / 1000.0 / 3600.0);
    }
}
