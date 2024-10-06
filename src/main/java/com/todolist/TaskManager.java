package com.todolist;

import java.util.*;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public List<Task> get() {
        synchronized (tasks) {
            return Collections.unmodifiableList(tasks);
        }
    }

    public void add(Task task) {
        synchronized (tasks) {
            tasks.add(task);
        }
    }

    public void remove(Task task) {
        synchronized (tasks) {
            tasks.remove(task);
        }
    }
}
