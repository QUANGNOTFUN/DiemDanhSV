package com.example.diemdanhsv.controllers;

import com.example.diemdanhsv.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

public class AddUserController {

    private final UserRepository userRepo = new UserRepository();

    @FXML
    private TextField nameField;

    @FXML
    private CheckBox genderMaleCheckBox;

    @FXML
    private CheckBox genderFemaleCheckBox;

    @FXML
    private CheckBox roleStudentCheckBox;

    @FXML
    private CheckBox roleTeacherCheckBox;

    @FXML
    private Button createButton;

    // Phương thức được gọi sau khi FXML được tải
    @FXML
    private void initialize() {
        // Đảm bảo chỉ chọn 1 giới tính
        genderMaleCheckBox.setOnAction(event -> {
            if (genderMaleCheckBox.isSelected()) {
                genderFemaleCheckBox.setSelected(false);
            }
        });

        genderFemaleCheckBox.setOnAction(event -> {
            if (genderFemaleCheckBox.isSelected()) {
                genderMaleCheckBox.setSelected(false);
            }
        });

        // Đảm bảo chỉ chọn 1 vai trò
        roleStudentCheckBox.setOnAction(event -> {
            if (roleStudentCheckBox.isSelected()) {
                roleTeacherCheckBox.setSelected(false);
            }
        });

        roleTeacherCheckBox.setOnAction(event -> {
            if (roleTeacherCheckBox.isSelected()) {
                roleStudentCheckBox.setSelected(false);
            }
        });

        // Xử lý nút tạo
        createButton.setOnAction(event -> handleCreateUser());
    }

    // Xử lý khi bấm nút Tạo
    private void handleCreateUser() {
        String name = nameField.getText().trim();
        String gender = genderMaleCheckBox.isSelected() ? "Male" : genderFemaleCheckBox.isSelected() ? "Female" : null;
        String role = roleStudentCheckBox.isSelected() ? "student" : roleTeacherCheckBox.isSelected() ? "teacher" : null;

        // Kiểm tra dữ liệu đầu vào
        if (name.isEmpty() || gender == null || role == null) {
            showAlert(AlertType.ERROR, "Lỗi", "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        if (userRepo.createAccount(name, gender, role)) {
            showAlert(AlertType.INFORMATION, "Thành công", "Người dùng đã được tạo!");
            clearForm();
        }
    }

    // Hiển thị thông báo
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Xóa dữ liệu trên form
    private void clearForm() {
        nameField.clear();
        genderMaleCheckBox.setSelected(false);
        genderFemaleCheckBox.setSelected(false);
        roleStudentCheckBox.setSelected(false);
        roleTeacherCheckBox.setSelected(false);
    }
}

