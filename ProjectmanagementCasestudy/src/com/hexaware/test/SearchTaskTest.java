package com.hexaware.test;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import com.hexaware.dao.ProjectRepositoryImpl;
import com.hexaware.model.Task;

public class SearchTaskTest {

    @Test
    public void testGetAllTasks() {
        ProjectRepositoryImpl service = new ProjectRepositoryImpl();
        List<Task> tasks = service.getAllTasks(1,1);
        assertNotNull(tasks); // Ensures list is not empty
        assertFalse(tasks.isEmpty()); // Ensures tasks exist for the employee
    }
}
