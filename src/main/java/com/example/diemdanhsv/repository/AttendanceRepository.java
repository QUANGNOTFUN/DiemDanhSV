package com.example.diemdanhsv.repository;

import com.example.diemdanhsv.database.DatabaseConnection;
import com.example.diemdanhsv.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AttendanceRepository {
    // Phương thức đăng nhập, trả về đối tượng User nếu đăng nhập thành công, null nếu không thành công
    public static User login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Tạo một đối tượng User từ kết quả truy vấn
                int id = rs.getInt("id");
                String role = rs.getString("role"); // Lấy quyền của người dùng (admin hoặc teacher)
                boolean firstLogin = rs.getBoolean("first_login"); // Assuming you have this field in your database
                Date createdAt = rs.getDate("created_at"); // Assuming you have this field in your database
                Date updatedAt = rs.getDate("updated_at"); // Assuming you have this field in your database
                return new User(id, username, password, role, firstLogin, createdAt, updatedAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy user
    }
    // Phương thức lấy danh sách môn học từ cơ sở dữ liệu
    public ObservableList<String> getCourses() {
        ObservableList<String> subjects = FXCollections.observableArrayList();

        String query = "SELECT name FROM courses";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                subjects.add(rs.getString("name")); // Thay "subject_name" bằng tên cột chứa tên môn học
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;
    }
}
