package org.catncode.leaders_backend.navigation.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Root {
    private ArrayList<Hit> hits;
    private String locale;
}
