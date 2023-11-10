package org.catncode.leaders_backend.agent_point.service;

import org.catncode.leaders_backend.agent_point.dto.CreateAgentPointDto;
import org.catncode.leaders_backend.agent_point.dto.UpdateAgentPointDto;
import org.catncode.leaders_backend.agent_point.entity.AgentPoint;
import org.catncode.leaders_backend.agent_point.exception.AgentPointNotFoundException;
import org.catncode.leaders_backend.agent_point.repository.AgentPointRepository;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.navigation.repository.LocationRepository;
import org.catncode.leaders_backend.navigation.service.DistanceMatrixService;
import org.catncode.leaders_backend.navigation.service.GeocodeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgentPointServiceImpl implements AgentPointService {
    private final AgentPointRepository agentPointRepository;
    private final GeocodeService geocodeService;
    private final DistanceMatrixService distanceMatrixService;
    private final ModelMapper modelMapper;

    public AgentPointServiceImpl(
            LocationRepository locationRepository,
            AgentPointRepository agentPointRepository,
            GeocodeService geocodeService,
            DistanceMatrixService distanceMatrixService,
            ModelMapper modelMapper
    ) {
        this.agentPointRepository = agentPointRepository;
        this.geocodeService = geocodeService;
        this.distanceMatrixService = distanceMatrixService;
        this.modelMapper = modelMapper;
    }

    @Override
    public AgentPoint create(CreateAgentPointDto dto) {
        var agentPoint = modelMapper.map(dto, AgentPoint.class);
        agentPoint.setLocation(
                geocodeService.createLocationByAddress(dto.getAddress())
        );
        agentPoint = agentPointRepository.save(agentPoint);

        distanceMatrixService.recalculateFor(agentPoint.getLocation());

        return agentPoint;
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

        boolean addressChanged = !agentPoint.getAddress().equals(dto.getAddress());
        if (addressChanged) {
            agentPoint.setLocation(
                    geocodeService.createLocationByAddress(dto.getAddress())
            );
        }
        modelMapper.map(dto, agentPoint);
        agentPoint = agentPointRepository.save(agentPoint);

        if (addressChanged) {
            distanceMatrixService.recalculateFor(agentPoint.getLocation());
        }

        return agentPoint;
    }

    @Override
    public void deleteById(Integer id) throws AppException {
        if (!agentPointRepository.existsById(id)){
            throw new AgentPointNotFoundException();
        }
        agentPointRepository.deleteById(id);
    }
}
