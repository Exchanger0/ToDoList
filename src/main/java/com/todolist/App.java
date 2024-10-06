package com.todolist;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

public class App extends Application {
    public static Scene scene;
    public static MainScreen mainScreen;
    public static final TaskManager taskManager = new TaskManager();

    @Override
    public void start(Stage stage) {
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task №"+i, "content", Task.Priority.values()[ThreadLocalRandom.current().nextInt(0, 3)], LocalDateTime.now().plusMinutes(5));
            task.setExecutionDate(LocalDateTime.now().plusMinutes(i+1));
            taskManager.add(task);
        }

        Notifier notifier = new Notifier(taskManager);
        Timer timer = new Timer(true);
        timer.schedule(notifier, (60 - LocalTime.now().getSecond()) * 1000, 60_000);

        mainScreen = new MainScreen();

        scene = new Scene(mainScreen);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("ToDoList");
        stage.setWidth(600);
        stage.setHeight(700);
        stage.show();
    }
}
