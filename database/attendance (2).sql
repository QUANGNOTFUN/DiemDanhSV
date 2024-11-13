-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 10, 2024 lúc 11:51 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `attendance`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `attendance`
--

CREATE TABLE `attendance` (
  `id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `status` enum('present','absent') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `attendance`
--

INSERT INTO `attendance` (`id`, `student_id`, `course_id`, `date`, `status`) VALUES
(1, 1, 1, '2024-10-01', 'present'),
(2, 1, 2, '2024-10-02', 'absent'),
(3, 2, 1, '2024-10-01', 'present'),
(4, 2, 3, '2024-10-03', 'present'),
(5, 3, 2, '2024-10-02', 'absent'),
(6, 3, 4, '2024-10-04', 'present'),
(7, 4, 1, '2024-10-01', 'present'),
(8, 4, 3, '2024-10-03', 'absent'),
(9, 5, 2, '2024-10-02', 'present'),
(10, 5, 4, '2024-10-04', 'present');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `courses`
--

CREATE TABLE `courses` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `code` varchar(10) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `courses`
--

INSERT INTO `courses` (`id`, `name`, `code`, `created_at`, `updated_at`) VALUES
(1, 'Math 101', 'MATH101', '2024-11-10 09:26:27', '2024-11-10 09:26:27'),
(2, 'Physics 101', 'PHYS101', '2024-11-10 09:26:27', '2024-11-10 09:26:27'),
(3, 'Chemistry 101', 'CHEM101', '2024-11-10 09:26:27', '2024-11-10 09:26:27'),
(4, 'Biology 101', 'BIO101', '2024-11-10 09:26:27', '2024-11-10 09:26:27');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `course_students`
--

CREATE TABLE `course_students` (
  `id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `course_students`
--

INSERT INTO `course_students` (`id`, `student_id`, `course_id`) VALUES
(1, 1, 1),
(3, 2, 1),
(7, 4, 1),
(11, 6, 1),
(15, 8, 1),
(19, 10, 1),
(23, 12, 1),
(27, 14, 1),
(31, 16, 1),
(35, 18, 1),
(39, 20, 1),
(2, 1, 2),
(5, 3, 2),
(9, 5, 2),
(13, 7, 2),
(17, 9, 2),
(21, 11, 2),
(25, 13, 2),
(29, 15, 2),
(33, 17, 2),
(37, 19, 2),
(4, 2, 3),
(8, 4, 3),
(12, 6, 3),
(16, 8, 3),
(20, 10, 3),
(24, 12, 3),
(28, 14, 3),
(32, 16, 3),
(36, 18, 3),
(40, 20, 3),
(6, 3, 4),
(10, 5, 4),
(14, 7, 4),
(18, 9, 4),
(22, 11, 4),
(26, 13, 4),
(30, 15, 4),
(34, 17, 4),
(38, 19, 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `csv_templates`
--

CREATE TABLE `csv_templates` (
  `id` int(11) NOT NULL,
  `template_name` varchar(100) NOT NULL,
  `template_description` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `csv_templates`
--

INSERT INTO `csv_templates` (`id`, `template_name`, `template_description`, `created_at`) VALUES
(1, 'StudentListTemplate', 'CSV template for importing student list to a course', '2024-11-09 17:33:05');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `students`
--

INSERT INTO `students` (`id`, `name`, `email`, `user_id`) VALUES
(1, 'Nguyen Van A', 'student01@example.com', 1),
(2, 'Tran Thi B', 'student02@example.com', 2),
(3, 'Le Van C', 'student03@example.com', 3),
(4, 'Pham Thi D', 'student04@example.com', 4),
(5, 'Hoang Van E', 'student05@example.com', 5),
(6, 'Vu Thi F', 'student06@example.com', 6),
(7, 'Bui Van G', 'student07@example.com', 7),
(8, 'Do Thi H', 'student08@example.com', 8),
(9, 'Pham Van I', 'student09@example.com', 9),
(10, 'Nguyen Thi J', 'student10@example.com', 10),
(11, 'Le Van K', 'student11@example.com', 11),
(12, 'Tran Thi L', 'student12@example.com', 12),
(13, 'Vu Van M', 'student13@example.com', 13),
(14, 'Do Van N', 'student14@example.com', 14),
(15, 'Pham Van O', 'student15@example.com', 15),
(16, 'Nguyen Thi P', 'student16@example.com', 16),
(17, 'Le Van Q', 'student17@example.com', 17),
(18, 'Tran Van R', 'student18@example.com', 18),
(19, 'Vu Van S', 'student19@example.com', 19),
(20, 'Bui Van T', 'student20@example.com', 20);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `hashed_password` varchar(255) NOT NULL,
  `role` enum('admin','student') NOT NULL,
  `first_login` tinyint(1) DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `username`, `hashed_password`, `role`, `first_login`, `created_at`, `updated_at`) VALUES
(1, 'student01', 'hashedpassword1', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(2, 'student02', 'hashedpassword2', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(3, 'student03', 'hashedpassword3', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(4, 'student04', 'hashedpassword4', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(5, 'student05', 'hashedpassword5', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(6, 'student06', 'hashedpassword6', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(7, 'student07', 'hashedpassword7', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(8, 'student08', 'hashedpassword8', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(9, 'student09', 'hashedpassword9', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(10, 'student10', 'hashedpassword10', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(11, 'student11', 'hashedpassword11', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(12, 'student12', 'hashedpassword12', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(13, 'student13', 'hashedpassword13', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(14, 'student14', 'hashedpassword14', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(15, 'student15', 'hashedpassword15', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(16, 'student16', 'hashedpassword16', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(17, 'student17', 'hashedpassword17', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(18, 'student18', 'hashedpassword18', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(19, 'student19', 'hashedpassword19', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(20, 'student20', 'hashedpassword20', 'student', 1, '2024-11-10 09:26:10', '2024-11-10 09:26:10'),
(21, 'admin01', 'hashedadminpassword1', 'admin', 1, '2024-11-10 09:28:04', '2024-11-10 09:28:04'),
(22, 'admin02', 'hashedadminpassword2', 'admin', 1, '2024-11-10 09:28:04', '2024-11-10 09:28:04'),
(23, 'admin03', 'hashedadminpassword3', 'admin', 1, '2024-11-10 09:28:04', '2024-11-10 09:28:04');

--
-- Bẫy `users`
--
DELIMITER $$
CREATE TRIGGER `set_first_login` BEFORE UPDATE ON `users` FOR EACH ROW BEGIN
    IF NEW.hashed_password <> OLD.hashed_password THEN
        SET NEW.first_login = FALSE;
    END IF;
END
$$
DELIMITER ;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `student_id` (`student_id`),
  ADD KEY `idx_attendance` (`course_id`,`student_id`,`date`);

--
-- Chỉ mục cho bảng `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Chỉ mục cho bảng `course_students`
--
ALTER TABLE `course_students`
  ADD PRIMARY KEY (`id`),
  ADD KEY `student_id` (`student_id`),
  ADD KEY `idx_course_student` (`course_id`,`student_id`);

--
-- Chỉ mục cho bảng `csv_templates`
--
ALTER TABLE `csv_templates`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `attendance`
--
ALTER TABLE `attendance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `courses`
--
ALTER TABLE `courses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `course_students`
--
ALTER TABLE `course_students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT cho bảng `csv_templates`
--
ALTER TABLE `csv_templates`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `course_students`
--
ALTER TABLE `course_students`
  ADD CONSTRAINT `course_students_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `course_students_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
