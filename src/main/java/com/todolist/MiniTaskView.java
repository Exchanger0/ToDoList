package com.todolist;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;

public class MiniTaskView extends VBox {
    private FullTaskView fullTaskView;
    private final Text title;
    private final Label executionDate;
    private final Task task;

    public MiniTaskView(Task task) {
        this.task = task;
        setFillWidth(true);
        getStyleClass().add("mini-task-card");
        getStyleClass().add(task.getPriority().cssClass);

        setOnMouseClicked(e -> {
            if (fullTaskView == null)
                fullTaskView = new FullTaskView(this, task);
            else fullTaskView.update();
            App.scene.setRoot(fullTaskView);
        });

        title = new Text(task.getTitle());
        title.setStrikethrough(task.isFinalized());
        title.getStyleClass().add("mini-task-title");
        executionDate = new Label(task.getExecutionDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        executionDate.getStyleClass().add("mini-task-details");

        getChildren().addAll(title, executionDate);
    }

    public void update() {
        title.setText(task.getTitle());
        title.setStrikethrough(task.isFinalized());
        executionDate.setText(task.getExecutionDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        getStyleClass().set(getStyleClass().size() - 1, task.getPriority().cssClass);
    }
}
