package org.catncode.leaders_backend.task.specification;

import org.catncode.leaders_backend.task.entity.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {
    public static Specification<Task> filterTasks(Integer employeeId, Integer agentPointId, Boolean archived) {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.asc(root.get("id")));

            var predicate = criteriaBuilder.conjunction();

            if (employeeId != null) {
                query.orderBy(
                        criteriaBuilder.asc(root.get("orderNumber")),
                        criteriaBuilder.asc(root.get("id"))
                );

                predicate = criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("employee").get("id"), employeeId),
                        predicate
                );
            }
            else if (agentPointId != null) {
                predicate = criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("agentPoint").get("id"), agentPointId),
                        predicate
                );
            }

            predicate = criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("isArchived"), archived),
                    predicate
            );

            return predicate;
        };
    }
}
