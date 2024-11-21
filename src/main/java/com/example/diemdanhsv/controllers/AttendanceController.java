package com.example.diemdanhsv.controllers;

import com.example.diemdanhsv.models.Student;
import com.example.diemdanhsv.repository.AttendanceRepository;
import com.example.diemdanhsv.repository.CourseRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField userIdField;
    @FXML
    private ComboBox<String> courseComboBox;
    @FXML
    private TableView<Student> attendanceTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, Integer> userIdColumn;
    @FXML
    private TableColumn<Student, Boolean> statusColumn;
    @FXML
    private TableColumn<Student, Boolean> genderColumn;

    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        statusColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        statusColumn.setCellFactory(CheckBoxTableCell.forTableColumn(statusColumn));

        attendanceTable.setItems(studentList);
        loadCourses();
    }

    private void loadCourses() {
        CourseRepository queryHandle = new CourseRepository();
        ObservableList<String> courses = queryHandle.getCourses();
        courseComboBox.setItems(courses);
    }

    public void add(ActionEvent e) {
        try {
            Student newStudent = new Student();
            newStudent.setId(Integer.parseInt(idField.getText()));
            newStudent.setName(nameField.getText());
            newStudent.setEmail(emailField.getText());
            newStudent.setUserId(Integer.parseInt(userIdField.getText()));

            if (maleRadioButton.isSelected()) {
                newStudent.setGender(true);
            } else if (femaleRadioButton.isSelected()) {
                newStudent.setGender(false);
            }

            studentList.add(newStudent);

            idField.clear();
            nameField.clear();
            emailField.clear();
            userIdField.clear();
        } catch (NumberFormatException ex) {
            showAlert("Input Error", "Please enter valid data!");
        }
    }

    public void remove(ActionEvent e) {
        Student selectedStudent = attendanceTable.getSelectionModel().getSelectedItem();

        if (selectedStudent == null) {
            showAlert("No Selection", "Please select a student to remove.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText("Delete Student");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            studentList.remove(selectedStudent);
            showAlert("Success", "Student deleted successfully.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
