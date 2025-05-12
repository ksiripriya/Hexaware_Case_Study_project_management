package com.hexaware.model;
//Represents a Task entity with essential attributes
public class Task {
    private String taskName;
    private int projectId;
    private int employeeId;
    private String status;
//Default constructor for Project.
    public Task() {}
//Parameterized constructor for Project.
    public Task(String taskName, int projectId, int employeeId, String status) {
        this.taskName = taskName;
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.status = status;
    }
  //Getter and Setter methods for each attribute to ensure encapsulation.
    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
