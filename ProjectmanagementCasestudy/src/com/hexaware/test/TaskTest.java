package com.hexaware.test;
import static org.junit.Assert.*;
import org.junit.Test;
import com.hexaware.dao.ProjectRepositoryImpl;
import com.hexaware.model.Task;

public class TaskTest {

    @Test
    public void testCreateTask() {
        ProjectRepositoryImpl service = new ProjectRepositoryImpl();
        Task task = new Task("develop", 1, 1, "started");
        boolean result = service.createTask(task);
        assertTrue(result); // Verifies task creation success
    }
}
//Jayaram", "Developer", "Male", 50000, 1