package com.example.diemdanhsv.repository;

import com.example.diemdanhsv.databaseConnect.DatabaseConnection;
import com.example.diemdanhsv.viewModels.StudentsViewModel;
import javafx.concurrent.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentsRepository {
    private final CourseRepository courseRepo;

    public StudentsRepository() {
        this.courseRepo = new CourseRepository();
    }

    // lấy thông tin sinh viên sau khi đăng nhập
    public Task<StudentsViewModel> getInfoLoginAsync(int userId) {
        return new Task<>() {
            @Override
            protected StudentsViewModel call() throws Exception {
                String query = "SELECT id, name, class FROM students WHERE user_id = ?";
                StudentsViewModel studentsViewModel = new StudentsViewModel();

                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {

                    stmt.setInt(1, userId);

                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            studentsViewModel.setId(rs.getString("id"));
                            studentsViewModel.setName(rs.getString("name"));
                            studentsViewModel.setClassName(rs.getString("class"));

                            // Lấy danh sách khóa học của sinh viên
                            int studentId = rs.getInt("id");
                            Task<StudentsViewModel> courseTask = courseRepo.getCourseLoginAsync(studentId);

                            // Đợi kết quả từ task
                            courseTask.setOnSucceeded(event -> {
                                StudentsViewModel courses = courseTask.getValue();
                                System.out.println(courses);

                                // Thêm khóa học vào studentsViewModel
                                studentsViewModel.getCourses().addAll(courses.getCourses());
                            });

                            // Đảm bảo rằng chúng ta trả về đúng studentsViewModel sau khi đã lấy đầy đủ dữ liệu
                            courseTask.setOnFailed(event -> {
                                // Xử lý khi có lỗi
                                Throwable error = courseTask.getException();
                                System.err.println("Lỗi khi lấy khóa học: " + error.getMessage());
                            });

                            return studentsViewModel;  // Chúng ta trả về đối tượng, nhưng danh sách khóa học sẽ được thêm sau khi task hoàn tất
                        } else {
                            throw new RuntimeException("Không tìm thấy sinh viên với ID: " + userId);
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Lỗi truy vấn dữ liệu: " + e.getMessage(), e);
                }
            }
        };
    }
}
