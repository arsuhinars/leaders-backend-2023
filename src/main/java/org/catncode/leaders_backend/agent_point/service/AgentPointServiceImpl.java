package org.catncode.leaders_backend.agent_point.service;

import org.catncode.leaders_backend.agent_point.dto.CreateAgentPointDto;
import org.catncode.leaders_backend.agent_point.dto.UpdateAgentPointDto;
import org.catncode.leaders_backend.agent_point.entity.AgentPoint;
import org.catncode.leaders_backend.agent_point.exception.AgentPointNotFoundException;
import org.catncode.leaders_backend.agent_point.repository.AgentPointRepository;
import org.catncode.leaders_backend.core.exception.AppException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgentPointServiceImpl implements AgentPointService{
    private final AgentPointRepository agentPointRepository;
    private final ModelMapper modelMapper;

    public AgentPointServiceImpl(AgentPointRepository agentPointRepository, ModelMapper modelMapper) {
        this.agentPointRepository = agentPointRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AgentPoint create(CreateAgentPointDto dto) {
        return agentPointRepository.save(
                modelMapper.map(dto, AgentPoint.class)
        );
    }

    @Override
    public Page<AgentPoint> getAll(Pageable pageable) {
        return agentPointRepository.findAll(pageable);
    }

    @Override
    public AgentPoint getById(Integer id) throws AppException {
        return agentPointRepository.findById(id).orElseThrow(AgentPointNotFoundException::new);
    }

    @Override
    public AgentPoint updateById(Integer id, UpdateAgentPointDto dto) throws AppException {
        var agentPoint = agentPointRepository.findById(id).orElseThrow(AgentPointNotFoundException::new);
        modelMapper.map(dto, agentPoint);
        return agentPointRepository.save(agentPoint);
    }

    @Override
    public void deleteById(Integer id) throws AppException {
        if (!agentPointRepository.existsById(id)){
            throw new AgentPointNotFoundException();
        }
        agentPointRepository.deleteById(id);
    }
}
