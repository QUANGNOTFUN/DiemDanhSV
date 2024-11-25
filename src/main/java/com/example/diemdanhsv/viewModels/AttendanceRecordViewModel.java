package com.example.diemdanhsv.viewModels;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

import java.time.LocalDate;

public class AttendanceRecordViewModel {
    private final IntegerProperty session = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private final StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<Button> button = new SimpleObjectProperty<>();

    // Constructor
    public AttendanceRecordViewModel() {}

    // Getters and setters
    public int getSession() {
        return session.get();
    }

    public void setSession(int session) {
        this.session.set(session);
    }

    public IntegerProperty sessionProperty() {
        return session;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }

    public Button getButton() {
        return button.get();
    }

    public void setButton(Button button) {
        this.button.set(button);
    }

    public ObjectProperty<Button> buttonProperty() {
        return button;
    }
}
