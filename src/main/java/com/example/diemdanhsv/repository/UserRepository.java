package com.example.diemdanhsv.repository;

import com.example.diemdanhsv.databaseConnect.DatabaseConnection;
import com.example.diemdanhsv.models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserRepository {
    private Connection connection;

    public UserRepository() {
        this.connection = DatabaseConnection.getConnection();
        if (this.connection == null) {
            throw new RuntimeException("Không thể kết nối database!");
        }
    }

    public User login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Lấy mật khẩu băm từ cơ sở dữ liệu
                String storedHashedPassword = rs.getString("hashed_password");

                // Băm mật khẩu người dùng nhập vào
                String hashedInputPassword = hashPassword(password);

                // So sánh mật khẩu băm
                if (storedHashedPassword.equals(hashedInputPassword)) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("hashed_password"),
                            rs.getString("role").toUpperCase(),
                            rs.getBoolean("first_login"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi đăng nhập: " + e.getMessage());
        }
        return null; // Đăng nhập thất bại
    }

    public void closeConnection() {
        if (connection != null) {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public boolean updatePassword(int userId, String newPassword) {
        String query = "UPDATE users SET hashed_password = ?, first_login = 0 WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Băm mật khẩu mới
            String hashedPassword = hashPassword(newPassword);
            stmt.setString(1, hashedPassword);
            stmt.setInt(2, userId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi cập nhật mật khẩu: " + e.getMessage());
        }
    }

    public boolean checkPassword(int userId, String currentPassword) {
        String query = "SELECT hashed_password FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHashedPassword = rs.getString("hashed_password");
                // Băm mật khẩu người dùng nhập vào
                String hashedInputPassword = hashPassword(currentPassword);
                // So sánh
                return storedHashedPassword.equals(hashedInputPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi kiểm tra mật khẩu: " + e.getMessage());
        }
        return false;
    }

    public boolean createAccount(String name, String gender, String role) {
        // Tạo username và email tự động dựa trên vai trò
        String[] generatedData = generateEmailAndUsername(role);
        String username = generatedData[0];
        String generatedEmail = generatedData[1];

        // Câu truy vấn SQL cho việc tạo người dùng
        String userQuery = "INSERT INTO users (username, hashed_password, role, first_login, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?)";

        String hashedPassword = hashPassword("123"); // Bạn có thể thay đổi mật khẩu mặc định nếu cần

        // Lấy thời gian hiện tại
        LocalDateTime now = LocalDateTime.now();
        String currentTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS)) {

            // Gán giá trị cho các placeholder (?):
            stmt.setString(1, username); // Tên người dùng tự động tạo
            stmt.setString(2, hashedPassword);
            stmt.setString(3, role);
            stmt.setBoolean(4, true); // Đánh dấu lần đăng nhập đầu tiên
            stmt.setString(5, currentTime); // Thời gian tạo tài khoản
            stmt.setString(6, currentTime); // Thời gian cập nhật tài khoản

            // Thực thi câu lệnh và lấy kết quả
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                // Lấy id người dùng vừa tạo
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1); // Lấy id người dùng vừa tạo

                        // Gọi hàm tạo học sinh hoặc giáo viên
                        String query = "INSERT INTO " + (role.equals("student") ? "students" : "teachers") +
                                " (name, email, user_id, gender) VALUES(?, ?, ?, ?)";

                        // Sử dụng PreparedStatement với kết nối đã có
                        try (PreparedStatement stmt2 = conn.prepareStatement(query)) {
                            stmt2.setString(1, name);
                            stmt2.setString(2, generatedEmail); // Sử dụng generatedEmail
                            stmt2.setInt(3, userId);
                            stmt2.setString(4, gender);

                            int rowsInsertedRole = stmt2.executeUpdate();
                            if (rowsInsertedRole > 0) {
                                System.out.println(role + " đã được tạo thành công với userId: " + userId);
                                return true; // Trả về true sau khi tạo thành công
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi hoặc không thể tạo tài khoản
    }

    private String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }

    private String[] generateEmailAndUsername(String role) {
        // Sử dụng một biến đếm giả lập để tạo username cho học sinh và giáo viên
        int id = (int) (Math.random() * 1000); // Bạn có thể thay thế bằng cơ chế tự động tăng ID từ DB

        String username;
        String email;

        if (role.equals("student")) {
            username = "student_" + id;
            email = username + "@example.com";
        } else if (role.equals("teacher")) {
            username = "teacher_" + id;
            email = username + "@example.com";
        } else {
            throw new IllegalArgumentException("Role không hợp lệ!");
        }

        return new String[] {username, email};
    }
}