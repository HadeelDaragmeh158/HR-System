package com.hadeel.HR_System.services;


import com.hadeel.HR_System.Models.Directorate;
import com.hadeel.HR_System.Models.Employee;
import com.hadeel.HR_System.Reposetoreis.DirectorateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorateService {

    @Autowired
    DirectorateRepository directorateRepository ;


    public List<Directorate> getAllEmployees()
    {
        List<Directorate> result = (List<Directorate>) directorateRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Directorate>();
        }
    }

    public Directorate getEmployeeById(Long id) throws ChangeSetPersister.NotFoundException
    {
        Optional<Directorate> directorate = directorateRepository.findById(id);

        if(!directorate.isPresent())
            System.out.println("Not Present Employee");

        return directorate.get();
    }

    public Directorate createOrUpdateEmployee(Directorate directorate)
    {
        if(directorate.getDirectorateId() == 0) {

            directorate = directorateRepository.save(directorate);

            return directorate;
        }
        else
        {
            Optional<Directorate> directorate1 = directorateRepository.findById(directorate.getDirectorateId());

            if(directorate1.isPresent())
            {
                Directorate newEntity = directorate1.get();
                newEntity.setDirectorateName(directorate.getName());

                newEntity = directorateRepository.save(newEntity);

                return newEntity;
            } else {
                directorate = directorateRepository.save(directorate);

                return directorate;
            }
        }
    }

    public void deleteEmployeeById(Long id)  {
        Optional<Directorate> directorate = directorateRepository.findById(id);

        if(directorate.isPresent())
        {
            directorateRepository.deleteById(id);
        } else {
            System.out.println("No employee record exist for given id");
        }
    }

}
