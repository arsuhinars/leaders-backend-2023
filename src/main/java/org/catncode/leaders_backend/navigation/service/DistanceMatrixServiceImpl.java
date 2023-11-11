package org.catncode.leaders_backend.navigation.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.catncode.leaders_backend.navigation.dto.PathDistance;
import org.catncode.leaders_backend.navigation.entity.Location;
import org.catncode.leaders_backend.navigation.exception.PathNotFoundException;
import org.catncode.leaders_backend.navigation.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class DistanceMatrixServiceImpl implements DistanceMatrixService {
    @Data
    @AllArgsConstructor
    private static class LocationPair {
        private Location a;
        private Location b;
    }

    private final RoutingService routingService;
    private final LocationRepository locationRepository;

    private Map<LocationPair, PathDistance> distanceMatrix = null;

    public DistanceMatrixServiceImpl(RoutingService routingService, LocationRepository locationRepository) {
        this.routingService = routingService;
        this.locationRepository = locationRepository;
    }

    @Override
    public PathDistance getDistanceBetween(Location a, Location b) {
        if (distanceMatrix == null) {
            recalculateMatrix();
        }

        var distance = distanceMatrix.get(new LocationPair(a, b));
        if (distance == null) {
            System.out.println("distance not found " + a + " " + b);
            throw new PathNotFoundException();
        }

        return distance;
    }

    @Override
    public void recalculateMatrix() {
        var locations = ImmutableList.copyOf(locationRepository.findAll());

        distanceMatrix = new HashMap<>();
        for (int i = 0; i < locations.size(); ++i) {
            for (int j = 0; j < locations.size(); ++j) {
                var pair = new LocationPair(locations.get(i), locations.get(j));
                var path = routingService.findPath(pair.getA(), pair.getB());

                distanceMatrix.put(pair, path);
            }
        }
    }

    @Override
    public void recalculateFor(Location location) {
        if (distanceMatrix == null) {
            recalculateMatrix();
            return;
        }

        var locations = ImmutableList.copyOf(locationRepository.findAll());

        for (int i = 0; i < locations.size(); ++i) {
            var pair = new LocationPair(location, locations.get(i));
            var inversePair = new LocationPair(locations.get(i), location);

            var path = routingService.findPath(pair.getA(), pair.getB());
            var inversePath = routingService.findPath(pair.getB(), pair.getA());

            distanceMatrix.put(pair, path);
            distanceMatrix.put(inversePair, inversePath);
        }
    }
}
