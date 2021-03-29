package tasks.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.DateService;
import tasks.services.TasksService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class NewEditControllerTest {
    private ObservableList<Task> tasksList;
    private DateService dateService;
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @BeforeEach
    void setUp() {
        tasksList = FXCollections.observableArrayList();
        dateService = new DateService(new TasksService(new ArrayTaskList()));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveChangesECP1() {

        // Setup / Arrange
        Date startDateWithNoTime = dateService.getDateValueFromLocalDate(LocalDate.now());
        Date newStartDate = dateService.getDateMergedWithTime("12:00", startDateWithNoTime);
        Task task = new Task("Mc cu Dani", newStartDate);

        // Act
        tasksList.add(task);

        // Assert
        assertEquals(1, tasksList.size());

    }

    @Test
    void saveChangesECP2() {
        Date startDateWithNoTime = dateService.getDateValueFromLocalDate(LocalDate.now());
        Date newStartDate = dateService.getDateMergedWithTime("22:00", startDateWithNoTime);
        Task task = new Task("Stuff", newStartDate);
        tasksList.add(task);
        assertEquals(1, tasksList.size());
        assertEquals(task, tasksList.get(0));
    }

    @Test
    void saveChangesECP3() {
        try {
            Date startDateWithNoTime = dateService.getDateValueFromLocalDate(LocalDate.now());
            Date newStartDate = dateService.getDateMergedWithTime("", startDateWithNoTime);
            Task task = new Task("Stuff", newStartDate);
            tasksList.add(task);
            fail();
        } catch (Exception ex) {
            assertTrue(true, "It should throw error");
        }
    }

    @Test
    void saveChangesECP4() {
        try {
            Date startDateWithNoTime = dateService.getDateValueFromLocalDate(LocalDate.now());
            Date newStartDate = dateService.getDateMergedWithTime("50:90", startDateWithNoTime);
            Task task = new Task("Stuff", newStartDate);
            tasksList.add(task);
            fail();
        } catch (Exception ex) {
            assertTrue(true, "It should throw error");
        }
    }

    @Test
    void saveChangesBVA1() {
        Date startDateWithNoTime = dateService.getDateValueFromLocalDate(LocalDate.parse("01/01/2021", DATE_FORMAT));
        Date newStartDate = dateService.getDateMergedWithTime("12:00", startDateWithNoTime);
        Task task = new Task("", newStartDate);
        tasksList.add(task);
        assertEquals(1, tasksList.size());
    }

    @Test
    void saveChangesBVA2() {
        Task task = new Task("Titlu", new Date(2021, Calendar.JANUARY, 1));
        tasksList.add(task);
        assertEquals(1, tasksList.size());
        assertEquals(task, tasksList.get(0));
    }

    @Test
    void saveChangesBVA3() {
        Task task = new Task("Titlu", new Date(2021,Calendar.DECEMBER, 31));
        tasksList.add(task);
        assertEquals(1, tasksList.size());
        assertEquals(task, tasksList.get(0));
    }

    @Test
    void saveChangesBVA4() {
        try {
            Date startDateWithNoTime = dateService.getDateValueFromLocalDate(LocalDate.parse("0/0/2021", DATE_FORMAT));
            Date newStartDate = dateService.getDateMergedWithTime("25:60", startDateWithNoTime);
            Task task = new Task("titlu", newStartDate);
            tasksList.add(task);
            fail();
        } catch (Exception ex) {
            assertTrue(true, "should throw error");
        }
    }
}