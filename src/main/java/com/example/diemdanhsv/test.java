package com.example.diemdanhsv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Tải file FXML
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/diemdanhsv/StudentsView.fxml"));
        primaryStage.setTitle("Thông tin điểm danh học sinh");
        
        // Thiết lập scene với kích thước mặc định
        primaryStage.setScene(new Scene(root)); // Không cần chỉ định kích thước
        
        // Đặt chế độ cửa sổ toàn màn hình
        primaryStage.setMaximized(true);
        
        // Hiển thị cửa sổ
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Khởi động ứng dụng
    }
}
