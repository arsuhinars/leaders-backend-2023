package org.catncode.leaders_backend.agent_point.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.catncode.leaders_backend.agent_point.dto.AgentPointDto;
import org.catncode.leaders_backend.agent_point.dto.CreateAgentPointDto;
import org.catncode.leaders_backend.agent_point.dto.UpdateAgentPointDto;
import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/agent_points")
public interface AgentPointController {
    @PostMapping
    AgentPointDto createAgentPoint(@Valid @RequestBody CreateAgentPointDto schema);

    @GetMapping("/all")
    Pagination<AgentPointDto> getAllAgentPoints(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size
    );

    @GetMapping("/{id}")
    AgentPointDto getAgentPointById(@PathVariable @NotNull @Min(1) Integer id) throws AppException;

    @PutMapping("/{id}")
    AgentPointDto updateAgentPointById(
            @Valid @RequestBody UpdateAgentPointDto schema, @PathVariable @NotNull @Min(1) Integer id
    ) throws AppException;

    @DeleteMapping("/{id}")
    void deleteAgentPointById(@PathVariable @NotNull @Min(1) Integer id) throws AppException;
}
