package com.todolist;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;
/*
todo:
 * Сделать сохранение задач на диске
 * Сделать возможность импорта задач из директории
 * Сделать фоновый поток notifier, который работает даже после закрытия приложения (в настройках можно будет убрать)
 */
public class App extends Application {
    public static Scene scene;
    public static MainScreen mainScreen;
    public static final NoteManager NOTE_MANAGER = new NoteManager();

    @Override
    public void start(Stage stage) {
        for (int i = 0; i < 10; i++) {
            Task note = new Task("Task №"+i, "content", Note.Priority.values()[ThreadLocalRandom.current().nextInt(0, 3)], LocalDateTime.now().plusMinutes(5));
            note.setExecutionDate(LocalDateTime.now().plusMinutes(i+1));
            NOTE_MANAGER.add(note);
        }

        mainScreen = new MainScreen();

        scene = new Scene(mainScreen);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("ToDoList");
        stage.setWidth(600);
        stage.setHeight(700);
        stage.show();

        Notifier notifier = new Notifier(NOTE_MANAGER);
        Timer timer = new Timer(true);
        timer.schedule(notifier, (60 - LocalTime.now().getSecond()) * 1000, 60_000);
    }
}
