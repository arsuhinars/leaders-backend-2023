package org.catncode.leaders_backend.navigation.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class PathDistance {
    private Double meters;
    private Double hours;
}
