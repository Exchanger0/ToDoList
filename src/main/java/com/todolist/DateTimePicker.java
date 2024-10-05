package com.todolist;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimePicker extends VBox {
    private final DatePicker datePicker;
    private final Spinner<Integer> hours;
    private final Spinner<Integer> minutes;

    public DateTimePicker(LocalDateTime localDateTime) {
        setSpacing(10);

        datePicker = new DatePicker(localDateTime.toLocalDate());

        hours = new Spinner<>(0, 23, localDateTime.getHour());
        hours.setEditable(true);
        hours.setPrefWidth(70);
        minutes = new Spinner<>(0, 59, localDateTime.getMinute());
        minutes.setEditable(true);
        minutes.setPrefWidth(70);

        HBox hoursMinutes = new HBox(hours, new Label(":"), minutes);
        hoursMinutes.setSpacing(5);

        getChildren().addAll(datePicker, hoursMinutes);
    }

    public void setDateTime(LocalDateTime localDateTime) {
        datePicker.setValue(localDateTime.toLocalDate());
        hours.getValueFactory().setValue(localDateTime.getHour());
        minutes.getValueFactory().setValue(localDateTime.getMinute());
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.of(datePicker.getValue(), LocalTime.of(hours.getValue(), minutes.getValue()));
    }
}
