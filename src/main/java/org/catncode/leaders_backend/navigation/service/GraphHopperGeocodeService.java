package org.catncode.leaders_backend.navigation.service;

import org.catncode.leaders_backend.core.exception.ApiException;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.navigation.dto.GeocodeResponse;
import org.catncode.leaders_backend.navigation.entity.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GraphHopperGeocodeService implements GeocodeService {
    private final WebClient graphHopperClient;

    @Value("${leaders_backend.graph_hopper_token}")
    private String graphHopperToken;

    public GraphHopperGeocodeService(WebClient graphHopperClient) {
        this.graphHopperClient = graphHopperClient;
    }

    @Override
    public Location createLocationByAddress(String address) throws AppException {
        var response = graphHopperClient.get()
                .uri(uriBuilder -> {
                uriBuilder.pathSegment("geocode");
                uriBuilder.queryParam("q", address);
                uriBuilder.queryParam("locale", "ru");
                uriBuilder.queryParam("limit", 1);
                uriBuilder.queryParam("key", graphHopperToken);
                return uriBuilder.build();
        })
                .retrieve()
                .bodyToMono(GeocodeResponse.class)
                .doOnError(error -> Mono.error(ApiException::new))
                .block();

        if (response == null || response.getHits().isEmpty()) {
            throw new ApiException();
        }

        var point = response.getHits().get(0).getPoint();

        return new Location(point.getLat(), point.getLng());
    }
}
