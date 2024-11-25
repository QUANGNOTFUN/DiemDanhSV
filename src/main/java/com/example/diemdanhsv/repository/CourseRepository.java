package com.example.diemdanhsv.repository;

import com.example.diemdanhsv.databaseConnect.DatabaseConnection;
import com.example.diemdanhsv.models.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    // lấy danh sách môn học của sinh viên đăng nhập
    public List<Course> getCourseLogin(int studentId, int semester) {
        String courseIdQuery = "Select course_id From course_students Where student_id = ?";
        List<Course> courses = new ArrayList<>();

        // Lấy coureId từ student_coures
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement coursesIdStmt = conn.prepareStatement(courseIdQuery)) {
            coursesIdStmt.setInt(1, studentId);

            // Lấy couresName từ course
            try(ResultSet coursesIdRs = coursesIdStmt.executeQuery()) {
                while (coursesIdRs.next()) {
                    int courseId = coursesIdRs.getInt("course_id");
                    String coursesQurey = "Select name, start_date, end_date, day, academic_year From course Where id = ? And semester = ?";

                    try(PreparedStatement coursesStmt = conn.prepareStatement(coursesQurey)) {
                        coursesStmt.setInt(1, courseId);
                        coursesStmt.setInt(2, semester);

                        try (ResultSet courseRs = coursesStmt.executeQuery()) {
                            if (courseRs.next()) {
                                Course newCourse = new Course();
                                newCourse.setId(courseId);
                                newCourse.setName(courseRs.getString("name"));
                                newCourse.setStartDate(courseRs.getDate("start_date").toLocalDate());
                                newCourse.setEndDate(courseRs.getDate("end_date").toLocalDate());
                                newCourse.setDay(courseRs.getString("day"));
                                newCourse.setAcademicYear(courseRs.getString("academic_year"));
                                courses.add(newCourse);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return courses;
    }
}
