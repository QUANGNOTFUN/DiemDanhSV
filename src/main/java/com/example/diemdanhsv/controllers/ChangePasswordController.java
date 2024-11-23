package com.example.diemdanhsv.controllers;

import com.example.diemdanhsv.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import com.example.diemdanhsv.repository.UserRepository;

public class ChangePasswordController {
    @FXML
    private PasswordField currentPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label messageLabel;

    private User currentUser;
    private final UserRepository userRepository;

    public ChangePasswordController() {
        this.userRepository = new UserRepository();
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @FXML
    private void handleChangePassword() {
        if (currentUser == null) {
            messageLabel.setText("Lỗi: Không tìm thấy thông tin người dùng!");
            return;
        }

        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("Vui lòng điền đầy đủ thông tin!");
            return;
        }

        try {
            if (!currentUser.checkPassword(currentPassword)) {
                messageLabel.setText("Mật khẩu hiện tại không đúng!");
                return;
            }

            if (newPassword.length() < 6) {
                messageLabel.setText("Mật khẩu mới phải có ít nhất 6 ký tự!");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                messageLabel.setText("Xác nhận mật khẩu không khớp!");
                return;
            }

            if (userRepository.updatePassword(currentUser.getId(), newPassword)) {
                currentUser.setHashedPassword(newPassword);
                currentUser.setFirstLogin(false);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thành công");
                alert.setHeaderText(null);
                alert.setContentText("Đổi mật khẩu thành công!");
                alert.showAndWait();
                
                ((Stage) currentPasswordField.getScene().getWindow()).close();
            } else {
                messageLabel.setText("Có lỗi xảy ra khi cập nhật mật khẩu!");
            }
        } catch (Exception e) {
            messageLabel.setText("Có lỗi xảy ra: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        ((Stage) currentPasswordField.getScene().getWindow()).close();
    }
}