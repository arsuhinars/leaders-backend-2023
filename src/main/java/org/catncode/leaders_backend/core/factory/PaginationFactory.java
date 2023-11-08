package org.catncode.leaders_backend.core.factory;

import org.catncode.leaders_backend.core.dto.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PaginationFactory {
    public <T> Pagination<T> createPagination(Page<T> page) {
        return new Pagination<>((int)page.getTotalElements(), page.toList());
    }
}
