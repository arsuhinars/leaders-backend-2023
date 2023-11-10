package org.catncode.leaders_backend.employee.repository;

import org.catncode.leaders_backend.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByAccountId(Integer accountId);
}
