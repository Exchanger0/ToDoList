package com.todolist;

import javafx.collections.FXCollections;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MainScreen extends VBox {
    public final Map<Note, MiniNoteView> noteMiniNoteViewMap = new HashMap<>();
    private final VBox content = new VBox();

    public MainScreen() {
        //Header
        Label title = new Label("ToDoList");
        title.getStyleClass().add("title");

        Button addTaskButton = new Button("+");
        addTaskButton.setOnAction(e -> App.scene.setRoot(new CreateNoteTask()));
        addTaskButton.getStyleClass().add("header-button");

        Button sortButton = new Button("▼");
        sortButton.getStyleClass().add("header-button");
        ContextMenu sortContext = new ContextMenu();
        sortButton.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                Bounds bounds = sortButton.localToScreen(sortButton.getBoundsInLocal());
                sortContext.show(sortButton, bounds.getMaxX(), bounds.getMaxY());
            }
        });
        MenuItem defaultSort = new MenuItem("Default");
        defaultSort.setOnAction(e -> FXCollections.sort(content.getChildren(), (o1, o2) -> {
            if (o1 instanceof MiniNoteView mnv1 && o2 instanceof MiniNoteView mnv2) {
                return mnv1.getNote().getTitle().compareTo(mnv2.getNote().getTitle());
            }
            return 0;
        }));
        Menu nameSort = createSortMenu("Name", (o1, o2) -> {
            if (o1 instanceof MiniNoteView mnv1 && o2 instanceof MiniNoteView mnv2) {
                return mnv1.getNote().getTitle().compareTo(mnv2.getNote().getTitle());
            }
            return 0;
        });
        Menu prioritySort = createSortMenu("Priority", (o1, o2) -> {
            if (o1 instanceof MiniNoteView mnv1 && o2 instanceof MiniNoteView mnv2) {
                return mnv1.getNote().getPriority().compareTo(mnv2.getNote().getPriority());
            }
            return 0;
        });
        Menu executionDateSort = createSortMenu("Execution date", (o1, o2) -> {
            if (o1 instanceof MiniTaskView mtv1 && o2 instanceof MiniTaskView mtv2) {
                return mtv1.getNote().getExecutionDate().compareTo(mtv2.getNote().getExecutionDate());
            }
            return 1;
        });
        sortContext.getItems().addAll(defaultSort, nameSort, prioritySort, executionDateSort);

        HBox header = new HBox(title, addTaskButton, sortButton);
        header.getStyleClass().add("header");

        //Main content
        content.setFillWidth(true);
        ScrollPane contentScroll = new ScrollPane(content);
        contentScroll.setFitToWidth(true);
        contentScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        for (Note note : App.NOTE_MANAGER.getAll()) {
            addNote(note);
        }

        getChildren().addAll(header, contentScroll);
    }

    public void removeNote(Note note) {
        content.getChildren().remove(noteMiniNoteViewMap.get(note));
        noteMiniNoteViewMap.remove(note);
    }

    public void addNote(Note note) {
        MiniNoteView mnv;
        if (note.getClass().equals(Task.class)) {
            mnv = new MiniTaskView((Task) note);
        }else {
            mnv = new MiniNoteView(note);
        }
        noteMiniNoteViewMap.put(note, mnv);
        content.getChildren().add(mnv);
    }

    public void update(Note note) {
        noteMiniNoteViewMap.get(note).update();
    }

    private Menu createSortMenu(String name, Comparator<Node> comparator) {
        Menu menu = new Menu(name);
        MenuItem ascending = new MenuItem("Ascending");
        ascending.setOnAction(e -> FXCollections.sort(content.getChildren(), comparator));
        MenuItem descending = new MenuItem("Descending");
        descending.setOnAction(e -> FXCollections.sort(content.getChildren(), comparator.reversed()));
        menu.getItems().addAll(ascending, descending);
        return menu;
    }
}
