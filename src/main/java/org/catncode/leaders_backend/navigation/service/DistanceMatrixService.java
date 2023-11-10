package org.catncode.leaders_backend.navigation.service;

import org.catncode.leaders_backend.navigation.dto.PathDistance;
import org.catncode.leaders_backend.navigation.entity.Location;

public interface DistanceMatrixService {
    PathDistance getDistanceBetween(Location a, Location b);

    void recalculateMatrix();

    void recalculateFor(Location location);
}
