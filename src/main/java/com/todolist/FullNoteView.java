package com.todolist;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FullNoteView extends VBox {
    protected final TextField title;
    protected final PriorityRect priorityRect;
    protected final TextArea content;
    protected Button saveButton;
    protected HBox buttonPane;
    protected final Note note;

    public FullNoteView(MiniNoteView miniNoteView, Note note) {
        this.note = note;
        getStyleClass().add("task-card");

        title = new TextField(note.getTitle());
        priorityRect = new PriorityRect(note.getPriority());
        content = new TextArea(note.getContent());
        content.getStyleClass().add("task-card-content");
        content.setWrapText(true);

        Button backButton = new Button("←");
        backButton.getStyleClass().add("task-card-button");
        backButton.setOnAction(e -> App.scene.setRoot(App.mainScreen));
        saveButton = new Button("Save changes");
        saveButton.getStyleClass().add("task-card-button");
        saveButton.setOnAction(e -> {
            note.setTitle(title.getText());
            note.setContent(content.getText());
            note.setPriority(priorityRect.getPriority());
            miniNoteView.update();
            update();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            App.scene.setRoot(App.mainScreen);
            App.mainScreen.removeNote(note);
            App.NOTE_MANAGER.remove(note);
        });
        deleteButton.getStyleClass().add("task-card-button");
        buttonPane = new HBox(backButton, saveButton, deleteButton);
        buttonPane.getStyleClass().add("task-card-button-pane");

        getChildren().addAll(buttonPane, title, priorityRect, content);
    }

    public void update() {
        title.setText(note.getTitle());
        priorityRect.setPriority(note.getPriority());
        content.setText(note.getContent());
    }
}
