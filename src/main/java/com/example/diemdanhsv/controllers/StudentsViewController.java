package com.example.diemdanhsv.controllers;

import com.example.diemdanhsv.repository.StudentsRepository;
import com.example.diemdanhsv.viewModels.StudentsViewModel;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;

public class StudentsViewController {
    private final StudentsRepository _repo;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label stdClass;
    @FXML
    private ComboBox<String> listCourse;

    public StudentsViewController() {
        // Khởi tạo mặc định hoặc thông qua Dependency Injection
        this._repo = new StudentsRepository();
    }

    // Phương thức khởi tạo
    @FXML
    public void initialize() {
        try {
            getInfoLogin(2);  // Giả sử user_id là 2
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo: " + e.getMessage());
        }
    }

    // Lấy thông tin sinh viên và danh sách khóa học
    public void getInfoLogin(int user_id) {
        Task<StudentsViewModel> task = _repo.getInfoLoginAsync(user_id);

        task.setOnSucceeded(event -> {
            StudentsViewModel newValue = task.getValue();
            if (newValue != null) {
                // Cập nhật thông tin sinh viên lên UI
                id.setText("Mssv: " + newValue.getId());
                name.setText("Tên: " + newValue.getName());
                stdClass.setText("Lớp: " + newValue.getClassName());

                // Cập nhật ComboBox với danh sách khóa học
                listCourse.setItems(FXCollections.observableArrayList(newValue.getCourses().stream()
                        .map(course -> course.getName())  // Chuyển danh sách khóa học thành danh sách tên khóa học
                        .toList()));
            }
        });

        task.setOnFailed(event -> {
            Throwable e = task.getException();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không thể tải thông tin sinh viên");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        });

        new Thread(task).start();  // Chạy task bất đồng bộ
    }
}
