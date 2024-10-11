package com.todolist;

import javafx.scene.control.Label;

import java.time.format.DateTimeFormatter;

public class MiniTaskView extends MiniNoteView{
    protected final Label executionDate;
    protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public MiniTaskView(Task task) {
        super(task);

        title.setStrikethrough(task.isFinalized());
        executionDate = new Label(task.getExecutionDate().format(dtf));
        executionDate.getStyleClass().add("mini-task-details");

        getChildren().add(executionDate);
    }

    public void update() {
        super.update();
        Task t = (Task) note;
        title.setStrikethrough(t.isFinalized());
        executionDate.setText(t.getExecutionDate().format(dtf));
    }

    @Override
    protected FullNoteView getFullView() {
        return new FullTaskView(this, (Task) note);
    }

    @Override
    public Task getNote() {
        return (Task) super.getNote();
    }
}
