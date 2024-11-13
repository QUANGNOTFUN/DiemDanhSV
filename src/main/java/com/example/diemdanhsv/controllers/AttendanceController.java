package com.example.diemdanhsv.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;

public class AttendanceController {

    @FXML
    private TableView<?> attendanceTable;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    public void markAttendance() {
        // Logic để đánh dấu điểm danh hoặc thực hiện hành động khác
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attendance");
        alert.setContentText("Attendance has been marked.");
        alert.showAndWait();
    }
}
