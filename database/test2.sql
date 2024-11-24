-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 24, 2024 lúc 10:19 PM
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
-- Cơ sở dữ liệu: `test2`
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
  `status` enum('present','absent','late') NOT NULL DEFAULT 'absent'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `attendance`
--

INSERT INTO `attendance` (`id`, `student_id`, `course_id`, `date`, `status`) VALUES
(2, 2, 1, '2024-11-24', 'present'),
(3, 4, 1, '2024-11-24', 'present'),
(4, 6, 1, '2024-11-24', 'present'),
(5, 9, 1, '2024-11-24', 'absent'),
(6, 12, 1, '2024-11-24', 'absent'),
(7, 14, 1, '2024-11-24', 'absent'),
(8, 18, 1, '2024-11-24', 'absent'),
(9, 21, 1, '2024-11-24', 'absent'),
(10, 25, 1, '2024-11-24', 'absent'),
(11, 28, 1, '2024-11-24', 'absent'),
(12, 31, 1, '2024-11-24', 'absent'),
(13, 34, 1, '2024-11-24', 'absent'),
(14, 37, 1, '2024-11-24', 'absent'),
(15, 40, 1, '2024-11-24', 'absent'),
(16, 43, 1, '2024-11-24', 'absent'),
(17, 46, 1, '2024-11-24', 'absent'),
(18, 49, 1, '2024-11-24', 'absent'),
(19, 52, 1, '2024-11-24', 'absent'),
(20, 55, 1, '2024-11-24', 'absent'),
(21, 58, 1, '2024-11-24', 'absent'),
(22, 61, 1, '2024-11-24', 'absent'),
(23, 64, 1, '2024-11-24', 'absent'),
(24, 67, 1, '2024-11-24', 'absent'),
(25, 70, 1, '2024-11-24', 'absent'),
(28, 2, 2, '2024-11-24', 'present'),
(29, 3, 2, '2024-11-24', 'absent'),
(31, 4, 2, '2024-11-24', 'present'),
(32, 5, 2, '2024-11-24', 'absent'),
(33, 6, 2, '2024-11-24', 'present'),
(35, 7, 2, '2024-11-24', 'absent'),
(36, 8, 2, '2024-11-24', 'absent'),
(38, 9, 2, '2024-11-24', 'absent'),
(39, 10, 2, '2024-11-24', 'absent'),
(40, 11, 2, '2024-11-24', 'absent'),
(42, 12, 2, '2024-11-24', 'absent'),
(43, 13, 2, '2024-11-24', 'absent'),
(44, 14, 2, '2024-11-24', 'absent'),
(46, 15, 2, '2024-11-24', 'absent'),
(47, 16, 2, '2024-11-24', 'absent'),
(48, 17, 2, '2024-11-24', 'absent'),
(50, 18, 2, '2024-11-24', 'absent'),
(51, 19, 2, '2024-11-24', 'absent'),
(53, 20, 2, '2024-11-24', 'absent'),
(54, 21, 2, '2024-11-24', 'absent'),
(56, 22, 2, '2024-11-24', 'absent'),
(57, 23, 2, '2024-11-24', 'absent'),
(58, 24, 2, '2024-11-24', 'absent'),
(60, 25, 2, '2024-11-24', 'absent'),
(61, 26, 2, '2024-11-24', 'absent'),
(62, 27, 2, '2024-11-24', 'absent'),
(64, 28, 2, '2024-11-24', 'absent'),
(65, 29, 2, '2024-11-24', 'absent'),
(66, 30, 2, '2024-11-24', 'absent'),
(68, 31, 2, '2024-11-24', 'absent'),
(69, 32, 2, '2024-11-24', 'absent'),
(70, 33, 2, '2024-11-24', 'absent'),
(72, 34, 2, '2024-11-24', 'absent'),
(73, 35, 2, '2024-11-24', 'absent'),
(74, 36, 2, '2024-11-24', 'absent'),
(76, 37, 2, '2024-11-24', 'absent'),
(77, 38, 2, '2024-11-24', 'absent'),
(78, 39, 2, '2024-11-24', 'absent'),
(80, 40, 2, '2024-11-24', 'absent'),
(81, 41, 2, '2024-11-24', 'absent'),
(82, 42, 2, '2024-11-24', 'absent'),
(84, 43, 2, '2024-11-24', 'absent'),
(85, 44, 2, '2024-11-24', 'absent'),
(86, 45, 2, '2024-11-24', 'absent'),
(88, 46, 2, '2024-11-24', 'absent'),
(89, 47, 2, '2024-11-24', 'absent'),
(90, 48, 2, '2024-11-24', 'absent'),
(92, 49, 2, '2024-11-24', 'absent'),
(93, 50, 2, '2024-11-24', 'absent'),
(94, 51, 2, '2024-11-24', 'absent'),
(96, 52, 2, '2024-11-24', 'absent'),
(97, 53, 2, '2024-11-24', 'absent'),
(98, 54, 2, '2024-11-24', 'absent'),
(100, 55, 2, '2024-11-24', 'absent'),
(101, 56, 2, '2024-11-24', 'absent'),
(102, 57, 2, '2024-11-24', 'absent'),
(104, 58, 2, '2024-11-24', 'absent'),
(105, 59, 2, '2024-11-24', 'absent'),
(106, 60, 2, '2024-11-24', 'absent'),
(108, 61, 2, '2024-11-24', 'absent'),
(109, 62, 2, '2024-11-24', 'absent'),
(110, 63, 2, '2024-11-24', 'absent'),
(112, 64, 2, '2024-11-24', 'absent'),
(113, 65, 2, '2024-11-24', 'absent'),
(114, 66, 2, '2024-11-24', 'absent'),
(116, 67, 2, '2024-11-24', 'absent'),
(117, 68, 2, '2024-11-24', 'absent'),
(118, 69, 2, '2024-11-24', 'absent'),
(120, 70, 2, '2024-11-24', 'absent'),
(258, 1, 1, '2024-11-25', 'absent'),
(386, 1, 3, '2024-11-25', 'absent'),
(387, 2, 3, '2024-11-25', 'absent'),
(388, 3, 3, '2024-11-25', 'absent'),
(389, 4, 3, '2024-11-25', 'absent'),
(390, 6, 3, '2024-11-25', 'absent'),
(391, 7, 3, '2024-11-25', 'absent'),
(392, 11, 3, '2024-11-25', 'absent'),
(393, 14, 3, '2024-11-25', 'absent'),
(394, 16, 3, '2024-11-25', 'absent'),
(395, 17, 3, '2024-11-25', 'absent'),
(396, 19, 3, '2024-11-25', 'absent'),
(397, 20, 3, '2024-11-25', 'absent'),
(398, 21, 3, '2024-11-25', 'absent'),
(399, 22, 3, '2024-11-25', 'absent'),
(400, 25, 3, '2024-11-25', 'absent'),
(401, 27, 3, '2024-11-25', 'absent'),
(402, 28, 3, '2024-11-25', 'absent'),
(403, 33, 3, '2024-11-25', 'absent'),
(404, 35, 3, '2024-11-25', 'absent'),
(405, 41, 3, '2024-11-25', 'absent'),
(406, 45, 3, '2024-11-25', 'absent'),
(407, 51, 3, '2024-11-25', 'absent'),
(408, 52, 3, '2024-11-25', 'absent'),
(409, 54, 3, '2024-11-25', 'absent'),
(410, 56, 3, '2024-11-25', 'absent'),
(411, 57, 3, '2024-11-25', 'absent'),
(412, 58, 3, '2024-11-25', 'absent'),
(413, 59, 3, '2024-11-25', 'absent'),
(414, 62, 3, '2024-11-25', 'absent'),
(415, 64, 3, '2024-11-25', 'absent'),
(416, 65, 3, '2024-11-25', 'absent'),
(417, 69, 3, '2024-11-25', 'absent'),
(418, 4, 4, '2024-11-25', 'absent'),
(419, 7, 4, '2024-11-25', 'absent'),
(420, 8, 4, '2024-11-25', 'absent'),
(421, 14, 4, '2024-11-25', 'absent'),
(422, 15, 4, '2024-11-25', 'absent'),
(423, 16, 4, '2024-11-25', 'absent'),
(424, 17, 4, '2024-11-25', 'absent'),
(425, 18, 4, '2024-11-25', 'absent'),
(426, 19, 4, '2024-11-25', 'absent'),
(427, 26, 4, '2024-11-25', 'absent'),
(428, 27, 4, '2024-11-25', 'absent'),
(429, 29, 4, '2024-11-25', 'absent'),
(430, 30, 4, '2024-11-25', 'absent'),
(431, 31, 4, '2024-11-25', 'absent'),
(432, 32, 4, '2024-11-25', 'absent'),
(433, 33, 4, '2024-11-25', 'absent'),
(434, 36, 4, '2024-11-25', 'absent'),
(435, 50, 4, '2024-11-25', 'absent'),
(436, 53, 4, '2024-11-25', 'absent'),
(437, 54, 4, '2024-11-25', 'absent'),
(438, 58, 4, '2024-11-25', 'absent'),
(439, 59, 4, '2024-11-25', 'absent'),
(440, 61, 4, '2024-11-25', 'absent'),
(441, 63, 4, '2024-11-25', 'absent'),
(442, 65, 4, '2024-11-25', 'absent'),
(443, 66, 4, '2024-11-25', 'absent'),
(444, 67, 4, '2024-11-25', 'absent'),
(445, 1, 5, '2024-11-25', 'absent'),
(446, 2, 5, '2024-11-25', 'absent'),
(447, 4, 5, '2024-11-25', 'absent'),
(448, 5, 5, '2024-11-25', 'absent'),
(449, 10, 5, '2024-11-25', 'absent'),
(450, 11, 5, '2024-11-25', 'absent'),
(451, 18, 5, '2024-11-25', 'absent'),
(452, 21, 5, '2024-11-25', 'absent'),
(453, 22, 5, '2024-11-25', 'absent'),
(454, 23, 5, '2024-11-25', 'absent'),
(455, 25, 5, '2024-11-25', 'absent'),
(456, 28, 5, '2024-11-25', 'absent'),
(457, 29, 5, '2024-11-25', 'absent'),
(458, 30, 5, '2024-11-25', 'absent'),
(459, 33, 5, '2024-11-25', 'absent'),
(460, 34, 5, '2024-11-25', 'absent'),
(461, 36, 5, '2024-11-25', 'absent'),
(462, 42, 5, '2024-11-25', 'absent'),
(463, 43, 5, '2024-11-25', 'absent'),
(464, 44, 5, '2024-11-25', 'absent'),
(465, 48, 5, '2024-11-25', 'absent'),
(466, 60, 5, '2024-11-25', 'absent'),
(467, 61, 5, '2024-11-25', 'absent'),
(468, 64, 5, '2024-11-25', 'absent'),
(469, 65, 5, '2024-11-25', 'absent'),
(470, 67, 5, '2024-11-25', 'absent');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `day` varchar(15) NOT NULL,
  `academic_year` varchar(10) NOT NULL,
  `semester` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `course`
--

INSERT INTO `course` (`id`, `name`, `start_date`, `end_date`, `day`, `academic_year`, `semester`) VALUES
(1, 'Lập trình Python cơ bản', '2024-01-01', '2024-03-01', 'Monday', '2023-2024', 1),
(2, 'Phát triển Web nâng cao', '2024-02-01', '2024-04-30', 'Wednesday', '2023-2024', 1),
(3, 'Học máy (Machine Learning)', '2024-03-15', '2024-06-15', 'Friday', '2023-2024', 2),
(4, 'Khoa học dữ liệu (Data Science)', '2024-04-01', '2024-07-01', 'Tuesday', '2023-2024', 2),
(5, 'An ninh mạng (Cyber Security)', '2024-05-01', '2024-08-01', 'Thursday', '2024-2025', 1);

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
(3, 2, 1),
(7, 4, 1),
(11, 6, 1),
(17, 9, 1),
(23, 12, 1),
(27, 14, 1),
(35, 18, 1),
(40, 21, 1),
(47, 25, 1),
(52, 28, 1),
(57, 31, 1),
(62, 34, 1),
(67, 37, 1),
(72, 40, 1),
(77, 43, 1),
(82, 46, 1),
(87, 49, 1),
(92, 52, 1),
(97, 55, 1),
(102, 58, 1),
(107, 61, 1),
(112, 64, 1),
(117, 67, 1),
(122, 70, 1),
(2, 1, 2),
(125, 2, 2),
(5, 3, 2),
(127, 4, 2),
(128, 5, 2),
(12, 6, 2),
(130, 7, 2),
(15, 8, 2),
(132, 9, 2),
(133, 10, 2),
(21, 11, 2),
(135, 12, 2),
(136, 13, 2),
(28, 14, 2),
(138, 15, 2),
(139, 16, 2),
(33, 17, 2),
(141, 18, 2),
(37, 19, 2),
(143, 20, 2),
(41, 21, 2),
(145, 22, 2),
(146, 23, 2),
(45, 24, 2),
(148, 25, 2),
(149, 26, 2),
(50, 27, 2),
(151, 28, 2),
(152, 29, 2),
(55, 30, 2),
(154, 31, 2),
(155, 32, 2),
(60, 33, 2),
(157, 34, 2),
(158, 35, 2),
(65, 36, 2),
(160, 37, 2),
(161, 38, 2),
(70, 39, 2),
(163, 40, 2),
(164, 41, 2),
(75, 42, 2),
(166, 43, 2),
(167, 44, 2),
(80, 45, 2),
(169, 46, 2),
(170, 47, 2),
(85, 48, 2),
(172, 49, 2),
(173, 50, 2),
(90, 51, 2),
(175, 52, 2),
(176, 53, 2),
(95, 54, 2),
(178, 55, 2),
(179, 56, 2),
(100, 57, 2),
(181, 58, 2),
(182, 59, 2),
(105, 60, 2),
(184, 61, 2),
(185, 62, 2),
(110, 63, 2),
(187, 64, 2),
(188, 65, 2),
(115, 66, 2),
(190, 67, 2),
(191, 68, 2),
(120, 69, 2),
(193, 70, 2),
(194, 1, 3),
(4, 2, 3),
(196, 3, 3),
(197, 4, 3),
(9, 5, 3),
(199, 6, 3),
(13, 7, 3),
(201, 8, 3),
(18, 9, 3),
(203, 10, 3),
(204, 11, 3),
(205, 12, 3),
(25, 13, 3),
(207, 14, 3),
(208, 15, 3),
(31, 16, 3),
(210, 17, 3),
(36, 18, 3),
(212, 19, 3),
(213, 20, 3),
(42, 22, 3),
(48, 25, 3),
(53, 28, 3),
(58, 31, 3),
(63, 34, 3),
(68, 37, 3),
(73, 40, 3),
(78, 43, 3),
(83, 46, 3),
(88, 49, 3),
(93, 52, 3),
(98, 55, 3),
(103, 58, 3),
(108, 61, 3),
(113, 64, 3),
(118, 67, 3),
(123, 70, 3),
(214, 1, 4),
(215, 2, 4),
(216, 3, 4),
(8, 4, 4),
(218, 5, 4),
(219, 6, 4),
(220, 7, 4),
(16, 8, 4),
(222, 9, 4),
(20, 10, 4),
(224, 11, 4),
(24, 12, 4),
(226, 13, 4),
(227, 14, 4),
(29, 15, 4),
(229, 16, 4),
(34, 17, 4),
(231, 18, 4),
(38, 19, 4),
(233, 20, 4),
(43, 22, 4),
(46, 24, 4),
(51, 27, 4),
(56, 30, 4),
(61, 33, 4),
(66, 36, 4),
(71, 39, 4),
(76, 42, 4),
(81, 45, 4),
(86, 48, 4),
(91, 51, 4),
(96, 54, 4),
(101, 57, 4),
(106, 60, 4),
(111, 63, 4),
(116, 66, 4),
(121, 69, 4),
(234, 1, 5),
(235, 2, 5),
(6, 3, 5),
(237, 4, 5),
(10, 5, 5),
(239, 6, 5),
(14, 7, 5),
(241, 8, 5),
(242, 9, 5),
(19, 10, 5),
(22, 11, 5),
(245, 12, 5),
(26, 13, 5),
(247, 14, 5),
(30, 15, 5),
(32, 16, 5),
(250, 17, 5),
(251, 18, 5),
(252, 19, 5),
(39, 20, 5),
(44, 23, 5),
(49, 26, 5),
(54, 29, 5),
(59, 32, 5),
(64, 35, 5),
(69, 38, 5),
(74, 41, 5),
(79, 44, 5),
(84, 47, 5),
(89, 50, 5),
(94, 53, 5),
(99, 56, 5),
(104, 59, 5),
(109, 62, 5),
(114, 65, 5),
(119, 68, 5);

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

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `gender` enum('Male','Female','Other') NOT NULL DEFAULT 'Other'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `students`
--

INSERT INTO `students` (`id`, `name`, `email`, `user_id`, `gender`) VALUES
(1, 'Nguyen Van A1', 'student1@example.com', 1, 'Male'),
(2, 'Tran Thi B2', 'student2@example.com', 2, 'Female'),
(3, 'Le Van C3', 'student3@example.com', 3, 'Male'),
(4, 'Pham Thi D4', 'student4@example.com', 4, 'Female'),
(5, 'Vu Van E5', 'student5@example.com', 5, 'Male'),
(6, 'Bui Thi F6', 'student6@example.com', 6, 'Female'),
(7, 'Do Van G7', 'student7@example.com', 7, 'Male'),
(8, 'Pham Thi H8', 'student8@example.com', 8, 'Female'),
(9, 'Nguyen Van I9', 'student9@example.com', 9, 'Male'),
(10, 'Tran Thi J10', 'student10@example.com', 10, 'Female'),
(11, 'Le Van K11', 'student11@example.com', 11, 'Male'),
(12, 'Vu Thi L12', 'student12@example.com', 12, 'Female'),
(13, 'Nguyen Van M13', 'student13@example.com', 13, 'Male'),
(14, 'Pham Thi N14', 'student14@example.com', 14, 'Female'),
(15, 'Do Van O15', 'student15@example.com', 15, 'Male'),
(16, 'Bui Thi P16', 'student16@example.com', 16, 'Female'),
(17, 'Nguyen Van Q17', 'student17@example.com', 17, 'Male'),
(18, 'Pham Thi R18', 'student18@example.com', 18, 'Female'),
(19, 'Vu Van S19', 'student19@example.com', 19, 'Male'),
(20, 'Le Thi T20', 'student20@example.com', 20, 'Female'),
(21, 'Nguyen Van A21', 'student21@example.com', 21, 'Male'),
(22, 'Tran Thi B22', 'student22@example.com', 22, 'Female'),
(23, 'Le Van C23', 'student23@example.com', 23, 'Male'),
(24, 'Pham Thi D24', 'student24@example.com', 24, 'Female'),
(25, 'Vu Van E25', 'student25@example.com', 25, 'Male'),
(26, 'Bui Thi F26', 'student26@example.com', 26, 'Female'),
(27, 'Do Van G27', 'student27@example.com', 27, 'Male'),
(28, 'Pham Thi H28', 'student28@example.com', 28, 'Female'),
(29, 'Nguyen Van I29', 'student29@example.com', 29, 'Male'),
(30, 'Tran Thi J30', 'student30@example.com', 30, 'Female'),
(31, 'Le Van K31', 'student31@example.com', 31, 'Male'),
(32, 'Vu Thi L32', 'student32@example.com', 32, 'Female'),
(33, 'Nguyen Van M33', 'student33@example.com', 33, 'Male'),
(34, 'Pham Thi N34', 'student34@example.com', 34, 'Female'),
(35, 'Do Van O35', 'student35@example.com', 35, 'Male'),
(36, 'Bui Thi P36', 'student36@example.com', 36, 'Female'),
(37, 'Nguyen Van Q37', 'student37@example.com', 37, 'Male'),
(38, 'Pham Thi R38', 'student38@example.com', 38, 'Female'),
(39, 'Vu Van S39', 'student39@example.com', 39, 'Male'),
(40, 'Le Thi T40', 'student40@example.com', 40, 'Female'),
(41, 'Nguyen Van U41', 'student41@example.com', 41, 'Male'),
(42, 'Tran Thi V42', 'student42@example.com', 42, 'Female'),
(43, 'Bui Van W43', 'student43@example.com', 43, 'Male'),
(44, 'Pham Thi X44', 'student44@example.com', 44, 'Female'),
(45, 'Vu Van Y45', 'student45@example.com', 45, 'Male'),
(46, 'Nguyen Van Z46', 'student46@example.com', 46, 'Male'),
(47, 'Tran Thi A47', 'student47@example.com', 47, 'Female'),
(48, 'Le Van B48', 'student48@example.com', 48, 'Male'),
(49, 'Vu Thi C49', 'student49@example.com', 49, 'Female'),
(50, 'Pham Van D50', 'student50@example.com', 50, 'Male'),
(51, 'Nguyen Thi E51', 'student51@example.com', 51, 'Female'),
(52, 'Le Van F52', 'student52@example.com', 52, 'Male'),
(53, 'Bui Thi G53', 'student53@example.com', 53, 'Female'),
(54, 'Tran Van H54', 'student54@example.com', 54, 'Male'),
(55, 'Pham Thi I55', 'student55@example.com', 55, 'Female'),
(56, 'Nguyen Van J56', 'student56@example.com', 56, 'Male'),
(57, 'Le Thi K57', 'student57@example.com', 57, 'Female'),
(58, 'Do Van L58', 'student58@example.com', 58, 'Male'),
(59, 'Bui Thi M59', 'student59@example.com', 59, 'Female'),
(60, 'Vu Van N60', 'student60@example.com', 60, 'Male'),
(61, 'Nguyen Thi O61', 'student61@example.com', 61, 'Female'),
(62, 'Tran Van P62', 'student62@example.com', 62, 'Male'),
(63, 'Pham Thi Q63', 'student63@example.com', 63, 'Female'),
(64, 'Le Van R64', 'student64@example.com', 64, 'Male'),
(65, 'Nguyen Van S65', 'student65@example.com', 65, 'Male'),
(66, 'Vu Thi T66', 'student66@example.com', 66, 'Female'),
(67, 'Pham Van U67', 'student67@example.com', 67, 'Male'),
(68, 'Nguyen Thi V68', 'student68@example.com', 68, 'Female'),
(69, 'Le Van W69', 'student69@example.com', 69, 'Male'),
(70, 'Tran Thi X70', 'student70@example.com', 70, 'Female');

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
(1, 'admin01', '1234', 'admin', 0, '2024-11-24 11:37:09', '2024-11-24 20:57:44'),
(2, 'admin02', 'hashedpasswordadmin2', 'admin', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(3, 'student01', 'hashedpassword1', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(4, 'student02', 'hashedpassword2', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(5, 'student03', 'hashedpassword3', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(6, 'student04', 'hashedpassword4', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(7, 'student05', 'hashedpassword5', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(8, 'student06', 'hashedpassword6', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(9, 'student07', 'hashedpassword7', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(10, 'student08', 'hashedpassword8', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(11, 'student09', 'hashedpassword9', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(12, 'student10', 'hashedpassword10', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(13, 'student11', 'hashedpassword11', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(14, 'student12', 'hashedpassword12', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(15, 'student13', 'hashedpassword13', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(16, 'student14', 'hashedpassword14', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(17, 'student15', 'hashedpassword15', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(18, 'student16', 'hashedpassword16', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(19, 'student17', 'hashedpassword17', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(20, 'student18', 'hashedpassword18', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(21, 'student19', 'hashedpassword19', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(22, 'student20', 'hashedpassword20', 'student', 1, '2024-11-24 11:37:09', '2024-11-24 11:37:09'),
(23, 'student21', 'hashedpassword21', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(24, 'student22', 'hashedpassword22', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(25, 'student23', 'hashedpassword23', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(26, 'student24', 'hashedpassword24', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(27, 'student25', 'hashedpassword25', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(28, 'student26', 'hashedpassword26', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(29, 'student27', 'hashedpassword27', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(30, 'student28', 'hashedpassword28', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(31, 'student29', 'hashedpassword29', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(32, 'student30', 'hashedpassword30', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(33, 'student31', 'hashedpassword31', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(34, 'student32', 'hashedpassword32', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(35, 'student33', 'hashedpassword33', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(36, 'student34', 'hashedpassword34', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(37, 'student35', 'hashedpassword35', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(38, 'student36', 'hashedpassword36', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(39, 'student37', 'hashedpassword37', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(40, 'student38', 'hashedpassword38', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(41, 'student39', 'hashedpassword39', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(42, 'student40', 'hashedpassword40', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(43, 'student41', 'hashedpassword41', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(44, 'student42', 'hashedpassword42', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(45, 'student43', 'hashedpassword43', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(46, 'student44', 'hashedpassword44', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(47, 'student45', 'hashedpassword45', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(48, 'student46', 'hashedpassword46', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(49, 'student47', 'hashedpassword47', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(50, 'student48', 'hashedpassword48', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(51, 'student49', 'hashedpassword49', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(52, 'student50', 'hashedpassword50', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(53, 'student51', 'hashedpassword51', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(54, 'student52', 'hashedpassword52', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(55, 'student53', 'hashedpassword53', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(56, 'student54', 'hashedpassword54', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(57, 'student55', 'hashedpassword55', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(58, 'student56', 'hashedpassword56', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(59, 'student57', 'hashedpassword57', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(60, 'student58', 'hashedpassword58', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(61, 'student59', 'hashedpassword59', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(62, 'student60', 'hashedpassword60', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(63, 'student61', 'hashedpassword61', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(64, 'student62', 'hashedpassword62', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(65, 'student63', 'hashedpassword63', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(66, 'student64', 'hashedpassword64', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(67, 'student65', 'hashedpassword65', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(68, 'student66', 'hashedpassword66', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(69, 'student67', 'hashedpassword67', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(70, 'student68', 'hashedpassword68', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(71, 'student69', 'hashedpassword69', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35'),
(72, 'student70', 'hashedpassword70', 'student', 1, '2024-11-24 11:38:35', '2024-11-24 11:38:35');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `student_id` (`student_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Chỉ mục cho bảng `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

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
  ADD KEY `fk_students_users` (`user_id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=513;

--
-- AUTO_INCREMENT cho bảng `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `course_students`
--
ALTER TABLE `course_students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=254;

--
-- AUTO_INCREMENT cho bảng `csv_templates`
--
ALTER TABLE `csv_templates`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `fk_attendance_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_attendance_student` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `course_students`
--
ALTER TABLE `course_students`
  ADD CONSTRAINT `course_students_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `course_students_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `fk_students_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
