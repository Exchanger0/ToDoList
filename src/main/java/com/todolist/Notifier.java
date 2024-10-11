package com.todolist;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;

import java.time.LocalDateTime;
import java.util.*;

public class Notifier extends TimerTask {
    private final NoteManager noteManager;

    public Notifier(NoteManager noteManager) {
        this.noteManager = noteManager;
    }

    @Override
    public void run() {
        noteManager.getTasks()
                .parallelStream()
                .filter(task -> !task.isFinalized() && le(task.getExecutionDate(), LocalDateTime.now()))
                .forEach(t -> {
                    t.setFinalized(true);
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(t.getTitle());
                        WebView webView = new WebView();
                        webView.setDisable(true);
                        WebEngine webEngine = webView.getEngine();
                        webEngine.loadContent(t.getContent());
                        alert.getDialogPane().setMaxSize(400, 400);
                        alert.getDialogPane().setContent(webView);
                        alert.initModality(Modality.NONE);
                        alert.show();
                        App.mainScreen.update(t);
                    });
                });
    }

    private boolean le(LocalDateTime ldt1, LocalDateTime ldt2) {
        return (ldt1.toLocalDate().equals(ldt2.toLocalDate()) || ldt1.toLocalDate().isBefore(ldt2.toLocalDate())) &&
                ldt1.getHour() <= ldt2.getHour() && ldt1.getMinute() <= ldt2.getMinute();
    }
}
