package com.example.HrSystem.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Leave {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Leave() {
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}