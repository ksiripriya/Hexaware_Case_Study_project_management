package com.hexaware.test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.hexaware.dao.ProjectRepositoryImpl;
import com.hexaware.exception.EmployeeNotFoundException;

public class ExceptionTest {

    @Test
    public void testDeleteNonExistingEmployee() {
        ProjectRepositoryImpl service = new ProjectRepositoryImpl();
        assertThrows(EmployeeNotFoundException.class, () -> {
            service.deleteEmployee(9999); // Trying to delete non-existing employee
        });
    }
}