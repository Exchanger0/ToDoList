package com.todolist;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Timer;

public class App extends Application {
    public static Scene scene;
    public static MainScreen mainScreen;
    public static final NoteManager NOTE_MANAGER = new NoteManager();

    @Override
    public void start(Stage stage) {
        NOTE_MANAGER.setSaveFile(Paths.get("saves/app.sv"));
        NOTE_MANAGER.load();

        mainScreen = new MainScreen();

        scene = new Scene(mainScreen);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setOnCloseRequest(e -> {
            try {
                Path path = NOTE_MANAGER.getSaveFile();
                if (!Files.exists(path)) {
                    if (path.getParent() != null && Files.notExists(path.getParent())) {
                        Files.createDirectory(path.getParent());
                    }
                    Files.createFile(path);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            NOTE_MANAGER.save();
        });
        stage.setScene(scene);
        stage.setTitle("ToDoList");
        stage.setWidth(680);
        stage.setHeight(700);
        stage.show();

        Notifier notifier = new Notifier(NOTE_MANAGER);
        Timer timer = new Timer(true);
        timer.schedule(notifier, (60 - LocalTime.now().getSecond()) * 1000, 60_000);
    }

    public static ImageView createIcon(String path) {
        Image i = new Image(App.class.getResourceAsStream(path));
        return new ImageView(i);
    }
}
