package com.hexaware.test;
import static org.junit.Assert.*;
import org.junit.Test;
import com.hexaware.dao.ProjectRepositoryImpl;
import com.hexaware.model.Employee;
public class EmployeeTest {

	    @Test
	    public void testCreateEmployee() {
	        ProjectRepositoryImpl service = new ProjectRepositoryImpl();
	        Employee emp = new Employee("Jayaram", "Developer", "Male", 50000, 1);
	        boolean result = service.createEmployee(emp);
	        assertTrue(result); // Passes if employee creation is successful
	    }
	}
	
