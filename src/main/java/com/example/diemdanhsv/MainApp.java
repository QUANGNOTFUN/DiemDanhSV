package com.example.diemdanhsv;

import com.example.diemdanhsv.database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Kiểm tra kết nối cơ sở dữ liệu
        Connection connection = DatabaseConnection.getConnection();

        // Tải giao diện FXML cho Login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        VBox root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
