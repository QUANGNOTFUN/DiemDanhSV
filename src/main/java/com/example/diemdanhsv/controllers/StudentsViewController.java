package com.example.diemdanhsv.controllers;

import com.example.diemdanhsv.databaseConnect.DatabaseConnection;
import com.example.diemdanhsv.models.Course;
import com.example.diemdanhsv.viewModels.AttendanceRecordViewModel;
import com.example.diemdanhsv.viewModels.StudentsViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StudentsViewController {
    private static final StudentsViewModel studentsVM = new StudentsViewModel();

    // Khung bên trái
    @FXML
    private ToggleButton hk1ToggleButton;
    @FXML
    private ToggleButton hk2ToggleButton;

    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private ComboBox<String> listCourse;

    // khung bên phải
    @FXML
    private Label titleSubject;
    @FXML
    private TableView<AttendanceRecordViewModel> attendanceTable;
    @FXML
    private TableColumn<AttendanceRecordViewModel, Integer> sessionColumn;
    @FXML
    private TableColumn<AttendanceRecordViewModel, LocalDate> dateColumn;
    @FXML
    private TableColumn<AttendanceRecordViewModel, String> statusColumn;
    @FXML
    private TableColumn<AttendanceRecordViewModel, Button> buttonColumn;

    // Phương thức khởi tạo
    @FXML
    public void initialize() {
        try {
            // Tạo 1 kết nối cho khi chạy
            Connection conn = DatabaseConnection.getConnection();
            int userId = 1;
            // Load dữ liệu ban đầu
            loadStudentData(userId, 1, conn);

            // Đăng ký sự kiện thay đổi học kỳ
            hk1ToggleButton.setOnAction(event -> handleSemesterChange(userId, conn));
            hk2ToggleButton.setOnAction(event -> handleSemesterChange(userId, conn));

            // Đăng ký sự kiện chọn môn học từ ComboBox
            listCourse.setOnAction(event -> handleCourseSelection());
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo: " + e.getMessage());
        }
    }

    // Ràng buộc dữ liệu từ StudentViewModel vào UI
    private void bindStudentsVM() {
        id.textProperty().bind(studentsVM.idProperty());
        name.textProperty().bind(studentsVM.nameProperty());
    }

    // Phương thức cập nhật danh sách môn học trong ComboBox
    private void updateCourseList() {
        List<Course> courses = studentsVM.getCourses();
        listCourse.getItems().clear(); // Xóa danh sách cũ
        for (Course course : courses) {
            listCourse.getItems().add(course.getName());
        }
    }

    // Phương thức tải dữ liệu sinh viên từ ViewModel
    private void loadStudentData(int userId, int semester, Connection conn) throws SQLException {
        studentsVM.getInfoLoginVM(userId, semester, conn);
        bindStudentsVM();
        updateCourseList();
    }

    // Xử lý sự kiện thay đổi học kỳ
    private void handleSemesterChange(int userId, Connection conn) {
        int semester = selectedSemester();

        try {
            loadStudentData(userId, semester, conn);
            hk1ToggleButton.setSelected(false);
            hk2ToggleButton.setSelected(false);
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải dữ liệu: " + e.getMessage());
        }
    }

    // Phương thức xác định học kỳ được chọn
    private int selectedSemester() {
        if (hk1ToggleButton.isSelected()) {
            return 1;
        } else if (hk2ToggleButton.isSelected()) {
            return 2;
        }
        return 0;
    }

    // Hiển thị thông báo lỗi
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Xử lý sự kiện chọn môn học từ ComboBox
    private void handleCourseSelection() {
        // Lấy tên môn học đã chọn từ ComboBox
        String selectedCourse = listCourse.getValue();

        // Cập nhật titleSubject với tên môn học đã chọn
        if (selectedCourse != null) {
            titleSubject.setText("Môn: " + selectedCourse);
            loadAttendanceSubject();
        }
    }

    // Cập nhật thông tin của môn được chọn
    // Cập nhật thông tin của môn được chọn
    private void loadAttendanceSubject() {
        // Thiết lập tên cột trong bảng Attendance
        sessionColumn.setText("Session");
        dateColumn.setText("Date");
        statusColumn.setText("Status");
        buttonColumn.setText("Action");

        // Lấy danh sách AttendanceRecordViewModel và cập nhật vào bảng
        ObservableList<AttendanceRecordViewModel> attendanceRecords = FXCollections.observableArrayList();
        // Lấy dữ liệu Attendance từ ViewModel (giả sử có phương thức trong StudentsViewModel)
        for (Course course : studentsVM.getCourses()) {
            // Tạo đối tượng AttendanceRecordViewModel và thêm vào danh sách
            AttendanceRecordViewModel attendanceRecord = new AttendanceRecordViewModel();
            // Giả sử bạn đã có dữ liệu như session, date, status từ các nguồn khác
            attendanceRecords.add(attendanceRecord);
        }

        // Thiết lập dữ liệu vào bảng
        attendanceTable.setItems(attendanceRecords);
    }

}
