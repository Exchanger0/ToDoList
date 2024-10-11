package com.todolist;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MiniNoteView extends VBox {
    protected FullNoteView fullNoteView;
    protected final Text title;
    protected final Label miniContent;
    protected final Note note;

    public MiniNoteView(Note note) {
        this.note = note;
        setFillWidth(true);
        getStyleClass().add("mini-task-card");
        getStyleClass().add(note.getPriority().cssClass);

        setOnMouseClicked(e -> {
            if (fullNoteView == null)
                fullNoteView = getFullView();
            else fullNoteView.update();
            App.scene.setRoot(fullNoteView);
        });

        title = new Text(note.getTitle());
        title.getStyleClass().add("mini-task-title");
        miniContent = new Label();
        miniContent.getStyleClass().add("mini-task-details");
        setMiniContentText();

        getChildren().addAll(title, miniContent);
    }

    public void update() {
        title.setText(note.getTitle());
        setMiniContentText();
        getStyleClass().set(getStyleClass().size() - 1, note.getPriority().cssClass);
    }

    private void setMiniContentText() {
        String content = note.getPlainContent();
        int idx = content.indexOf("\n");
        if (idx > 0)
            content = content.substring(0, idx);
        miniContent.setText(content);
    }

    protected FullNoteView getFullView() {
        return new FullNoteView(this, note);
    }

    public Note getNote() {
        return note;
    }
}
