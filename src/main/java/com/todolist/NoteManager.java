package com.todolist;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class NoteManager {
    private List<Note> notes = new ArrayList<>();
    private final Object notesKey = new Object();
    private List<Task> tasks = new ArrayList<>();
    private final Object tasksKey = new Object();
    private Path saveFile;

    public List<Note> getAll() {
        synchronized (notesKey) {
            synchronized (tasksKey) {
                List<Note> list = new ArrayList<>();
                list.addAll(notes);
                list.addAll(tasks);
                return list;
            }
        }
    }

    public List<Task> getTasks() {
        synchronized (tasksKey) {
            return Collections.unmodifiableList(tasks);
        }
    }

    public void add(Note note) {
        if (note.getClass().equals(Task.class)) {
            synchronized (tasksKey) {
                tasks.add((Task) note);
            }
        } else {
            synchronized (notesKey) {
                notes.add(note);
            }
        }
    }

    public void remove(Note note) {
        if (note.getClass().equals(Task.class)) {
            synchronized (tasksKey) {
                tasks.remove(note);
            }
        } else {
            synchronized (notesKey) {
                notes.remove(note);
            }
        }
    }

    public Path getSaveFile() {
        return saveFile;
    }

    public void setSaveFile(Path saveFile) {
        this.saveFile = saveFile;
    }

    public void save() {
        save(saveFile);
    }

    public void save(Path path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            oos.writeObject(getAll());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load() {
        load(saveFile);
    }

    public void load(Path path) {
        if (Files.exists(path)) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile.toFile()))) {
                List<Note> all = (List<Note>) ois.readObject();
                List<Task> tasks = new ArrayList<>();
                List<Note> notes = new ArrayList<>();
                for (Note n : all) {
                    if (n instanceof Task t) {
                        tasks.add(t);
                    } else {
                        notes.add(n);
                    }
                }
                synchronized (notesKey) {
                    this.notes = notes;
                }
                synchronized (tasksKey) {
                    this.tasks = tasks;
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
