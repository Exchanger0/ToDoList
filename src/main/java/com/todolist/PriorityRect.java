package com.todolist;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class PriorityRect extends VBox {
    private int index;
    private final Label priorityLabel;
    private final Pane priorityRect;

    public PriorityRect(Note.Priority priority) {
        setSpacing(10);
        setFillWidth(true);
        priorityLabel = new Label("Priority: " + priority.name().toLowerCase());

        priorityRect = new Pane();
        index = priority.ordinal() + 1;
        priorityRect.getStyleClass().add("task-card-" + priority.cssClass);
        setOnMouseClicked(e -> {
            priorityRect.getStyleClass().removeLast();
            if (index >= Note.Priority.values().length)
                index = 0;
            priorityRect.getStyleClass().add("task-card-" + Note.Priority.values()[index].cssClass);
            priorityLabel.setText("Priority: " + Note.Priority.values()[index].name().toLowerCase());
            index++;
        });

        getChildren().addAll(priorityLabel, priorityRect);
    }

    public Note.Priority getPriority() {
        return Note.Priority.values()[index-1];
    }

    public void setPriority(Note.Priority priority) {
        priorityLabel.setText("Priority: " + priority.name().toLowerCase());
        priorityRect.getStyleClass().removeLast();
        priorityRect.getStyleClass().add("task-card-" + priority.cssClass);
    }
}
