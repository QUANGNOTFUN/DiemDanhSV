package com.example.diemdanhsv.repository;

import com.example.diemdanhsv.databaseConnect.DatabaseConnection;
import com.example.diemdanhsv.models.Attendance;
import com.example.diemdanhsv.viewModels.AttendanceRecordViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRepository {

    // Phương thức lấy danh sách Attendance từ cơ sở dữ liệu
    public ObservableList<Attendance> getAttendanceList() {
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
        String query = "SELECT a.id, a.student_id, a.course_id, a.date, a.status, s.name AS student_name, s.gender " +
                       "FROM attendance a " +
                       "JOIN students s ON a.student_id = s.id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Attendance attendance = new Attendance(
                        resultSet.getInt("id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getString("status"),
                        resultSet.getString("student_name"),
                        resultSet.getString("gender")
                );

                attendanceList.add(attendance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceList;
    }
    // lấy danh sách điểm danh của học sinh đang học
    public List<Attendance> getAttendanceOfCourse(int studentId, int courseId) {
        String query = "SELECT * FROM attendance WHERE student_id = ? AND course_id = ?";
        List<Attendance> attendances = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);

            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Attendance newAttendance = new Attendance();
                    newAttendance.setStudentId(rs.getInt("student_id"));
                    newAttendance.setCourseId(rs.getInt("course_id"));
                    newAttendance.setSession(rs.getInt("session"));
                    newAttendance.setDate(rs.getDate("date").toLocalDate());
                    newAttendance.setStatus(rs.getString("status"));

                    attendances.add(newAttendance);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return attendances;
    }

    // Cập nhật điểm danh vào database
    public void updateStatusSessionCourse(Attendance record, int studentId) {
        String query = "UPDATE attendance SET status = 'present' WHERE student_id = ? AND course_id = ? AND session = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, record.getCourseId());
            stmt.setInt(3, record.getSession());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
