package com.example.diemdanhsv.controllers;

import com.example.diemdanhsv.models.Course;
import com.example.diemdanhsv.viewModels.AttendanceRecordViewModel;
import com.example.diemdanhsv.viewModels.StudentsViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class StudentsViewController {
    private static final StudentsViewModel studentsVM = new StudentsViewModel();
    private static final AttendanceRecordViewModel attendanceVM = new AttendanceRecordViewModel();

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
            int userId = 1;
            // Load dữ liệu ban đầu
            loadStudentView(userId, 1);

            // Đăng ký sự kiện thay đổi học kỳ
            hk1ToggleButton.setOnAction(event -> handleSemesterChange(userId));
            hk2ToggleButton.setOnAction(event -> handleSemesterChange(userId));

        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo: " + e.getMessage());
        }
    }

    // Phương thức tải dữ liệu sinh viên từ ViewModel
    private void loadStudentView(int userId, int semester) throws SQLException {
        loadInfoUser(userId, semester);
    }

    // Ràng buộc dữ liệu từ StudentViewModel vào UI
    private void loadInfoUser(int userId, int semester) throws SQLException {
        studentsVM.getInfoLoginVM(userId, semester);

        // Cập nhật vào label
        id.setText("MSSV" + studentsVM.getId());
        name.setText("Tên: " + studentsVM.getName());

        // cập nhật vào combo box courses
        loadCourseList();
    }

    // Phương thức cập nhật danh sách môn học trong ComboBox
    private void loadCourseList() {
        List<Course> courses = studentsVM.getCourses();

        // Xóa các mục cũ và thêm các mục mới
        listCourse.getItems().clear();
        for (Course course : courses) {
            listCourse.getItems().add(course.getName());
        }

        // Gắn sự kiện khi người dùng chọn một môn học
        listCourse.setOnAction(event -> {
            // Lấy môn học được chọn
            String selectedCourseName = listCourse.getSelectionModel().getSelectedItem();

            // Tìm đối tượng Course tương ứng
            Course selectedCourse = courses.stream()
                    .filter(course -> course.getName().equals(selectedCourseName))
                    .findFirst()
                    .orElse(null);

            if (selectedCourse != null) {
                configureTableColumns();
                attendanceTable.setItems(attendanceVM.getAttendanceOfCourseVM(studentsVM.getId(), selectedCourse.getId()));
            }
        });
    }


    // Phương thức thêm data vào table view
    private void configureTableColumns() {
        // Cấu hình các cột
        sessionColumn.setCellValueFactory(cellData -> cellData.getValue().sessionProperty().asObject());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        buttonColumn.setCellValueFactory(cellData -> cellData.getValue().buttonProperty());

        // Đảm bảo danh sách ObservableList được liên kết với TableView
        attendanceTable.setItems(FXCollections.observableArrayList());
    }

    // Phương thức xử lý khi bấm nút
    private void handleButtonClick(AttendanceRecordViewModel record, int studentId) {
        attendanceVM.updateStatusCourseVN(record, studentId); // Cật nhật trạng thái
        record.setStatus("Present");
    }

    // Xử lý sự kiện thay đổi học kỳ
    private void handleSemesterChange(int userId) {
        int semester = selectedSemester();

        try {
            loadStudentView(userId, semester);
            hk1ToggleButton.setSelected(false);
            hk2ToggleButton.setSelected(false);
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải dữ liệu: " + e.getMessage());
        }
    }

    // Phương thức xác định học kỳ được chọn
    private int selectedSemester() {
        return hk1ToggleButton.isSelected() ? 1 : 2;
    }

    // Hiển thị thông báo lỗi
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
