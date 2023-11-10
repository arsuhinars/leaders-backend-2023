package org.catncode.leaders_backend.navigation.dto;

import lombok.*;

import java.util.List;

@Data
public class GeocodeResponse {
    private List<GeocodeHit> hits;
    private Integer took;
}
