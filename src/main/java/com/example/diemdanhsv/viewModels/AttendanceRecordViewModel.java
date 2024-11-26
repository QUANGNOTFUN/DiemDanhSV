package com.example.diemdanhsv.viewModels;

import com.example.diemdanhsv.models.Attendance;
import com.example.diemdanhsv.repository.AttendanceRepository;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.time.LocalDate;
import java.util.List;

public class AttendanceRecordViewModel {
    private final IntegerProperty courseId = new SimpleIntegerProperty();
    private final IntegerProperty session = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private final StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<Button> button = new SimpleObjectProperty<>();

    private final AttendanceRepository _attendanceRepo = new AttendanceRepository();
    // Constructor
    public AttendanceRecordViewModel() {}

    // Hàm gọi để tạo dữ liệu cột
    public ObservableList<AttendanceRecordViewModel> getAttendanceOfCourseVM(int studentId, int courseId) {
        ObservableList<AttendanceRecordViewModel> data = FXCollections.observableArrayList();
        List<Attendance> attendanceList = _attendanceRepo.getAttendanceOfCourse(studentId, courseId);
        LocalDate timeNow = LocalDate.now(); // Lấy ngày hiện tại

        for (Attendance attendance : attendanceList) {
            AttendanceRecordViewModel newAttendanceVM = new AttendanceRecordViewModel();

            // Gán dữ liệu cho ViewModel
            newAttendanceVM.setCourseId(attendance.getCourseId());
            newAttendanceVM.setSession(attendance.getSession());
            newAttendanceVM.setDate(attendance.getDate());
            newAttendanceVM.setStatus(attendance.getStatus().equals("present") ? "Present" : "Absent");

            Button button = new Button();
            if (!attendance.getStatus().equals("present") && attendance.getDate().isEqual(timeNow)) {
                button.setText("Điểm danh");
                button.setOnAction(event -> {
                    _attendanceRepo.updateStatusSessionCourse(attendance, studentId);
                    // Cập nhật trực tiếp trạng thái trong ViewModel
                    attendance.setStatus("present"); // Cập nhật trạng thái bản ghi trong cơ sở dữ liệu
                    newAttendanceVM.setStatus("Present"); // Cập nhật trạng thái trong ViewModel
                    button.setVisible(false); // Ẩn nút sau khi điểm danh
                });
            } else {
                button.setVisible(false); // Ẩn nút nếu không cần thiết
            }

            newAttendanceVM.setButton(button);

            // Thêm ViewModel vào danh sách
            data.add(newAttendanceVM);
        }

        return data;
    }

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

    public int getCourseId() {
        return courseId.get();
    }

    public void setCourseId(int courseId) {
        this.courseId.set(courseId);
    }

    public IntegerProperty courseIdProperty() {
        return courseId;
    }

}
