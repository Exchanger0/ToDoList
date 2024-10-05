package com.todolist;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class PriorityRect extends VBox {
    private int index;
    private final Label priorityLabel;
    private final Pane priorityRect;

    public PriorityRect(Task.Priority priority) {
        setSpacing(10);
        setFillWidth(true);
        priorityLabel = new Label("Priority: " + priority.name().toLowerCase());

        priorityRect = new Pane();
        index = priority.ordinal() + 1;
        priorityRect.getStyleClass().add("task-card-" + priority.cssClass);
        setOnMouseClicked(e -> {
            priorityRect.getStyleClass().removeLast();
            if (index >= Task.Priority.values().length)
                index = 0;
            priorityRect.getStyleClass().add("task-card-" + Task.Priority.values()[index].cssClass);
            priorityLabel.setText("Priority: " + Task.Priority.values()[index].name().toLowerCase());
            index++;
        });

        getChildren().addAll(priorityLabel, priorityRect);
    }

    public Task.Priority getPriority() {
        return Task.Priority.values()[index-1];
    }

    public void setPriority(Task.Priority priority) {
        priorityLabel.setText("Priority: " + priority.name().toLowerCase());
        priorityRect.getStyleClass().removeLast();
        priorityRect.getStyleClass().add("task-card-" + priority.cssClass);
    }
}
