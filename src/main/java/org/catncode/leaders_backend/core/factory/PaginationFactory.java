package org.catncode.leaders_backend.core.factory;

import org.catncode.leaders_backend.core.dto.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PaginationFactory {
    private final ModelMapper modelMapper;

    public PaginationFactory(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T, V> Pagination<V> createPagination(Page<T> page, Class<V> dtoType) {
        var items = page.stream().map(item -> modelMapper.map(item, dtoType)).toList();

        return new Pagination<>((int)page.getTotalElements(), items);
    }
}
