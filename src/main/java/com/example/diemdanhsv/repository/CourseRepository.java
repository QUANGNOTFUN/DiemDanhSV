package com.example.diemdanhsv.repository;

import com.example.diemdanhsv.databaseConnect.DatabaseConnection;
import com.example.diemdanhsv.models.Course;
import com.example.diemdanhsv.viewModels.StudentsViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    // lấy danh sách môn học cảu sinh viên
    public Task<StudentsViewModel> getCourseLoginAsync(int studentId) {
        return new Task<>() {
            @Override
            protected StudentsViewModel call() throws Exception {
                String query = "Select course_id From course_students Where student_id = ?";
                StudentsViewModel studentsViewModel = new StudentsViewModel();

                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {

                    stmt.setInt(1, studentId);

                    try (ResultSet rs = stmt.executeQuery()) {
                        // Duyệt qua danh sách khóa học của học sinh đăng nhập
                        while (rs.next()) {  // Nếu có nhiều môn học, dùng vòng lặp while thay vì if
                            String coursesQuery = "Select name, created_at, updated_at From courses Where id = ?";
                            try (PreparedStatement coursesStmt = conn.prepareStatement(coursesQuery)) {
                                coursesStmt.setInt(1, rs.getInt("course_id"));

                                try (ResultSet courses = coursesStmt.executeQuery()) {
                                    if (courses.next()) {
                                        Course newCourse = new Course();
                                        newCourse.setName(courses.getString("name"));
                                        newCourse.setCreatedAt((courses.getTimestamp("created_at")).toLocalDateTime());
                                        newCourse.setUpdatedAt((courses.getTimestamp("updated_at")).toLocalDateTime());

                                        studentsViewModel.addCourse(newCourse);
                                    }
                                } catch (SQLException e) {
                                    System.err.println("Lỗi khi truy vấn thông tin khóa học: " + e.getMessage());
                                    e.printStackTrace();  // In chi tiết ngoại lệ
                                }
                            } catch (SQLException e) {
                                System.err.println("Lỗi khi chuẩn bị câu lệnh truy vấn khóa học: " + e.getMessage());
                                e.printStackTrace();  // In chi tiết ngoại lệ
                            }
                        }
                    } catch (SQLException e) {
                        System.err.println("Lỗi khi truy vấn dữ liệu khóa học của sinh viên: " + e.getMessage());
                        e.printStackTrace();  // In chi tiết ngoại lệ
                    }
                } catch (SQLException e) {
                    System.err.println("Lỗi khi kết nối cơ sở dữ liệu hoặc truy vấn sinh viên: " + e.getMessage());
                    e.printStackTrace();  // In chi tiết ngoại lệ
                } catch (Exception e) {
                    System.err.println("Lỗi không xác định: " + e.getMessage());
                    e.printStackTrace();  // In chi tiết ngoại lệ
                }
                return studentsViewModel;
            }
        };
    }
}
