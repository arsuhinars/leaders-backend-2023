package org.catncode.leaders_backend.unit.Navigation;

import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import org.catncode.leaders_backend.navigation.FindRoute;
import org.catncode.leaders_backend.navigation.dto.Point;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.catncode.leaders_backend.navigation.FindRoute.createGraphHopperInstance;

public class FindRouteTest {

    protected String address1 = "Краснодар, Красная, д. 139";
    protected String address2 = "Краснодар, Красных Партизан, 321";
    protected String address3 = "Краснодар, В.Н. Мачуги, 41";

    protected GraphHopper hopper;

    @BeforeEach
    public void createHopper(){
        hopper = createGraphHopperInstance();
    }

    @Test
    public void aToBTest(){
        List<String> addresses = new ArrayList<>();
        FindRoute findRoute = new FindRoute();

        ResponsePath path1 = findRoute.aToB(hopper, address1, address2);
    }

    @Test
    public void routeFewPointsTest(){
        List<String> addresses = new ArrayList<>();
        FindRoute findRoute = new FindRoute();

        addresses.add(address1);
        addresses.add(address2);
        addresses.add(address3);

        ResponsePath path2 = findRoute.routeFewPoints(hopper, addresses);
    }

    @Test
    public void getPointTest(){
        FindRoute findRoute = new FindRoute();
        Point point = findRoute.getPoint(hopper, address2);
    }

    @AfterEach
    public void closeHopper(){
        // release resources to properly shutdown or start a new instance
        hopper.close();
    }
}
