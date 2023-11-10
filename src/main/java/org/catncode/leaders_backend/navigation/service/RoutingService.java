package org.catncode.leaders_backend.navigation.service;

import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.navigation.dto.PathDistance;
import org.catncode.leaders_backend.navigation.entity.Location;

public interface RoutingService {
    PathDistance findPath(Location start, Location end) throws AppException;
}
