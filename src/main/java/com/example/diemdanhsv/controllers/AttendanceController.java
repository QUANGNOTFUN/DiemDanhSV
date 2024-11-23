package com.example.diemdanhsv.controllers;

import com.example.diemdanhsv.models.Attendance;
import com.example.diemdanhsv.models.Student;
import com.example.diemdanhsv.repository.AttendanceRepository;
import com.example.diemdanhsv.repository.CourseRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable {
    @FXML
    public TextField idField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField userIdField;
    @FXML
    public TextField genderField;

    @FXML
    private HBox courseButtonContainer;
    @FXML
    private TableView<Student> attendanceTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> genderColumn;
    @FXML
    private TableColumn<Student, String> statusColumn;

    private ObservableList<Student> studentList;

    private AttendanceRepository attendanceRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        attendanceRepository = new AttendanceRepository();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        studentList = FXCollections.observableArrayList();
        attendanceTable.setItems(studentList);

        loadCourses();
        loadAttendanceData();
    }

    private void loadAttendanceData() {
        ObservableList<Attendance> attendanceList = attendanceRepository.getAttendanceList();
        for (Attendance attendance : attendanceList) {
            Student student = new Student(
                    attendance.getStudentId(),
                    attendance.getStudentName(),
                    attendance.getStudentId(), // Giá trị userId
                    attendance.getGender(),
                    attendance.getStatus()
            );
            studentList.add(student);
        }
    }

    public void add(ActionEvent e) {
        try {
            Student newStudent = new Student();
            newStudent.setId(Integer.parseInt(idField.getText()));
            newStudent.setName(nameField.getText());
            newStudent.setUserId(Integer.parseInt(userIdField.getText()));
            newStudent.setGender(genderField.getText());

            studentList.add(newStudent);

            idField.clear();
            nameField.clear();
            userIdField.clear();
            genderField.clear();
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
            boolean isDeleted = attendanceRepository.deleteStudent(selectedStudent.getId());

            if (isDeleted) {
                studentList.remove(selectedStudent);
                showAlert("Success", "Student deleted successfully.");
            } else {
                showAlert("Error", "Failed to delete student from the database.");
            }
        }
    }

    private void loadCourses() {
        CourseRepository queryHandle = new CourseRepository();
        ObservableList<String> courses = queryHandle.getCourses();

        courseButtonContainer.getChildren().clear();

        for (String course : courses) {
            Button courseButton = new Button(course);
            courseButton.setPrefWidth(300);
            courseButton.setOnAction(event -> handleCourseSelection(course));
            courseButtonContainer.getChildren().add(courseButton);
        }

        if (courses.isEmpty()) {
            showAlert("No Data", "No courses found in the database.");
        }
    }

    private void handleCourseSelection(String course) {
        CourseRepository courseRepository = new CourseRepository();
        int courseId = courseRepository.getCourseIdByName(course);

        ObservableList<Attendance> attendanceList = attendanceRepository.getAttendanceByCourseId(courseId);

        studentList.clear();

        for (Attendance attendance : attendanceList) {
            Student student = new Student(
                    attendance.getStudentId(),
                    attendance.getStudentName(),
                    attendance.getStudentId(),
                    attendance.getGender(),
                    attendance.getStatus()
            );
            studentList.add(student);
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
