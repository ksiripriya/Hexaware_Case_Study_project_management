package com.hexaware.dao;
//Import necessary exceptions for handling errors
import com.hexaware.exception.EmployeeNotFoundException;
import com.hexaware.exception.ProjectNotFoundException;
import com.hexaware.model.Employee;
import com.hexaware.model.Project;
import com.hexaware.model.Task;
import com.hexaware.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// Implementation class for IProjectRepository interface.
public class ProjectRepositoryImpl implements IProjectRepository {

    @Override
    public boolean createEmployee(Employee emp) {
    	//Inserts a new employee into the database.
        String sql = "INSERT INTO Employee (name, designation, gender, salary, project_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getDesignation());
            stmt.setString(3, emp.getGender());
            stmt.setDouble(4, emp.getSalary());
            stmt.setInt(5, emp.getProjectId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createProject(Project project) {
    	//Inserts a new project into the database.
        String sql = "INSERT INTO Project (projectname, description, start_date, project_status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());
            stmt.setDate(3, project.getStartDate());
            stmt.setString(4, project.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createTask(Task task) {
    	//Inserts a new task into the database.
        String sql = "INSERT INTO Task (task_name, project_id, employee_id, task_status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTaskName());
            stmt.setInt(2, task.getProjectId());
            stmt.setInt(3, task.getEmployeeId());
            stmt.setString(4, task.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean assignProjectToEmployee(int projectId, int employeeId) throws EmployeeNotFoundException {
    	//Assigns an employee to a specific project.
        String sql = "UPDATE Employee SET project_id = ? WHERE id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, projectId);
            stmt.setInt(2, employeeId);
            if (stmt.executeUpdate() == 0) {
                throw new EmployeeNotFoundException("Employee ID " + employeeId + " not found!");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean assignTaskInProjectToEmployee(int taskId, int projectId, int employeeId) throws EmployeeNotFoundException {
    	//Assigns a task within a project to an employee.
        String sql = "UPDATE Task SET employee_id = ? WHERE task_id = ? AND project_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setInt(2, taskId);
            stmt.setInt(3, projectId);
            if (stmt.executeUpdate() == 0) {
                throw new EmployeeNotFoundException("Employee ID " + employeeId + " not found!");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(int userId) throws EmployeeNotFoundException {
    	//Deletes an employee from the database.
        String sql = "DELETE FROM Employee WHERE id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            if (stmt.executeUpdate() == 0) {
                throw new EmployeeNotFoundException("Employee ID " + userId + " not found!");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProject(int projectId) throws ProjectNotFoundException {
    	//Deletes a project from the database.
        String sql = "DELETE FROM Project WHERE id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, projectId);
            if (stmt.executeUpdate() == 0) {
                throw new ProjectNotFoundException("Project ID " + projectId + " not found!");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//Retrieves all tasks assigned to an employee within a specific project.
    @Override
    public List<Task> getAllTasks(int empId, int projectId) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM Task WHERE employee_id = ? AND project_id = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empId);
            stmt.setInt(2, projectId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Task task = new Task(
                        rs.getString("task_name"),
                        rs.getInt("project_id"),
                        rs.getInt("employee_id"),
                        rs.getString("task_status")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
