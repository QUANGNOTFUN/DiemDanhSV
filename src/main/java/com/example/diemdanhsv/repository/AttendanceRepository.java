package com.example.diemdanhsv.repository;

import com.example.diemdanhsv.databaseConnect.DatabaseConnection;
import com.example.diemdanhsv.models.Attendance;
import com.example.diemdanhsv.viewModels.AttendanceRecordViewModel;
import com.example.diemdanhsv.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;

public class AttendanceRepository {

    // Phương thức lấy danh sách Attendance từ cơ sở dữ liệu
    public ObservableList<Attendance> getAttendanceByCourseId(int courseId, int session) {
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
        String query = "SELECT a.id,a.session, a.student_id, a.course_id, a.date, a.status, s.id, s.name AS student_name, s.gender " +
                "FROM attendance a " +
                "JOIN students s ON a.student_id = s.id " +
                "WHERE a.course_id = ? AND a.session = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, session);
            ResultSet resultSet = preparedStatement.executeQuery();

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
            stmt.setInt(3, record.getSessionInt());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean deleteStudent(int studentId, int courseId) {
        String sql = "DELETE FROM attendance WHERE student_id = ? AND course_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStatus(int studentId, String newStatus, int courseId, int session) {
        String query = "UPDATE attendance SET status = ? WHERE student_id = ? AND course_id = ? AND session = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, courseId);
            preparedStatement.setInt(4, session);


            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất 1 dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addAttendance(int student_id, int course_id, LocalDate date, String status) {
        String sql = "CALL add_student_to_course_with_sessions(?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, student_id);
            statement.setInt(2, course_id);


            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có ít nhất 1 dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<String> getAvailableSessions() {
        ObservableList<String> sessions = FXCollections.observableArrayList();
        String query = "SELECT DISTINCT session FROM attendance ORDER BY session ASC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int sessionNumber = resultSet.getInt("session");
                    sessions.add("Buổi " + sessionNumber); // Thêm chuỗi "Buổi x" vào danh sách
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log lỗi
        }

        return sessions;
    }

    public ObservableList<Integer> getSessionNumbers() {
        ObservableList<Integer> sessionNumbers = FXCollections.observableArrayList();
        String query = "SELECT DISTINCT session FROM attendance ORDER BY session ASC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                sessionNumbers.add(resultSet.getInt("session")); // Add session number to the list
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log error
        }

        return sessionNumbers;
    }

}
