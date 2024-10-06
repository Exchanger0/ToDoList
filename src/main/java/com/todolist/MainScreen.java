package com.todolist;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainScreen extends VBox {
    private final Map<Task, MiniTaskView> taskMiniTaskViewMap = new HashMap<>();
    private final VBox content = new VBox();

    public MainScreen() {
        //Header
        Label title = new Label("ToDoList");
        title.getStyleClass().add("title");
        Button addTaskButton = new Button("+");
        addTaskButton.setOnAction(e -> App.scene.setRoot(new CreateTask()));
        addTaskButton.getStyleClass().add("header-button");
        Button filterButton = new Button("▼");
        filterButton.getStyleClass().add("header-button");
        HBox header = new HBox(title, addTaskButton, filterButton);
        header.getStyleClass().add("header");

        //Main content
        content.setFillWidth(true);
        ScrollPane contentScroll = new ScrollPane(content);
        contentScroll.setFitToWidth(true);
        contentScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        for (Task task : App.taskManager.get()) {
            addTask(task);
        }

        getChildren().addAll(header, contentScroll);
    }

    public void removeTask(Task task) {
        content.getChildren().remove(taskMiniTaskViewMap.get(task));
        taskMiniTaskViewMap.remove(task);
    }

    public void addTask(Task task) {
        MiniTaskView mtv = new MiniTaskView(task);
        taskMiniTaskViewMap.put(task, mtv);
        content.getChildren().add(mtv);
    }

    public void update(Task task) {
        taskMiniTaskViewMap.get(task).update();
    }
}
