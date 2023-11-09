package org.catncode.leaders_backend.agent_point.repository;

import org.catncode.leaders_backend.agent_point.entity.AgentPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AgentPointRepository extends CrudRepository<AgentPoint, Integer>, PagingAndSortingRepository<AgentPoint, Integer> {

}
