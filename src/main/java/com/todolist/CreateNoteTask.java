package com.todolist;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;

import java.time.LocalDateTime;

public class CreateNoteTask extends VBox {

    public CreateNoteTask() {
        getStyleClass().add("task-card");

        TextField title = new TextField();
        title.setPromptText("Title");
        title.getStyleClass().add("task-main-info");
        PriorityRect priorityRect = new PriorityRect(Note.Priority.LOW);
        HTMLEditor content = new HTMLEditor();

        Label exLabel = new Label("Execution date:");
        DateTimePicker executionDate = new DateTimePicker(LocalDateTime.now());

        CheckBox isTask = new CheckBox("Task");
        isTask.selectedProperty().addListener((o, oldVal, newVal) -> {
            if (newVal) {
                getChildren().add(3, exLabel);
                getChildren().add(4, executionDate);
            } else {
                getChildren().remove(exLabel);
                getChildren().remove(executionDate);
            }
        });

        Button saveButton = new Button("Create");
        saveButton.getStyleClass().add("task-card-button");
        saveButton.setOnAction(e -> {
            Note note;
            if (isTask.isSelected())
                note = new Task(title.getText(), content.getHtmlText(), priorityRect.getPriority(), executionDate.getDateTime());
            else
                note = new Note(title.getText(), content.getHtmlText(), priorityRect.getPriority());
            App.NOTE_MANAGER.add(note);
            App.mainScreen.addNote(note);
            App.scene.setRoot(App.mainScreen);
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> App.scene.setRoot(App.mainScreen));
        cancelButton.getStyleClass().add("task-card-button");
        HBox buttonPane = new HBox(saveButton, cancelButton);
        buttonPane.getStyleClass().add("task-card-button-pane");

        getChildren().addAll(buttonPane, title, isTask, priorityRect, content);
    }
}
