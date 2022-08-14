package com.hadeel.HR_System.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
public class Leave {
    @Id
    @GeneratedValue
    private Long leaveId;
    private String name;
    private Date timeLeave;



    private boolean accepted;

//relation Leave with **** Employee ****
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
//********************************
    @ManyToOne
    @JoinColumn(name = "manager")
    private Manager manager;

    public Leave(String name) {
        this.name = name;
//        this.timeLeave = //TODO
        this.accepted =false;

    }

    public Leave() {
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
    public Date getTimeLeave() {
        return timeLeave;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
