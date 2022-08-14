package com.hadeel.HR_System.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Directorate {
    @Id
    @GeneratedValue
    private Long directorateId;
    private String directorateName;

// relation directorate with **** Manager ****
    @OneToMany(
            mappedBy = "directorate",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Department> departments;
//**********************************


    public Directorate() {

    }

    public String getDirectorateName() {
        return directorateName;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public Long getDirectorateId() {
        return directorateId;
    }

    public String getName() {
        return directorateName;
    }

    public void setDirectorateName(String name) {
        this.directorateName = name;
    }
}
