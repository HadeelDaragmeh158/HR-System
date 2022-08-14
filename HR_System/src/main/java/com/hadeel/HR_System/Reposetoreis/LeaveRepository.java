package com.hadeel.HR_System.Reposetoreis;

import com.hadeel.HR_System.Models.Leave;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends CrudRepository<Leave, Long> {
}


