package com.hadeel.HR_System.Reposetoreis;


import com.hadeel.HR_System.Models.Directorate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorateRepository extends CrudRepository<Directorate, Long> {

}
