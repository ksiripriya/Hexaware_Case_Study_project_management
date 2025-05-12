package com.hexaware.dao;
//Import necessary exceptions for handling missing employees and projects
import com.hexaware.exception.EmployeeNotFoundException;
import com.hexaware.exception.ProjectNotFoundException;
//Import entity classes for database interactions
import com.hexaware.model.*;
import java.util.List;

public interface IProjectRepository {
	
	/* Adds a new employee,new task,new project to the database.*/

    boolean createEmployee(Employee emp);

    boolean createProject(Project project);

    boolean createTask(Task task);

    boolean assignProjectToEmployee(int projectId, int employeeId) throws EmployeeNotFoundException;

    boolean assignTaskInProjectToEmployee(int taskId, int projectId, int employeeId) throws EmployeeNotFoundException;

    boolean deleteEmployee(int userId) throws EmployeeNotFoundException;

    boolean deleteProject(int projectId) throws ProjectNotFoundException;

    List<Task> getAllTasks(int empId, int projectId);
}
