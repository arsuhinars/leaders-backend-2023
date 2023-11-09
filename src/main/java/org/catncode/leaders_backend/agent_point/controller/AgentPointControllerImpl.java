package org.catncode.leaders_backend.agent_point.controller;

import org.catncode.leaders_backend.agent_point.dto.AgentPointDto;
import org.catncode.leaders_backend.agent_point.dto.CreateAgentPointDto;
import org.catncode.leaders_backend.agent_point.dto.UpdateAgentPointDto;
import org.catncode.leaders_backend.agent_point.entity.AgentPoint;
import org.catncode.leaders_backend.agent_point.service.AgentPointServiceImpl;
import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;

public class AgentPointControllerImpl implements AgentPointController{

    private final AgentPointServiceImpl agentPointServiceImpl;

    public AgentPointControllerImpl(AgentPointServiceImpl agentPointServiceImpl) {
        this.agentPointServiceImpl = agentPointServiceImpl;
    }

    @Override
    public AgentPoint createAgentPoint(CreateAgentPointDto schema) {
        return agentPointServiceImpl.create(schema);
    }

    @Override
    public Pagination<AgentPointDto> getAllAgentPoints(Integer page, Integer size) {
        return null;
    }

    @Override
    public AgentPoint getAgentPointById(Integer id) throws AppException {
        return agentPointServiceImpl.getById(id);
    }

    @Override
    public AgentPoint updateAgentPointById(UpdateAgentPointDto schema, Integer id) throws AppException {
        return agentPointServiceImpl.updateById(id, schema);
    }

    @Override
    public void deleteAgentPointById(Integer id) throws AppException {
        agentPointServiceImpl.deleteById(id);
    }
}
