package com.example.diemdanhsv.repository;

import com.example.diemdanhsv.databaseConnect.DatabaseConnection;
import com.example.diemdanhsv.models.Student;
import com.example.diemdanhsv.viewModels.StudentsViewModel;
import javafx.concurrent.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentsRepository {

    public StudentsRepository() {}

    // lấy thông tin sinh viên sau khi đăng nhập
    public static Student getInfoLogin(int userId, Connection conn) throws SQLException {
        String query = "SELECT id, name FROM students WHERE user_id = ?";
        Student student = new Student();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                }
            }
        }
        return student; // Trả về Student hoặc null nếu không tìm thấy
    }
}
