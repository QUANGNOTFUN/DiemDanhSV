package com.example.diemdanhsv.controllers;

import com.example.diemdanhsv.databaseConnect.DatabaseConnection;
import com.example.diemdanhsv.models.Course;
import com.example.diemdanhsv.models.Student;
import com.example.diemdanhsv.viewModels.AttendanceRecordViewModel;
import com.example.diemdanhsv.viewModels.StudentsViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
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
            // Tạo 1 kết nối cho khi chạy
            Connection conn = DatabaseConnection.getConnection();
            int userId = 2;
            // Load dữ liệu ban đầu
            loadStudentData(userId, 1, conn);

            // Đăng ký sự kiện thay đổi học kỳ
            hk1ToggleButton.setOnAction(event -> handleSemesterChange(userId, conn));
            hk2ToggleButton.setOnAction(event -> handleSemesterChange(userId, conn));

            // Đăng ký sự kiện chọn môn học từ ComboBox
            listCourse.setOnAction(event -> handleCourseSelection(userId));
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
//        System.out.println(courses);
        listCourse.getItems().clear(); // Xóa danh sách cũ
        for (Course course : courses) {
            listCourse.getItems().add(course.getName());
        }
    }

    // Phương thức thêm data vào table view
    private void configureTableColumns() {
        // Cấu hình các cột
        sessionColumn.setCellValueFactory(cellData -> cellData.getValue().sessionProperty().asObject());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        // Cập nhật trạng thái trong cột status
        statusColumn.setCellValueFactory(cellData -> {
            AttendanceRecordViewModel record = cellData.getValue();
            int studentId = Integer.parseInt(studentsVM.getId().replaceAll("\\D+", "")); // Lấy ID sinh viên

            // Kiểm tra nếu trạng thái điểm danh đã được ghi nhận
            if (attendanceVM.checkAttandanceVM(record, studentId)) {
                // Nếu điểm danh, trạng thái sẽ là Present
                return new SimpleStringProperty("Present");
            } else {
                // Nếu không điểm danh, trạng thái sẽ là Absent
                return new SimpleStringProperty("Absent");
            }
        });
        // Điều chỉnh buttonColumn dựa trên điều kiện
        buttonColumn.setCellFactory(column -> new TableCell<>() {
            private final Button actionButton = new Button("Điểm danh");

            @Override
            protected void updateItem(Button button, boolean empty) {
                super.updateItem(button, empty);

                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setGraphic(null);
                    return;
                }

                AttendanceRecordViewModel record = getTableRow().getItem();
                LocalDate recordDate = record.getDate();

                // lấy id từ studentVM
                int studentId = Integer.parseInt(studentsVM.getId().replaceAll("\\D+", ""));

                // Kiểm tra nếu ngày khớp với ngày hiện tại
                if (recordDate != null && recordDate.equals(LocalDate.now())) {
                    if(!attendanceVM.checkAttandanceVM(record, studentId)) {
                        actionButton.setOnAction(e -> handleButtonClick(record, studentId)); // Xử lý nút
                        setGraphic(actionButton);
                    }
                } else {
                    setGraphic(null); // Ẩn nút
                }
            }
        });

        // Đảm bảo danh sách ObservableList được liên kết với TableView
        attendanceTable.setItems(FXCollections.observableArrayList());
    }

    // Phương thức xử lý khi bấm nút
    private void handleButtonClick(AttendanceRecordViewModel record, int studentId) {
        attendanceVM.updateStatusCourseVN(record, studentId); // Cật nhật trạng thái
        record.setStatus("Present");
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
    private void handleCourseSelection(int userId) {
        // Lấy tên môn học đã chọn từ ComboBox
        String selectedCourse = listCourse.getValue();
        List<Course> courses = studentsVM.getCourses();

        // Cập nhật titleSubject với tên môn học đã chọn
        if (selectedCourse != null) {
            titleSubject.setText("Môn: " + selectedCourse);
            for (Course course : courses) {
                if (Objects.equals(course.getName(), selectedCourse)) {
                    loadAttendanceSubject(course);
                }
            }
        }
    }

    // Cập nhật thông tin của môn được chọn
    private void loadAttendanceSubject(Course course) {
        ObservableList<AttendanceRecordViewModel> data = attendanceVM.createDataColumnProperty(course);

        // Hiện giá trị lên bảng
        configureTableColumns();
        attendanceTable.setItems(data);
    }
}
