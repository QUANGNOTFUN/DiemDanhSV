package com.example.diemdanhsv.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button openAttendance;

    @FXML
    public void openAttendance() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/diemdanhsv/Attendance.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) openAttendance.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Attendance");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorMessage("Error", "Failed to load Attendance view.");
        }
    }

    private void showErrorMessage(String title, String message) {
        // Implement your error message display logic here
    }
}
