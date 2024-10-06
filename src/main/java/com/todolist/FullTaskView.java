package com.todolist;

import javafx.scene.control.Label;

public class FullTaskView extends FullNoteView{
    private final DateTimePicker executionDate;
    private final Label finalized;

    public FullTaskView(MiniTaskView miniNoteView, Task task) {
        super(miniNoteView, task);

        executionDate = new DateTimePicker(task.getExecutionDate());
        finalized = new Label("Finalized: " + (task.isFinalized() ? "yes" : "no"));
        finalized.setOnMouseClicked(e -> {
            if (finalized.getText().endsWith("yes")) {
                finalized.setText(finalized.getText().replaceAll("yes", "no"));
            } else {
                finalized.setText(finalized.getText().replaceAll("no", "yes"));
            }
        });

        saveButton.setOnAction(e -> {
            task.setExecutionDate(executionDate.getDateTime());
            task.setTitle(title.getText());
            task.setFinalized(finalized.getText().endsWith("yes"));
            task.setContent(content.getText());
            task.setPriority(priorityRect.getPriority());
            miniNoteView.update();
            update();
        });

        getChildren().clear();
        getChildren().addAll(buttonPane, title, new Label("Execution date:"), executionDate, finalized, priorityRect, content);
    }


    @Override
    public void update() {
        super.update();
        Task t = (Task) note;
        executionDate.setDateTime(t.getExecutionDate());
        finalized.setText("Finalized: " + (t.isFinalized() ? "yes" : "no"));
    }
}
