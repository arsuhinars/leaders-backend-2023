package org.catncode.leaders_backend.navigation.service;

import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.navigation.dto.Point;

public interface GeocodeService {
    Point findPointByAddress(String address) throws AppException;
}
