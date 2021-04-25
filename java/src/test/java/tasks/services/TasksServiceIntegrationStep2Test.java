package tasks.services;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class TasksServiceIntegrationStep2Test {
    ArrayTaskList arrayTaskList;
    TasksService tasksService;
    Task task1;
    Task task2;
    Task task3;

    @BeforeEach
    void setUp() {
        arrayTaskList = new ArrayTaskList();
        tasksService = new TasksService(arrayTaskList);
        task1 = mock(Task.class);
        task2 = mock(Task.class);
        task3 = mock(Task.class);
        Mockito.when(task1.getTitle()).thenReturn("Titlu1");
        Mockito.when(task1.getStartTime()).thenReturn(new Date(2021, Calendar.FEBRUARY, 10));
        Mockito.when(task2.getTitle()).thenReturn("Titlu2");
        Mockito.when(task2.getStartTime()).thenReturn(new Date(2021, Calendar.MAY, 10));
        Mockito.when(task3.getTitle()).thenReturn("Titlu3");
        Mockito.when(task3.getStartTime()).thenReturn(new Date(2021, Calendar.DECEMBER, 10));
    }

    @Test
    void getObservableList() {
        // act
        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        arrayTaskList.add(task3);
        ObservableList<Task> observableList = tasksService.getObservableList();

        // assert
        assert(observableList.size() == 3);
        assert(observableList.get(0).getTitle().equals("Titlu1"));
    }

    @Test
    void filterTasks() throws Exception {
        // act
        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        arrayTaskList.add(task3);
        ArrayList filtered = (ArrayList)tasksService.filterTasks(new Date(2021, Calendar.JANUARY, 1), new Date(2021, Calendar.MARCH, 1));

        // assert
        assertEquals(task1, filtered.get(0));
    }
}