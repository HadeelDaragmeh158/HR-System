package com.hadeel.HR_System.services;

import com.hadeel.HR_System.Models.Department;
import com.hadeel.HR_System.Reposetoreis.DepartmentRepository;
import com.hadeel.HR_System.Reposetoreis.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        List<Department> result = (List<Department>) departmentRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Department>();
        }
    }

    @Override
    public Department createNewDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> findASingleDepartment(int id) {
        return departmentRepository.findById((long) id);
    }
    @Override
    public void deleteEmployee(int employeeId) {
        employeeRepository.deleteById((long) employeeId);
    }
}
