package com.hadeel.HR_System.services;

import com.hadeel.HR_System.Models.Employee;
import com.hadeel.HR_System.Reposetoreis.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;



    public List<Employee> getAllEmployees()
    {
        List<Employee> result = (List<Employee>) employeeRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Employee>();
        }
    }

    public Employee getEmployeeById(int id) throws ChangeSetPersister.NotFoundException
    {
        Optional<Employee> employee = employeeRepository.findById((long) id);

        if(!employee.isPresent())
            System.out.println("Not Present Employee");

        return employee.get();
    }

    public Employee createOrUpdateEmployee(Employee employeeEntity)
    {
        if(employeeEntity.getEmployeeId() == 0) {

            employeeEntity = employeeRepository.save(employeeEntity);

            return employeeEntity;
        }
        else
        {
            Optional<Employee> employee = employeeRepository.findById((long) employeeEntity.getEmployeeId());

            if(employee.isPresent())
            {
                Employee newEntity = employee.get();
                newEntity.setFirstName(employeeEntity.getFirstName());
                newEntity.setLastName(employeeEntity.getLastName());

                newEntity = employeeRepository.save(newEntity);

                return newEntity;
            } else {
                employeeEntity = employeeRepository.save(employeeEntity);

                return employeeEntity;
            }
        }
    }

    public void deleteEmployeeById(int id)  {
        Optional<Employee> employee = employeeRepository.findById((long) id);

        if(employee.isPresent())
        {
            employeeRepository.deleteById((long) id);
        } else {
            System.out.println("No employee record exist for given id");
        }
    }


   public List<Employee> findAllByDepartmentId(Long id){

        return employeeRepository.findAllByDepartmentId(id);
   }


    public List<Employee> findEmployeesByCreationDate(Date start ,Date end){
        return employeeRepository.findByExpiryDateBetween(start,end);
    }
}