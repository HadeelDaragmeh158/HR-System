package com.hadeel.HR_System.services;

import com.hadeel.HR_System.Models.Department;

import java.util.List;
import java.util.Optional;


public interface DepartmentService {

    List<Department> getAllDepartments();
    Department createNewDepartment(Department department);
    Optional<Department> findASingleDepartment(int departmentId);
    void deleteEmployee(int employeeId);
}
