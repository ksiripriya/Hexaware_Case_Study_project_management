package com.hexaware.model;

//Represents an Employee entity with essential attributes
public class Employee {
    private String name;
    private String designation;
    private String gender;
    private double salary;
    private int projectId;
//Default constructor for Employee.
    public Employee() {}
//Parameterized constructor for Employee.
    public Employee(String name, String designation, String gender, double salary, int projectId) {
        
        this.name = name;
        this.designation = designation;
        this.gender = gender;
        this.salary = salary;
        this.projectId = projectId;
    }
// Getter and Setter methods for each attribute to ensure encapsulation.
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }
}


