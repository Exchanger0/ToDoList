package com.todolist;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FullTaskView extends VBox {
    private final TextField title;
    private final DateTimePicker executionDate;
    private final Label finalized;
    private final PriorityRect priorityRect;
    private final TextArea content;
    private final Task task;

    public FullTaskView(MiniTaskView miniTaskView, Task task) {
        this.task = task;
        getStyleClass().add("task-card");

        title = new TextField(task.getTitle());
        executionDate = new DateTimePicker(task.getExecutionDate());
        finalized = new Label("Finalized: " + (task.isFinalized() ? "yes" : "no"));
        finalized.setOnMouseClicked(e -> {
            if (finalized.getText().endsWith("yes")) {
                finalized.setText(finalized.getText().replaceAll("yes", "no"));
            } else {
                finalized.setText(finalized.getText().replaceAll("no", "yes"));
            }
        });
        priorityRect = new PriorityRect(task.getPriority());
        content = new TextArea(task.getContent());
        content.getStyleClass().add("task-card-content");
        content.setWrapText(true);

        Button backButton = new Button("←");
        backButton.getStyleClass().add("task-card-button");
        backButton.setOnAction(e -> App.scene.setRoot(App.mainScreen));
        Button saveButton = new Button("Save changes");
        saveButton.getStyleClass().add("task-card-button");
        saveButton.setOnAction(e -> {
            task.setExecutionDate(executionDate.getDateTime());
            task.setTitle(title.getText());
            task.setFinalized(finalized.getText().endsWith("yes"));
            task.setContent(content.getText());
            task.setPriority(priorityRect.getPriority());
            miniTaskView.update();
            update();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            App.scene.setRoot(App.mainScreen);
            App.mainScreen.removeTask(task);
            App.taskManager.remove(task);
        });
        deleteButton.getStyleClass().add("task-card-button");
        HBox buttonPane = new HBox(backButton, saveButton, deleteButton);
        buttonPane.getStyleClass().add("task-card-button-pane");

        getChildren().addAll(buttonPane, title, new Label("Execution date:"), executionDate, finalized, priorityRect, content);
    }

    public void update() {
        title.setText(task.getTitle());
        executionDate.setDateTime(task.getExecutionDate());
        finalized.setText("Finalized: " + (task.isFinalized() ? "yes" : "no"));
        priorityRect.setPriority(task.getPriority());
        content.setText(task.getContent());
    }
}
