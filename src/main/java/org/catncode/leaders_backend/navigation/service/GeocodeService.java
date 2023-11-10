package org.catncode.leaders_backend.navigation.service;

import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.navigation.entity.Location;

public interface GeocodeService {
    Location createLocationByAddress(String address) throws AppException;
}
