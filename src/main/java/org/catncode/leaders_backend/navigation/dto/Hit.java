package org.catncode.leaders_backend.navigation.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Hit {
    private Point point;
    private ArrayList<Double> extent;
    private String name;
    private String country;
    private String countrycode;
    private String city;
    private String state;
    private String street;
    private String postcode;
    private long osm_id;
    private String osm_type;
    private String housenumber;
    private String osm_key;
    private String osm_value;
    private String house_number;
}
