package com.todolist;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;

public class CreateTask extends VBox {

    public CreateTask() {
        getStyleClass().add("task-card");

        TextField title = new TextField();
        title.setPromptText("Title");
        title.getStyleClass().add("task-main-info");
        DateTimePicker executionDate = new DateTimePicker(LocalDateTime.now());
        PriorityRect priorityRect = new PriorityRect(Task.Priority.LOW);
        TextArea content = new TextArea();
        content.setPromptText("Content");
        content.getStyleClass().add("task-card-content");
        content.setWrapText(true);

        Button saveButton = new Button("Create");
        saveButton.getStyleClass().add("task-card-button");
        saveButton.setOnAction(e -> {
            Task task = new Task(title.getText(), content.getText(), priorityRect.getPriority(), executionDate.getDateTime());
            App.taskManager.add(task);
            App.mainScreen.addTask(task);
            App.scene.setRoot(App.mainScreen);
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> App.scene.setRoot(App.mainScreen));
        cancelButton.getStyleClass().add("task-card-button");
        HBox buttonPane = new HBox(saveButton, cancelButton);
        buttonPane.getStyleClass().add("task-card-button-pane");

        getChildren().addAll(buttonPane, title, new Label("Execution date:"), executionDate, priorityRect, content);
    }
}
