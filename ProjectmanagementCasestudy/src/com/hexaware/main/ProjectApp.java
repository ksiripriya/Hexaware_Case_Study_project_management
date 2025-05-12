package com.hexaware.main;
//Import DAO classes for database interaction
import com.hexaware.dao.*;
import com.hexaware.model.*;
import com.hexaware.exception.*;

import java.sql.Date;
import java.util.Scanner;

public class ProjectApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProjectRepositoryImpl service = new ProjectRepositoryImpl();
// Infinite loop to display menu until the user chooses to exit
        while (true) {
            System.out.println("\n=== Project Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Project");
            System.out.println("3. Add Task");
            System.out.println("4. Assign Project to Employee");
            System.out.println("5. Assign Task to Employee");
            System.out.println("6. Delete Employee");
            System.out.println("7. Delete Project");
            System.out.println("8. List Tasks for Employee in Project");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
            	//Switch statement to handle different menu options
                switch (choice) {
                    case 1:
                    	// Adding a new Employee
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter designation: ");
                        String designation = scanner.nextLine();
                        System.out.print("Enter gender: ");
                        String gender = scanner.nextLine();
                        System.out.print("Enter salary: ");
                        double salary = scanner.nextDouble();
                        System.out.print("Enter projectid: ");
                        int pid = scanner.nextInt();
                        Employee emp = new Employee(name, designation, gender, salary, pid);
                        service.createEmployee(emp);
                        System.out.println("Employee added successfully.");
                        break;
                    case 2:
                    	// Adding a new Project
                        System.out.print("Enter project name: ");
                        String pname = scanner.nextLine();
                        System.out.print("Enter description: ");
                        String desc = scanner.nextLine();
                        System.out.print("Enter start date (YYYY-MM-DD): ");
                        String startDate = scanner.nextLine();
                        System.out.print("Enter status: ");
                        String status = scanner.nextLine();
                        Project project = new Project(pname, desc, Date.valueOf(startDate), status);
                        service.createProject(project);
                        System.out.println("Project added successfully.");
                        break;

                    case 3:
                    	// Adding a new Task
                        System.out.print("Enter task name: ");
                        String taskName = scanner.nextLine();
                        System.out.print("Enter project ID: ");
                        int pid2 = scanner.nextInt();
                        System.out.print("Enter employee ID: ");
                        int eid1 = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.print("Enter status: ");
                        String taskStatus = scanner.nextLine();
                        Task task = new Task(taskName, pid2, eid1, taskStatus);
                        service.createTask(task);
                        System.out.println("Task added successfully.");
                        break;

                    case 4:
                    	// Assigning a Project to an Employee
                        System.out.print("Enter project ID: ");
                        int prId = scanner.nextInt();
                        System.out.print("Enter employee ID: ");
                        int empId = scanner.nextInt();
                        service.assignProjectToEmployee(prId, empId);
                        System.out.println("Project assigned successfully.");
                        break;

                    case 5:
                    	// Assigning a Task to an Employee within a Project
                        System.out.print("Enter task ID: ");
                        int taskId = scanner.nextInt();
                        System.out.print("Enter project ID: ");
                        int projId = scanner.nextInt();
                        System.out.print("Enter employee ID: ");
                        int employeeId = scanner.nextInt();
                        service.assignTaskInProjectToEmployee(taskId, projId, employeeId);
                        System.out.println("Task assigned successfully.");
                        break;

                    case 6:
                    	// Deleting an Employee
                        System.out.print("Enter employee ID to delete: ");
                        int delEmpId = scanner.nextInt();
                        service.deleteEmployee(delEmpId);
                        System.out.println("Employee deleted successfully.");
                        break;

                    case 7:
                    	// Deleting a Project
                        System.out.print("Enter project ID to delete: ");
                        int delProjId = scanner.nextInt();
                        service.deleteProject(delProjId);
                        System.out.println("Project deleted successfully.");
                        break;

                    case 8:
                    	// Listing all tasks assigned to an employee within a project
                        System.out.print("Enter employee ID: ");
                        int empSearchId = scanner.nextInt();
                        System.out.print("Enter project ID: ");
                        int projSearchId = scanner.nextInt();
                        service.getAllTasks(empSearchId, projSearchId)
                               .forEach(taskObj -> System.out.println("Task Name: " + taskObj.getTaskName() + ", Status: " + taskObj.getStatus()));
                        break;

                    case 9:
                    	// Exit the application
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (EmployeeNotFoundException | ProjectNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}
