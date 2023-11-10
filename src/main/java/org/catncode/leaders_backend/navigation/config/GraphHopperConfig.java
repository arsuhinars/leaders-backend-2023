package org.catncode.leaders_backend.navigation.config;

import com.graphhopper.GraphHopper;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphHopperConfig {
    public static final String DEFAULT_PROFILE_NAME = "car";

    @Value("${leaders_backend.osm_file_path}")
    private String osmFilePath;

    @Value("${leaders_backend.graph_hopper_location}")
    private String graphHopperLocation;

    @Value("${leaders_backend.graph_hopper_url}")
    private String graphHopperUrl;

    @Bean
    public GraphHopper graphHopper() {
        var hopper = new GraphHopper();
        hopper.setOSMFile(osmFilePath);
        hopper.setGraphHopperLocation(graphHopperLocation);
        hopper.setProfiles(new Profile(DEFAULT_PROFILE_NAME).setVehicle("car").setTurnCosts(true));
        hopper.getCHPreparationHandler().setCHProfiles(new CHProfile(DEFAULT_PROFILE_NAME));
        hopper.importOrLoad();

        return hopper;
    }

    @Bean("graphHopperClient")
    public WebClient graphHopperClient() {
        return WebClient.builder().baseUrl(graphHopperUrl).build();
    }
}
