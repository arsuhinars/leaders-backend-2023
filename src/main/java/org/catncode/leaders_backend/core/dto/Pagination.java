package org.catncode.leaders_backend.core.dto;

import java.util.List;

public class Pagination<T> {
    private Integer totalItemsCount;
    private List<T> items;
}
