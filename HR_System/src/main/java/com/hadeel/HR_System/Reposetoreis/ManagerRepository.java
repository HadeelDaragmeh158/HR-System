package com.hadeel.HR_System.Reposetoreis;


import com.hadeel.HR_System.Models.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends CrudRepository<Manager,Integer> {
}
