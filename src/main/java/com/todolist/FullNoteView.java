package com.todolist;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;

public class FullNoteView extends VBox {
    protected final TextField title;
    protected final PriorityRect priorityRect;
    protected final HTMLEditor content;
    protected Button saveButton;
    protected HBox buttonPane;
    protected final Note note;

    public FullNoteView(MiniNoteView miniNoteView, Note note) {
        this.note = note;
        getStyleClass().add("task-card");

        title = new TextField(note.getTitle());
        priorityRect = new PriorityRect(note.getPriority());
        content = new HTMLEditor();
        content.setHtmlText(note.getContent());

        ImageView back = App.createIcon("/icons/back.png");
        Button backButton = new Button();
        backButton.getStyleClass().add("header-button");
        backButton.setOnAction(e -> App.scene.setRoot(App.mainScreen));
        backButton.setGraphic(back);

        ImageView save = App.createIcon("/icons/save.png");
        saveButton = new Button();
        saveButton.setGraphic(save);
        saveButton.getStyleClass().add("header-button");
        saveButton.setOnAction(e -> {
            note.setTitle(title.getText());
            note.setContent(content.getHtmlText());
            note.setPriority(priorityRect.getPriority());
            miniNoteView.update();
            update();
        });

        ImageView delete = App.createIcon("/icons/delete.png");
        Button deleteButton = new Button();
        deleteButton.setGraphic(delete);
        deleteButton.setOnAction(e -> {
            App.scene.setRoot(App.mainScreen);
            App.mainScreen.removeNote(note);
            App.NOTE_MANAGER.remove(note);
        });
        deleteButton.getStyleClass().add("header-button");
        buttonPane = new HBox(backButton, saveButton, deleteButton);
        buttonPane.getStyleClass().add("header");

        getChildren().addAll(buttonPane, title, priorityRect, content);
    }

    public void update() {
        title.setText(note.getTitle());
        priorityRect.setPriority(note.getPriority());
        content.setHtmlText(note.getContent());
    }
}
