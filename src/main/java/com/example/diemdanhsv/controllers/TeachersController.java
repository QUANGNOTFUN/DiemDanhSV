package com.example.diemdanhsv.controllers;

import com.example.diemdanhsv.models.Attendance;
import com.example.diemdanhsv.models.Student;
import com.example.diemdanhsv.models.User;
import com.example.diemdanhsv.repository.AttendanceRepository;
import com.example.diemdanhsv.repository.CourseRepository;
import com.example.diemdanhsv.viewModels.TeachersViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class TeachersController {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        TeachersController.user = user;
    }

    private final TeachersViewModel _teacherVM = new TeachersViewModel();

    // nút chức năng
    @FXML
    private VBox vboxAddCourse;
    @FXML
    private VBox vboxAddStudent;

    // Chức năng thêm course
    @FXML
    public TextField nameCourseInput;
    @FXML
    public DatePicker  timeStart;
    @FXML
    public DatePicker  timeEnd;
    @FXML
    public TextField semester;
    @FXML
    public Button addCourseButton;

    //
    @FXML
    public ComboBox<String> sessionComboBox;
    @FXML
    private Label nameTecher;
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
    private TableColumn<Student, LocalDate> dateColumn;
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

    @FXML
    private Label selectedCourseLabel;

    @FXML
    public void initialize(User userId) throws SQLException {
        try {
            setUser(userId);

            loadAll();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAll() throws SQLException {
        // load thông tin giáo viên
        loadInFoTecherLogin(getUser().getId());

        // cấu hình cột trong table view
        configureAttendanceColumn();

        loadCourses(); // Chỉ tải các nút khóa học

        enableVBoxContainer();

        handleAddCourseButton();
    }

    public void loadInFoTecherLogin(int userId) throws SQLException {
        _teacherVM.getInFoTeacherVM(userId);
        System.out.println(userId);
        nameTecher.setText("Tên: " + _teacherVM.getName());
    }

    // cấu hình cột của table view
    public void configureAttendanceColumn() {
        attendanceRepository = new AttendanceRepository();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Load available sessions from the database
        ObservableList<String> availableSessions = attendanceRepository.getAvailableSessions();
        sessionComboBox.getItems().addAll(availableSessions);
        sessionComboBox.getSelectionModel().selectFirst(); // Select the first session by default

        statusColumn.setCellFactory(param -> new TableCell<>() {
            private final ComboBox<String> comboBox = new ComboBox<>(
                    FXCollections.observableArrayList("present", "late", "absent")); // Các giá trị trạng thái
            {
                comboBox.setOnAction(event -> {
                    Student data = getTableView().getItems().get(getIndex());
                    String newStatus = comboBox.getValue();
                    data.setStatus(newStatus); // Cập nhật status trong đối tượng Student
                    // Lưu cập nhật vào cơ sở dữ liệu
                    boolean isUpdated = attendanceRepository.updateStatus(data.getId(), newStatus, currentCourseId, getSessionNumber((String) sessionComboBox.getSelectionModel().getSelectedItem()));
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getIndex() >= getTableView().getItems().size()) {
                    setGraphic(null);
                } else {
                    Student data = getTableView().getItems().get(getIndex());
                    comboBox.setValue(data.getStatus()); // Lấy giá trị status từ đối tượng Student
                    setGraphic(comboBox);
                }
            }
        });

        studentList = FXCollections.observableArrayList(); // Khởi tạo danh sách rỗng
        attendanceTable.setItems(studentList); // Gắn danh sách rỗng vào bảng
    }

    public void add(ActionEvent e) {
        try {
            // Get student ID from input field
            int studentId = Integer.parseInt(idField.getText());

            int courseId = currentCourseId; // Get current course ID
            LocalDate date = LocalDate.now(); // Current date
            String status = "absent"; // Default status

            // Get the selected session from the ComboBox
            String selectedSession = (String) sessionComboBox.getSelectionModel().getSelectedItem();
            int sessionNumber = getSessionNumber(selectedSession); // Convert session string to number

            // Add attendance to the database
            boolean isAdded = attendanceRepository.addAttendance(studentId, courseId, date, status);

            if (isAdded) {
                loadAll();
                showAlert("Success", "Student added to attendance successfully.");
            } else {
                showAlert("Error", "Failed to add student to attendance.");
            }

            // Clear input field
            idField.clear();
        } catch (NumberFormatException ex) {
            showAlert("Input Error", "Please enter a valid student ID!");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void remove(ActionEvent e) throws SQLException {
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
                loadAll();
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
            courseButton.setOnAction(event -> {
                handleCourseSelection(course);
                loadSessions(); // Load sessions when a course is selected
            });

            // Thêm nút vào container.
            courseButtonContainer.getChildren().add(courseButton);
        }

        if (courses.isEmpty()) {
            showAlert("No Data", "No courses found in the database.");
        }
    }

    private void loadSessions() {
        // Load available sessions from the database based on the current course
        ObservableList<String> availableSessions = attendanceRepository.getAvailableSessions();
        sessionComboBox.getItems().clear(); // Clear existing sessions

        // Add all available sessions to the ComboBox
        sessionComboBox.getItems().addAll(availableSessions); // Ensure all sessions are added

        if (!availableSessions.isEmpty()) {
            sessionComboBox.getSelectionModel().selectFirst(); // Select the first session by default
        } else {
            // Handle case where no sessions are available
            showAlert("No Sessions", "No sessions available for the selected course.");
        }

        sessionComboBox.setOnAction(event -> {
            if (sessionComboBox.getSelectionModel().getSelectedItem() != null) {
                loadAttendanceTable(); // Load attendance when a session is selected
            }
        });
    }

    private void loadAttendanceTable() {
        // Get the selected session from the ComboBox
        String selectedSession = (String) sessionComboBox.getSelectionModel().getSelectedItem();

        if (selectedSession != null) { // Check if selectedSession is not null
            int sessionNumber = getSessionNumber(selectedSession); // Convert session string to number

            ObservableList<Attendance> attendanceList = attendanceRepository.getAttendanceByCourseId(currentCourseId, sessionNumber);

            studentList.clear(); // Xóa danh sách cũ trước khi tải mới

            for (Attendance attendance : attendanceList) {
                Student student = new Student(
                        attendance.getStudentId(),
                        attendance.getStudentName(),
                        attendance.getStudentId(),
                        attendance.getGender(),
                        attendance.getStatus(),
                        attendance.getDate()
                        );
                studentList.add(student);
            }
        } else {
            showAlert("No Session Selected", "Please select a session to load attendance.");
        }
    }

    private void handleCourseSelection(String course) {
        CourseRepository courseRepository = new CourseRepository();
        currentCourseId = courseRepository.getCourseIdByName(course);

        // Update the selected course label
        selectedCourseLabel.setText(course); // Set the selected course name

        // Load sessions for the selected course
        loadSessions();
    }

    private int getSessionNumber(String sessionString) {
        // Get all session numbers from the database
        ObservableList<Integer> sessionNumbers = attendanceRepository.getSessionNumbers();

        // Check if the sessionString is valid and matches any session number
        for (Integer sessionNumber : sessionNumbers) {
            if (sessionString.equals("Buổi " + sessionNumber)) {
                return sessionNumber; // Return the matching session number
            }
        }
        return -1; // Return -1 if no match is found
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // hiển thị chức năng theo role
    public boolean checkRoleUserLogin() {
        String role = getUser().getRole();
        System.out.println(role);
        return role.equals("ADMIN");
    }

    private void enableVBoxContainer() {
        if (checkRoleUserLogin()) {
            vboxAddCourse.setVisible(true);
            vboxAddStudent.setVisible(true);
            return;
        }
        vboxAddStudent.setVisible(true);
    }

    // chức năng thêm course
    private void handleAddCourseButton() {
        addCourseButton.setOnAction(e -> {
            try {
                // Lấy giá trị từ DatePicker
                LocalDate startDate = timeStart.getValue();
                LocalDate endDate = timeEnd.getValue();

                // Lấy các giá trị khác
                String nameCourse = nameCourseInput.getText();
                String semesterValue = semester.getText();
                System.out.println(nameCourse);

                // Kiểm tra xem các trường có hợp lệ không
                if (startDate == null || endDate == null || nameCourse.isEmpty() || semesterValue.isEmpty()) {
                    showAlert("Lỗi", "Vui lòng điền đầy đủ thông tin!");
                    return;
                }

                // Xử lý thêm course
                _teacherVM.addCourseToDatabaseVM(nameCourse, startDate, endDate, semesterValue);

                loadAll();

                showAlert("Thành công", "Đã thêm khóa học thành công!");
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Lỗi", "Không thể thêm khóa học: " + ex.getMessage());
            }
        });

    }
    @FXML
private void exportAttendance(ActionEvent event) {
    // Logic to export the attendance list
    try {
        // Create a file chooser to select the export location
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Attendance List");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

        if (file != null) {
            // Retrieve the attendance data
            ObservableList<Attendance> attendanceList = attendanceRepository.getAttendanceByCourseId(currentCourseId, getSessionNumber((String) sessionComboBox.getSelectionModel().getSelectedItem()));

            // Write the data to the selected file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("Student ID,Student Name,Gender,Status,Date\n");
                for (Attendance attendance : attendanceList) {
                    writer.write(attendance.getStudentId() + "," +
                                 attendance.getStudentName() + "," +
                                 attendance.getGender() + "," +
                                 attendance.getStatus() + "," +
                                 attendance.getDate() + "\n");
                }
                showAlert("Success", "Attendance list exported successfully.");
            } catch (IOException e) {
                showAlert("Error", "An error occurred while exporting the attendance list: " + e.getMessage());
            }
        }
    } catch (Exception e) {
        showAlert("Error", "An error occurred: " + e.getMessage());
    }
}
}
