package org.catncode.leaders_backend.agent_point.service;

import org.catncode.leaders_backend.agent_point.dto.CreateAgentPointDto;
import org.catncode.leaders_backend.agent_point.entity.AgentPoint;
import org.catncode.leaders_backend.core.exception.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AgentPointService {
    AgentPoint create(CreateAgentPointDto dto);

    Page<AgentPoint> getAll(Pageable pageable);

    AgentPoint getById(Integer id) throws AppException;

    AgentPoint updateById(Integer id) throws AppException;

    void deleteById(Integer id) throws AppException;
}
