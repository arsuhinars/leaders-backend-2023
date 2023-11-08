package org.catncode.leaders_backend.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagination<T> {
    private Integer totalItemsCount;
    private List<T> items;
}
