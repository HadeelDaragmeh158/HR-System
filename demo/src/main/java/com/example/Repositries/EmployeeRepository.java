package com.example.HrSystem.Repositries;

import com.example.HrSystem.Model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
