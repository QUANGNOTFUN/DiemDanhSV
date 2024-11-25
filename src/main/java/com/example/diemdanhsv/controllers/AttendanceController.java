package com.example.diemdanhsv.controllers;

import com.example.diemdanhsv.models.Attendance;
import com.example.diemdanhsv.models.Student;
import com.example.diemdanhsv.models.User;
import com.example.diemdanhsv.repository.AttendanceRepository;
import com.example.diemdanhsv.repository.CourseRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;

import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;

    @FXML
    public TextField idField;

    @FXML
    private FlowPane courseButtonContainer;
    @FXML
    private TableView<Student> attendanceTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> genderColumn;
    @FXML
    private TableColumn<Student, LocalDateTime> dateColumn;
    @FXML
    private TableColumn<Student, String> statusColumn;

    private ObservableList<Student> studentList;

    private AttendanceRepository attendanceRepository;

    private User currentUser;

    @FXML
    private ToggleGroup genderGroup;

    private int currentCourseId;

    @FXML
    private ComboBox<String> courseComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        attendanceRepository = new AttendanceRepository();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        statusColumn.setCellFactory(param -> new TableCell<>() {
            private final ComboBox<String> comboBox = new ComboBox<>(
                    FXCollections.observableArrayList("present", "late", "absent")); // Các giá trị trạng thái
            {
                comboBox.setOnAction(event -> {
                    Student data = getTableView().getItems().get(getIndex());
                    String newStatus = comboBox.getValue();
                    data.setStatus(newStatus); // Cập nhật status trong đối tượng `Student`
                    // Lưu cập nhật vào cơ sở dữ liệu
                    boolean isUpdated = attendanceRepository.updateStatus(data.getId(), newStatus, currentCourseId);
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getIndex() >= getTableView().getItems().size()) {
                    setGraphic(null);
                } else {
                    Student data = getTableView().getItems().get(getIndex());
                    comboBox.setValue(data.getStatus()); // Lấy giá trị status từ đối tượng `Student`
                    setGraphic(comboBox);
                }
            }
        });

        studentList = FXCollections.observableArrayList(); // Khởi tạo danh sách rỗng
        attendanceTable.setItems(studentList); // Gắn danh sách rỗng vào bảng

        loadCourses(); // Chỉ tải các nút khóa học

//        loadCoursesIntoComboBox();
    }

//    private void loadCoursesIntoComboBox() {
//        CourseRepository courseRepository = new CourseRepository();
//        ObservableList<String> courses = courseRepository.getCourses();
//        courseComboBox.setItems(courses);
//    }


    public void add(ActionEvent e) {
        try {
            // Lấy ID sinh viên từ trường nhập liệu
            int studentId = Integer.parseInt(idField.getText());


            int courseId = currentCourseId; // Lấy courseId hiện tại
            LocalDate date = LocalDate.now(); // Ngày hiện tại
            String status = "absent"; // Trạng thái mặc định

            // Thêm đối tượng Attendance vào cơ sở dữ liệu
            boolean isAdded = attendanceRepository.addAttendance(studentId, currentCourseId,date,status);

            if (isAdded) {
                // Nếu thêm thành công, cập nhật danh sách sinh viên
                showAlert("Success", "Student added to attendance successfully.");
            } else {
                showAlert("Error", "Failed to add student to attendance.");
            }

            // Xóa trường nhập liệu
            idField.clear();
        } catch (NumberFormatException ex) {
            showAlert("Input Error", "Please enter a valid student ID!");
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
            boolean isDeleted = attendanceRepository.deleteStudent(selectedStudent.getId(), currentCourseId);

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

        courseButtonContainer.getChildren().clear(); // Xóa các nút cũ.

        for (String course : courses) {
            Button courseButton = new Button(course);

            // Cài đặt nút để hiển thị nội dung đầy đủ.
            courseButton.setStyle("-fx-font-size: 14px; -fx-padding: 5;");
            courseButton.setMaxWidth(Double.MAX_VALUE); // Tự động giãn theo nội dung.

            // Sự kiện khi nhấn nút.
            courseButton.setOnAction(event -> handleCourseSelection(course));

            // Thêm nút vào container.
            courseButtonContainer.getChildren().add(courseButton);
        }

        if (courses.isEmpty()) {
            showAlert("No Data", "No courses found in the database.");
        }
    }


    private void handleCourseSelection(String course) {
        CourseRepository courseRepository = new CourseRepository();
        currentCourseId = courseRepository.getCourseIdByName(course);

        ObservableList<Attendance> attendanceList = attendanceRepository.getAttendanceByCourseId(currentCourseId);

        studentList.clear(); // Xóa danh sách cũ trước khi tải mới

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

    public void setCurrentUser(User user) {
        this.currentUser = user;
        if (user != null) {
            switch (user.getRole()) {
                case "ADMIN":
                    enableAllControls();
                    break;
                case "TEACHER":
                    enableTeacherControls();
                    break;
                case "STUDENT":
                    enableStudentControls();
                    break;
            }
        }
    }

    private void enableAllControls() {
        addButton.setDisable(false);
        removeButton.setDisable(false);
        courseButtonContainer.setDisable(false);
    }

    private void enableTeacherControls() {
        addButton.setDisable(false);
        removeButton.setDisable(false);
        courseButtonContainer.setDisable(false);
    }

    private void enableStudentControls() {
        addButton.setDisable(false);
        removeButton.setDisable(false);
        courseButtonContainer.setDisable(false);
    }
}
