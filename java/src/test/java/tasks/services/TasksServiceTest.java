package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TasksOperations;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TasksServiceTest {
    private Task task1 = new Task("title1", new Date(2021, Calendar.FEBRUARY, 20, 10, 0));
    private Task task2 = new Task("title2", new Date(2021, Calendar.FEBRUARY, 21, 10, 0));
    private Task task3 = new Task("title3", new Date(2021, Calendar.FEBRUARY, 22, 10, 0));
    private ArrayTaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new ArrayTaskList();
    }

    @Test
    void WBT_filterTasks_valid_3_tasks() throws Exception {
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        ObservableList<Task> observableList = FXCollections.observableArrayList(taskList.getAll());
        TasksOperations tasksOps = new TasksOperations(observableList);
        Date start = new Date(2021, Calendar.FEBRUARY, 12, 10, 0);
        Date end = new Date(2021, Calendar.MARCH, 12, 10, 0);

        Iterable<Task> filtered = tasksOps.incoming(start,end);

        assertEquals(observableList.size(), ((ArrayList)filtered).size());
    }

    @Test
    void WBT_filterTasks_valid_end_date_3_tasks() throws Exception {
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        ObservableList<Task> observableList = FXCollections.observableArrayList(taskList.getAll());
        TasksOperations tasksOps = new TasksOperations(observableList);
        Date start = new Date(2021, Calendar.FEBRUARY, 12, 10, 0);
        Date end = new Date(2021, Calendar.FEBRUARY, 22, 10, 0);

        Iterable<Task> filtered = tasksOps.incoming(start,end);

        assertEquals(observableList.size(), ((ArrayList)filtered).size());
    }

    @Test
    void WBT_filterTasks_valid_2_tasks() throws Exception {
        taskList.add(task1);
        taskList.add(task2);
        ObservableList<Task> observableList = FXCollections.observableArrayList(taskList.getAll());
        TasksOperations tasksOps = new TasksOperations(observableList);
        Date start = new Date(2021, Calendar.FEBRUARY, 12, 10, 0);
        Date end = new Date(2021, Calendar.MARCH, 12, 10, 0);

        Iterable<Task> filtered = tasksOps.incoming(start,end);

        assertEquals(observableList.size(), ((ArrayList)filtered).size());
    }

    @Test
    void WBT_filterTasks_empty_list_valid_0_tasks() throws Exception {
        ObservableList<Task> observableList = FXCollections.observableArrayList(taskList.getAll());
        TasksOperations tasksOps = new TasksOperations(observableList);
        Date start = new Date(2021, Calendar.FEBRUARY, 12, 10, 0);
        Date end = new Date(2021, Calendar.MARCH, 12, 10, 0);

        Iterable<Task> filtered = tasksOps.incoming(start,end);

        assertEquals(observableList.size(), ((ArrayList)filtered).size());
    }

    @Test
    void WBT_filterTasks_valid_0_tasks() throws Exception {
        ObservableList<Task> observableList = FXCollections.observableArrayList(taskList.getAll());
        TasksOperations tasksOps = new TasksOperations(observableList);
        Date start = new Date(2021, Calendar.MAY, 12, 10, 0);
        Date end = new Date(2021, Calendar.JUNE, 12, 10, 0);

        Iterable<Task> filtered = tasksOps.incoming(start,end);

        assertEquals(0, ((ArrayList)filtered).size());
    }

    @Test
    void WBT_filterTasks_invalid_throws_error() throws Exception {
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        ObservableList<Task> observableList = FXCollections.observableArrayList(taskList.getAll());
        TasksOperations tasksOps = new TasksOperations(observableList);
        Date start = new Date(2021, Calendar.MAY, 12, 10, 0);
        Date end = new Date(2021, Calendar.JUNE, 12, 10, 0);

        assertThrows(Exception.class, () -> tasksOps.incoming(end,start), "Start date must be before end date");
    }
}