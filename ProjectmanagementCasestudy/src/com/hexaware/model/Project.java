package com.hexaware.model;

import java.sql.Date;
//Represents a Project entity with essential attributes
public class Project {
    private String name;
    private String description;
    private Date startDate;
    private String status;
//Default constructor for Project.
    public Project() {}
//Parameterized constructor for Project.
    public Project(String name, String description, Date startDate, String status) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.status = status;
    }
//Getter and Setter methods for each attribute to ensure encapsulation.
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
