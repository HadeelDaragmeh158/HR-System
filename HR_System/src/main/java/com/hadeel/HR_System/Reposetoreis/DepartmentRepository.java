package com.hadeel.HR_System.Reposetoreis;


import com.hadeel.HR_System.Models.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Optional<Department> findById(Long id);
}
