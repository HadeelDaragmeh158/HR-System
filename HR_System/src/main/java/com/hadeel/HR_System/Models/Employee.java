package com.hadeel.HR_System.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long employeeId;
    private String firstName,lastName;
    private Date expiryDate;

//relation Employee with **** Department ****
    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;
//*******************************
// relation Employee with **** Leave ****
    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Leave> leaves;
//**********************************


    public Employee() {
    }

    public Employee(String firstName, String lastName, Date expiryDate , Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.expiryDate = expiryDate;
        this.department = department;
    }

    public Employee(String firstName, String lastName, Date expiryDate,Department department, List<Leave> leaves) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.expiryDate = expiryDate;
        this.department = department;
        this.leaves = leaves;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreationDate() {
        return expiryDate;
    }

    public void setCreationDate(Date expiryDate) { this.expiryDate = expiryDate;  }

    public Department getDepartment() { return department;    }

    public Date getExpiryDate() {   return expiryDate;  }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Leave> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leave> leaves) {
        this.leaves = leaves;
    }
}
