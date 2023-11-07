package org.catncode.leaders_backend.employee.repository;

import org.catncode.leaders_backend.employee.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
