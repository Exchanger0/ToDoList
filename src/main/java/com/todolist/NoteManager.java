package com.todolist;

import java.util.*;

public class NoteManager {
    private final List<Note> notes = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();

    public List<Note> getAll() {
        synchronized (notes) {
            synchronized (tasks) {
                List<Note> list = new ArrayList<>();
                list.addAll(notes);
                list.addAll(tasks);
                return list;
            }
        }
    }

    public List<Task> getTasks() {
        synchronized (tasks) {
            return Collections.unmodifiableList(tasks);
        }
    }

    public void add(Note note) {
        if (note.getClass().equals(Task.class)) {
            synchronized (tasks) {
                tasks.add((Task) note);
            }
        } else {
            synchronized (notes) {
                notes.add(note);
            }
        }
    }

    public void remove(Note note) {
        if (note.getClass().equals(Task.class)) {
            synchronized (tasks) {
                tasks.remove(note);
            }
        } else {
            synchronized (notes) {
                notes.remove(note);
            }
        }
    }
}
