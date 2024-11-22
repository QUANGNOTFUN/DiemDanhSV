package com.example.diemdanhsv.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleLogin() {
        // Lấy thông tin từ các trường nhập liệu
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Giả sử bạn có một phương thức kiểm tra đăng nhập và người dùng hợp lệ
        // (Ở đây bạn có thể thêm logic kiểm tra với cơ sở dữ liệu)
        boolean isValidLogin = true; // Đây chỉ là ví dụ, bạn cần thực hiện kiểm tra thực tế.

        if (isValidLogin) {
            // Nếu đăng nhập thành công, bạn có thể hiển thị thông báo
            // (Thực tế có thể chuyển đến màn hình Attendance)
        } else {
            showErrorMessage("Login Failed", "Invalid username or password.");
        }
    }

    // Phương thức cho nút "Test"
    @FXML
    public void openNext() {
        try {
            // Tải màn hình Attendance từ FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/diemdanhsv/MainMenu.fxml"));
            Parent root = loader.load();

            // Lấy cửa sổ hiện tại và thay đổi nội dung scene
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("MENU");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorMessage("Error", "Failed to load MENU.");
        }
    }


    private void showErrorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
