package com.example.diemdanhsv.repository;

import com.example.diemdanhsv.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CourseRepository
{
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
