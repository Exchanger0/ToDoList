package com.todolist;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class App extends Application {
    public static Scene scene;
    public static MainScreen mainScreen;
    public static final List<Task> tasks = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task №"+i, "content", Task.Priority.values()[ThreadLocalRandom.current().nextInt(0, 3)]);
            if (i % 2 == 0)
                task.setFinalized(true);
            task.setExecutionDate(LocalDateTime.of(2024, Month.SEPTEMBER,
                    ThreadLocalRandom.current().nextInt(1, 30), 14, 50));
            tasks.add(task);
        }
        mainScreen = new MainScreen(tasks);

        scene = new Scene(mainScreen);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("ToDoList");
        stage.setWidth(600);
        stage.setHeight(700);
        stage.show();
    }

    public static void removeTask(Task task) {
        tasks.remove(task);
        mainScreen.removeTask(task);
    }

    public static void addTask(Task task) {
        tasks.add(task);
        mainScreen.addTask(task);
    }
}
