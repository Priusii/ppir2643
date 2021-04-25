package tasks.model;

import javafx.collections.ObservableList;

import java.util.*;

public class TasksOperations {

    public ArrayList<Task> tasks;

    public TasksOperations(ObservableList<Task> tasksList) {
        tasks = new ArrayList<>();
        tasks.addAll(tasksList);
    }

    public Iterable<Task> incoming(Date start, Date end) throws Exception {
        System.out.println(start);
        System.out.println(end);
        ArrayList<Task> incomingTasks = new ArrayList<>();

        if(end.before(start)) {
            throw new Exception("Start date must be before end date");
        }

        if (tasks.isEmpty()) {
            System.out.println("isEmpty()");
            return incomingTasks;
        }
        for (Task t : tasks) {
            Date nextTime = t.getStartTime();
            System.out.println(nextTime);
            if (nextTime != null && nextTime.before(end)) {
                incomingTasks.add(t);
                System.out.println(t.getTitle());
            } else if (nextTime != null && nextTime.equals(end)) {
                incomingTasks.add(t);
                System.out.println(t.getTitle());
            }
        }
        return incomingTasks;
    }

    public SortedMap<Date, Set<Task>> calendar(Date start, Date end) throws Exception {
        Iterable<Task> incomingTasks = incoming(start, end);
        TreeMap<Date, Set<Task>> calendar = new TreeMap<>();

        for (Task t : incomingTasks) {
            Date nextTimeAfter = t.nextTimeAfter(start);
            while (nextTimeAfter != null && (nextTimeAfter.before(end) || nextTimeAfter.equals(end))) {
                if (calendar.containsKey(nextTimeAfter)) {
                    calendar.get(nextTimeAfter).add(t);
                } else {
                    HashSet<Task> oneDateTasks = new HashSet<>();
                    oneDateTasks.add(t);
                    calendar.put(nextTimeAfter, oneDateTasks);
                }
                nextTimeAfter = t.nextTimeAfter(nextTimeAfter);
            }
        }
        return calendar;
    }
}
