package com.todolist;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class MainScreen extends VBox {
    public final Map<Note, MiniNoteView> noteMiniNoteViewMap = new HashMap<>();
    private final VBox content = new VBox();

    public MainScreen() {
        //Header
        Label title = new Label("ToDoList");
        title.getStyleClass().add("title");
        Button addTaskButton = new Button("+");
        addTaskButton.setOnAction(e -> App.scene.setRoot(new CreateNoteTask()));
        addTaskButton.getStyleClass().add("header-button");
        Button filterButton = new Button("▼");
        filterButton.getStyleClass().add("header-button");
        HBox header = new HBox(title, addTaskButton, filterButton);
        header.getStyleClass().add("header");

        //Main content
        content.setFillWidth(true);
        ScrollPane contentScroll = new ScrollPane(content);
        contentScroll.setFitToWidth(true);
        contentScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        for (Note note : App.NOTE_MANAGER.getAll()) {
            addNote(note);
        }

        getChildren().addAll(header, contentScroll);
    }

    public void removeNote(Note note) {
        content.getChildren().remove(noteMiniNoteViewMap.get(note));
        noteMiniNoteViewMap.remove(note);
    }

    public void addNote(Note note) {
        MiniNoteView mnv;
        if (note.getClass().equals(Task.class)) {
            mnv = new MiniTaskView((Task) note);
        }else {
            mnv = new MiniNoteView(note);
        }
        noteMiniNoteViewMap.put(note, mnv);
        content.getChildren().add(mnv);
    }

    public void update(Note note) {
        noteMiniNoteViewMap.get(note).update();
    }
}
