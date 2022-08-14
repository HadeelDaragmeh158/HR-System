package com.hadeel.HR_System.Reposetoreis;


import com.hadeel.HR_System.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByDepartmentId(Long id);
    List<Employee> findByExpiryDateBetween(Date start, Date end);

}
