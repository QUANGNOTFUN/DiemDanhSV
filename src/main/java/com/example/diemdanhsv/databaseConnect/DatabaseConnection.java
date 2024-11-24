package com.example.diemdanhsv.databaseConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Thông tin cấu hình cơ sở dữ liệu
    private static final String URL = "jdbc:mysql://localhost:3306/attendance";
    private static final String USERNAME = "root"; // Đặt tên người dùng của bạn
    private static final String PASSWORD = ""; // Đặt mật khẩu của bạn

    // Phương thức tạo kết nối
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Đăng ký Driver MySQL (không bắt buộc với các phiên bản JDBC mới)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Khởi tạo kết nối
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } catch (SQLException e) {
            System.err.println("Không thể kết nối đến cơ sở dữ liệu!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy Driver!");
            e.printStackTrace();
        }
        return connection;
    }
}
