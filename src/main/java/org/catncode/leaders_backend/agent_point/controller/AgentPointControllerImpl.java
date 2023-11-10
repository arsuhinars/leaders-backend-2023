package org.catncode.leaders_backend.agent_point.controller;

import org.catncode.leaders_backend.agent_point.dto.AgentPointDto;
import org.catncode.leaders_backend.agent_point.dto.CreateAgentPointDto;
import org.catncode.leaders_backend.agent_point.dto.UpdateAgentPointDto;
import org.catncode.leaders_backend.agent_point.service.AgentPointService;
import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.core.factory.PaginationFactory;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentPointControllerImpl implements AgentPointController{

    private final AgentPointService agentPointService;
    private final ModelMapper modelMapper;
    private final PaginationFactory paginationFactory;

    public AgentPointControllerImpl(
            AgentPointService agentPointService,
            ModelMapper modelMapper,
            PaginationFactory paginationFactory
    ) {
        this.agentPointService = agentPointService;
        this.modelMapper = modelMapper;
        this.paginationFactory = paginationFactory;
    }

    @Override
    public AgentPointDto createAgentPoint(CreateAgentPointDto schema) {
        return modelMapper.map(agentPointService.create(schema), AgentPointDto.class);
    }

    @Override
    public Pagination<AgentPointDto> getAllAgentPoints(Integer page, Integer size) {
        return paginationFactory.createPagination(
                agentPointService.getAll(PageRequest.of(page, size)), AgentPointDto.class
        );
    }

    @Override
    public AgentPointDto getAgentPointById(Integer id) throws AppException {
        return modelMapper.map(agentPointService.getById(id), AgentPointDto.class);
    }

    @Override
    public AgentPointDto updateAgentPointById(UpdateAgentPointDto schema, Integer id) throws AppException {
        return modelMapper.map(agentPointService.updateById(id, schema), AgentPointDto.class);
    }

    @Override
    public void deleteAgentPointById(Integer id) throws AppException {
        agentPointService.deleteById(id);
    }
}
