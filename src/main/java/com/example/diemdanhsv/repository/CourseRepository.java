package com.example.diemdanhsv.repository;

import com.example.diemdanhsv.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    public List<String> getCourseNames() {
        List<String> courseNames = new ArrayList<>();
        String query = "SELECT name FROM courses";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                courseNames.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseNames;
    }

    public ObservableList<String> getCourses() {
        ObservableList<String> courses = FXCollections.observableArrayList();
        String query = "SELECT name FROM courses"; // Lấy tên khóa học

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                courses.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public int getCourseIdByName(String courseName) {
        String query = "SELECT id FROM courses WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id"); // Trả về ID của khóa học
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Trả về -1 nếu không tìm thấy
    }


}
