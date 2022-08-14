package com.hadeel.HR_System.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Manager {
    @Id
    @GeneratedValue
    private int managerId;
    private String name;

// relation Manager with **** Departmnet ****
    @OneToMany(
            mappedBy = "manager",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Department> departments;
//*************************************
// relation Manager with ***** Directorate ******
    @ManyToOne
    @JoinColumn(name = "directorateId")
    private Department directorate;
//*********************************
    @OneToMany(
            mappedBy = "manager",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Leave> leaves;
//*********************************
    @OneToOne
    @JoinColumn(name = "department")
    private Department department;


    public Manager() {
    }

    public int getManagerId() {
        return managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Department getDirectorate() {
        return directorate;
    }

    public void setDirectorate(Department directorate) {
        this.directorate = directorate;
    }
}
