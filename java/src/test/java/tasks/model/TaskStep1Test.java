package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskStep1Test {
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("title", new Date(2021, Calendar.APRIL,12));
    }

    @Test
    void getTitle() {
        assertEquals("title", task.getTitle());
    }

    @Test
    void setTitle() {
        String newTitle = "newTitle";
        task.setTitle(newTitle);
        assertEquals(newTitle, task.getTitle());
    }
}