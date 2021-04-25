package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ArrayTaskListStep1Test {
    ArrayTaskList arrayTaskList;
    Task task = Mockito.mock(Task.class);

    @BeforeEach
    void setUp() {
        arrayTaskList = Mockito.mock(ArrayTaskList.class);
        Mockito.doNothing().when(arrayTaskList).add(task);
        Mockito.when(arrayTaskList.remove(task)).thenReturn(true);
    }

    @Test
    void add() {
        arrayTaskList.add(task);
        Mockito.verify(arrayTaskList, Mockito.times(1)).add(task);
    }

    @Test
    void remove() {
        arrayTaskList.remove(task);
        Mockito.verify(arrayTaskList, Mockito.times(1)).remove(task);
    }
}