-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 26, 2024 lúc 05:06 PM
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

DELIMITER $$
--
-- Thủ tục
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_student_to_course_with_sessions` (IN `studentId` INT, IN `courseId` INT)   BEGIN
    DECLARE startDate DATE;
    DECLARE endDate DATE;
    DECLARE currentDate DATE;
    DECLARE sessionNumber INT DEFAULT 1;
    
    -- Lấy start_date và end_date từ bảng course
    SELECT start_date, end_date INTO startDate, endDate
    FROM `course`
    WHERE `id` = courseId;

    -- Tạo bản ghi tự động trong bảng attendance
    WHILE startDate <= endDate DO
        INSERT INTO `attendance` (`student_id`, `course_id`, `date`, `status`, `session`)
        VALUES (studentId, courseId, startDate, 'absent', sessionNumber);
        
        SET sessionNumber = sessionNumber + 1;  -- Tăng sessionNumber
        SET startDate = DATE_ADD(startDate, INTERVAL 1 WEEK);  -- Tăng một tuần
    END WHILE;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `remove_student_from_course` (IN `studentId` INT, IN `courseId` INT)   BEGIN
    -- Xóa các bản ghi attendance của học sinh khỏi khóa học
    DELETE FROM `attendance`
    WHERE `student_id` = studentId AND `course_id` = courseId;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `attendance`
--

CREATE TABLE `attendance` (
  `id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `status` enum('present','absent','late') NOT NULL DEFAULT 'absent',
  `session` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `attendance`
--

INSERT INTO `attendance` (`id`, `student_id`, `course_id`, `date`, `status`, `session`) VALUES
(1, 1, 1, '2024-09-01', 'present', 1),
(2, 1, 1, '2024-09-08', 'present', 2),
(3, 1, 1, '2024-09-15', 'absent', 3),
(4, 1, 1, '2024-09-22', 'absent', 4),
(5, 1, 1, '2024-09-29', 'absent', 5),
(6, 1, 1, '2024-10-06', 'absent', 6),
(7, 1, 1, '2024-10-13', 'absent', 7),
(8, 1, 1, '2024-10-20', 'absent', 8),
(9, 1, 1, '2024-10-27', 'absent', 9),
(10, 1, 1, '2024-11-03', 'absent', 10),
(11, 1, 1, '2024-11-10', 'absent', 11),
(12, 1, 1, '2024-11-17', 'absent', 12),
(13, 1, 1, '2024-11-24', 'absent', 13),
(14, 1, 1, '2024-12-01', 'absent', 14),
(15, 1, 1, '2024-12-08', 'absent', 15),
(16, 1, 1, '2024-12-15', 'absent', 16),
(17, 1, 1, '2024-12-22', 'absent', 17),
(18, 1, 1, '2024-12-29', 'absent', 18),
(19, 1, 1, '2025-01-05', 'absent', 19),
(20, 1, 1, '2025-01-12', 'absent', 20),
(21, 2, 1, '2024-09-01', 'present', 1),
(22, 2, 1, '2024-09-08', 'late', 2),
(23, 2, 1, '2024-09-15', 'absent', 3),
(24, 2, 1, '2024-09-22', 'absent', 4),
(25, 2, 1, '2024-09-29', 'absent', 5),
(26, 2, 1, '2024-10-06', 'absent', 6),
(27, 2, 1, '2024-10-13', 'absent', 7),
(28, 2, 1, '2024-10-20', 'absent', 8),
(29, 2, 1, '2024-10-27', 'absent', 9),
(30, 2, 1, '2024-11-03', 'absent', 10),
(31, 2, 1, '2024-11-10', 'absent', 11),
(32, 2, 1, '2024-11-17', 'absent', 12),
(33, 2, 1, '2024-11-24', 'absent', 13),
(34, 2, 1, '2024-12-01', 'absent', 14),
(35, 2, 1, '2024-12-08', 'absent', 15),
(36, 2, 1, '2024-12-15', 'absent', 16),
(37, 2, 1, '2024-12-22', 'absent', 17),
(38, 2, 1, '2024-12-29', 'absent', 18),
(39, 2, 1, '2025-01-05', 'absent', 19),
(40, 2, 1, '2025-01-12', 'absent', 20),
(41, 3, 1, '2024-09-01', 'present', 1),
(42, 3, 1, '2024-09-08', 'present', 2),
(43, 3, 1, '2024-09-15', 'absent', 3),
(44, 3, 1, '2024-09-22', 'absent', 4),
(45, 3, 1, '2024-09-29', 'absent', 5),
(46, 3, 1, '2024-10-06', 'absent', 6),
(47, 3, 1, '2024-10-13', 'absent', 7),
(48, 3, 1, '2024-10-20', 'absent', 8),
(49, 3, 1, '2024-10-27', 'absent', 9),
(50, 3, 1, '2024-11-03', 'absent', 10),
(51, 3, 1, '2024-11-10', 'absent', 11),
(52, 3, 1, '2024-11-17', 'absent', 12),
(53, 3, 1, '2024-11-24', 'absent', 13),
(54, 3, 1, '2024-12-01', 'absent', 14),
(55, 3, 1, '2024-12-08', 'absent', 15),
(56, 3, 1, '2024-12-15', 'absent', 16),
(57, 3, 1, '2024-12-22', 'absent', 17),
(58, 3, 1, '2024-12-29', 'absent', 18),
(59, 3, 1, '2025-01-05', 'absent', 19),
(60, 3, 1, '2025-01-12', 'absent', 20),
(61, 4, 1, '2024-09-01', 'present', 1),
(62, 4, 1, '2024-09-08', 'present', 2),
(63, 4, 1, '2024-09-15', 'absent', 3),
(64, 4, 1, '2024-09-22', 'absent', 4),
(65, 4, 1, '2024-09-29', 'absent', 5),
(66, 4, 1, '2024-10-06', 'absent', 6),
(67, 4, 1, '2024-10-13', 'absent', 7),
(68, 4, 1, '2024-10-20', 'absent', 8),
(69, 4, 1, '2024-10-27', 'absent', 9),
(70, 4, 1, '2024-11-03', 'absent', 10),
(71, 4, 1, '2024-11-10', 'absent', 11),
(72, 4, 1, '2024-11-17', 'absent', 12),
(73, 4, 1, '2024-11-24', 'absent', 13),
(74, 4, 1, '2024-12-01', 'absent', 14),
(75, 4, 1, '2024-12-08', 'absent', 15),
(76, 4, 1, '2024-12-15', 'absent', 16),
(77, 4, 1, '2024-12-22', 'absent', 17),
(78, 4, 1, '2024-12-29', 'absent', 18),
(79, 4, 1, '2025-01-05', 'absent', 19),
(80, 4, 1, '2025-01-12', 'absent', 20),
(81, 5, 1, '2024-09-01', 'present', 1),
(82, 5, 1, '2024-09-08', 'present', 2),
(83, 5, 1, '2024-09-15', 'absent', 3),
(84, 5, 1, '2024-09-22', 'absent', 4),
(85, 5, 1, '2024-09-29', 'absent', 5),
(86, 5, 1, '2024-10-06', 'absent', 6),
(87, 5, 1, '2024-10-13', 'absent', 7),
(88, 5, 1, '2024-10-20', 'absent', 8),
(89, 5, 1, '2024-10-27', 'absent', 9),
(90, 5, 1, '2024-11-03', 'absent', 10),
(91, 5, 1, '2024-11-10', 'absent', 11),
(92, 5, 1, '2024-11-17', 'absent', 12),
(93, 5, 1, '2024-11-24', 'absent', 13),
(94, 5, 1, '2024-12-01', 'absent', 14),
(95, 5, 1, '2024-12-08', 'absent', 15),
(96, 5, 1, '2024-12-15', 'absent', 16),
(97, 5, 1, '2024-12-22', 'absent', 17),
(98, 5, 1, '2024-12-29', 'absent', 18),
(99, 5, 1, '2025-01-05', 'absent', 19),
(100, 5, 1, '2025-01-12', 'absent', 20),
(101, 6, 1, '2024-09-01', 'present', 1),
(102, 6, 1, '2024-09-08', 'present', 2),
(103, 6, 1, '2024-09-15', 'absent', 3),
(104, 6, 1, '2024-09-22', 'absent', 4),
(105, 6, 1, '2024-09-29', 'absent', 5),
(106, 6, 1, '2024-10-06', 'absent', 6),
(107, 6, 1, '2024-10-13', 'absent', 7),
(108, 6, 1, '2024-10-20', 'absent', 8),
(109, 6, 1, '2024-10-27', 'absent', 9),
(110, 6, 1, '2024-11-03', 'absent', 10),
(111, 6, 1, '2024-11-10', 'absent', 11),
(112, 6, 1, '2024-11-17', 'absent', 12),
(113, 6, 1, '2024-11-24', 'absent', 13),
(114, 6, 1, '2024-12-01', 'absent', 14),
(115, 6, 1, '2024-12-08', 'absent', 15),
(116, 6, 1, '2024-12-15', 'absent', 16),
(117, 6, 1, '2024-12-22', 'absent', 17),
(118, 6, 1, '2024-12-29', 'absent', 18),
(119, 6, 1, '2025-01-05', 'absent', 19),
(120, 6, 1, '2025-01-12', 'absent', 20),
(121, 7, 1, '2024-09-01', 'present', 1),
(122, 7, 1, '2024-09-08', 'present', 2),
(123, 7, 1, '2024-09-15', 'absent', 3),
(124, 7, 1, '2024-09-22', 'absent', 4),
(125, 7, 1, '2024-09-29', 'absent', 5),
(126, 7, 1, '2024-10-06', 'absent', 6),
(127, 7, 1, '2024-10-13', 'absent', 7),
(128, 7, 1, '2024-10-20', 'absent', 8),
(129, 7, 1, '2024-10-27', 'absent', 9),
(130, 7, 1, '2024-11-03', 'absent', 10),
(131, 7, 1, '2024-11-10', 'absent', 11),
(132, 7, 1, '2024-11-17', 'absent', 12),
(133, 7, 1, '2024-11-24', 'absent', 13),
(134, 7, 1, '2024-12-01', 'absent', 14),
(135, 7, 1, '2024-12-08', 'absent', 15),
(136, 7, 1, '2024-12-15', 'absent', 16),
(137, 7, 1, '2024-12-22', 'absent', 17),
(138, 7, 1, '2024-12-29', 'absent', 18),
(139, 7, 1, '2025-01-05', 'absent', 19),
(140, 7, 1, '2025-01-12', 'absent', 20),
(161, 1, 2, '2024-09-01', 'absent', 1),
(162, 1, 2, '2024-09-08', 'absent', 2),
(163, 1, 2, '2024-09-15', 'absent', 3),
(164, 1, 2, '2024-09-22', 'absent', 4),
(165, 1, 2, '2024-09-29', 'absent', 5),
(166, 1, 2, '2024-10-06', 'absent', 6),
(167, 1, 2, '2024-10-13', 'absent', 7),
(168, 1, 2, '2024-10-20', 'absent', 8),
(169, 1, 2, '2024-10-27', 'absent', 9),
(170, 1, 2, '2024-11-03', 'absent', 10),
(171, 1, 2, '2024-11-10', 'absent', 11),
(172, 1, 2, '2024-11-17', 'absent', 12),
(173, 1, 2, '2024-11-24', 'absent', 13),
(174, 1, 2, '2024-12-01', 'absent', 14),
(175, 1, 2, '2024-12-08', 'absent', 15),
(176, 1, 2, '2024-12-15', 'absent', 16),
(177, 1, 2, '2024-12-22', 'absent', 17),
(178, 1, 2, '2024-12-29', 'absent', 18),
(179, 1, 2, '2025-01-05', 'absent', 19),
(180, 1, 2, '2025-01-12', 'absent', 20),
(181, 2, 2, '2024-09-01', 'absent', 1),
(182, 2, 2, '2024-09-08', 'absent', 2),
(183, 2, 2, '2024-09-15', 'absent', 3),
(184, 2, 2, '2024-09-22', 'absent', 4),
(185, 2, 2, '2024-09-29', 'absent', 5),
(186, 2, 2, '2024-10-06', 'absent', 6),
(187, 2, 2, '2024-10-13', 'absent', 7),
(188, 2, 2, '2024-10-20', 'absent', 8),
(189, 2, 2, '2024-10-27', 'absent', 9),
(190, 2, 2, '2024-11-03', 'absent', 10),
(191, 2, 2, '2024-11-10', 'absent', 11),
(192, 2, 2, '2024-11-17', 'absent', 12),
(193, 2, 2, '2024-11-24', 'absent', 13),
(194, 2, 2, '2024-12-01', 'absent', 14),
(195, 2, 2, '2024-12-08', 'absent', 15),
(196, 2, 2, '2024-12-15', 'absent', 16),
(197, 2, 2, '2024-12-22', 'absent', 17),
(198, 2, 2, '2024-12-29', 'absent', 18),
(199, 2, 2, '2025-01-05', 'absent', 19),
(200, 2, 2, '2025-01-12', 'absent', 20),
(201, 4, 2, '2024-09-01', 'absent', 1),
(202, 4, 2, '2024-09-08', 'absent', 2),
(203, 4, 2, '2024-09-15', 'absent', 3),
(204, 4, 2, '2024-09-22', 'absent', 4),
(205, 4, 2, '2024-09-29', 'absent', 5),
(206, 4, 2, '2024-10-06', 'absent', 6),
(207, 4, 2, '2024-10-13', 'absent', 7),
(208, 4, 2, '2024-10-20', 'absent', 8),
(209, 4, 2, '2024-10-27', 'absent', 9),
(210, 4, 2, '2024-11-03', 'absent', 10),
(211, 4, 2, '2024-11-10', 'absent', 11),
(212, 4, 2, '2024-11-17', 'absent', 12),
(213, 4, 2, '2024-11-24', 'absent', 13),
(214, 4, 2, '2024-12-01', 'absent', 14),
(215, 4, 2, '2024-12-08', 'absent', 15),
(216, 4, 2, '2024-12-15', 'absent', 16),
(217, 4, 2, '2024-12-22', 'absent', 17),
(218, 4, 2, '2024-12-29', 'absent', 18),
(219, 4, 2, '2025-01-05', 'absent', 19),
(220, 4, 2, '2025-01-12', 'absent', 20),
(221, 5, 2, '2024-09-01', 'absent', 1),
(222, 5, 2, '2024-09-08', 'absent', 2),
(223, 5, 2, '2024-09-15', 'absent', 3),
(224, 5, 2, '2024-09-22', 'absent', 4),
(225, 5, 2, '2024-09-29', 'absent', 5),
(226, 5, 2, '2024-10-06', 'absent', 6),
(227, 5, 2, '2024-10-13', 'absent', 7),
(228, 5, 2, '2024-10-20', 'absent', 8),
(229, 5, 2, '2024-10-27', 'absent', 9),
(230, 5, 2, '2024-11-03', 'absent', 10),
(231, 5, 2, '2024-11-10', 'absent', 11),
(232, 5, 2, '2024-11-17', 'absent', 12),
(233, 5, 2, '2024-11-24', 'absent', 13),
(234, 5, 2, '2024-12-01', 'absent', 14),
(235, 5, 2, '2024-12-08', 'absent', 15),
(236, 5, 2, '2024-12-15', 'absent', 16),
(237, 5, 2, '2024-12-22', 'absent', 17),
(238, 5, 2, '2024-12-29', 'absent', 18),
(239, 5, 2, '2025-01-05', 'absent', 19),
(240, 5, 2, '2025-01-12', 'absent', 20),
(241, 6, 2, '2024-09-01', 'absent', 1),
(242, 6, 2, '2024-09-08', 'absent', 2),
(243, 6, 2, '2024-09-15', 'absent', 3),
(244, 6, 2, '2024-09-22', 'absent', 4),
(245, 6, 2, '2024-09-29', 'absent', 5),
(246, 6, 2, '2024-10-06', 'absent', 6),
(247, 6, 2, '2024-10-13', 'absent', 7),
(248, 6, 2, '2024-10-20', 'absent', 8),
(249, 6, 2, '2024-10-27', 'absent', 9),
(250, 6, 2, '2024-11-03', 'absent', 10),
(251, 6, 2, '2024-11-10', 'absent', 11),
(252, 6, 2, '2024-11-17', 'absent', 12),
(253, 6, 2, '2024-11-24', 'absent', 13),
(254, 6, 2, '2024-12-01', 'absent', 14),
(255, 6, 2, '2024-12-08', 'absent', 15),
(256, 6, 2, '2024-12-15', 'absent', 16),
(257, 6, 2, '2024-12-22', 'absent', 17),
(258, 6, 2, '2024-12-29', 'absent', 18),
(259, 6, 2, '2025-01-05', 'absent', 19),
(260, 6, 2, '2025-01-12', 'absent', 20),
(261, 6, 3, '2024-09-01', 'absent', 1),
(262, 6, 3, '2024-09-08', 'absent', 2),
(263, 6, 3, '2024-09-15', 'absent', 3),
(264, 6, 3, '2024-09-22', 'absent', 4),
(265, 6, 3, '2024-09-29', 'absent', 5),
(266, 6, 3, '2024-10-06', 'absent', 6),
(267, 6, 3, '2024-10-13', 'absent', 7),
(268, 6, 3, '2024-10-20', 'absent', 8),
(269, 6, 3, '2024-10-27', 'absent', 9),
(270, 6, 3, '2024-11-03', 'absent', 10),
(271, 6, 3, '2024-11-10', 'absent', 11),
(272, 6, 3, '2024-11-17', 'late', 12),
(273, 6, 3, '2024-11-24', 'absent', 13),
(274, 6, 3, '2024-12-01', 'absent', 14),
(275, 6, 3, '2024-12-08', 'absent', 15),
(276, 6, 3, '2024-12-15', 'absent', 16),
(277, 6, 3, '2024-12-22', 'absent', 17),
(278, 6, 3, '2024-12-29', 'absent', 18),
(279, 6, 3, '2025-01-05', 'absent', 19),
(280, 6, 3, '2025-01-12', 'absent', 20),
(281, 3, 3, '2024-09-01', 'absent', 1),
(282, 3, 3, '2024-09-08', 'absent', 2),
(283, 3, 3, '2024-09-15', 'absent', 3),
(284, 3, 3, '2024-09-22', 'absent', 4),
(285, 3, 3, '2024-09-29', 'absent', 5),
(286, 3, 3, '2024-10-06', 'absent', 6),
(287, 3, 3, '2024-10-13', 'absent', 7),
(288, 3, 3, '2024-10-20', 'absent', 8),
(289, 3, 3, '2024-10-27', 'absent', 9),
(290, 3, 3, '2024-11-03', 'absent', 10),
(291, 3, 3, '2024-11-10', 'absent', 11),
(292, 3, 3, '2024-11-17', 'present', 12),
(293, 3, 3, '2024-11-24', 'absent', 13),
(294, 3, 3, '2024-12-01', 'absent', 14),
(295, 3, 3, '2024-12-08', 'absent', 15),
(296, 3, 3, '2024-12-15', 'absent', 16),
(297, 3, 3, '2024-12-22', 'absent', 17),
(298, 3, 3, '2024-12-29', 'absent', 18),
(299, 3, 3, '2025-01-05', 'absent', 19),
(300, 3, 3, '2025-01-12', 'absent', 20),
(302, 5, 3, '2024-09-01', 'absent', 1),
(303, 5, 3, '2024-09-08', 'absent', 2),
(304, 5, 3, '2024-09-15', 'absent', 3),
(305, 5, 3, '2024-09-22', 'absent', 4),
(306, 5, 3, '2024-09-29', 'absent', 5),
(307, 5, 3, '2024-10-06', 'absent', 6),
(308, 5, 3, '2024-10-13', 'absent', 7),
(309, 5, 3, '2024-10-20', 'absent', 8),
(310, 5, 3, '2024-10-27', 'absent', 9),
(311, 5, 3, '2024-11-03', 'absent', 10),
(312, 5, 3, '2024-11-10', 'absent', 11),
(313, 5, 3, '2024-11-17', 'present', 12),
(314, 5, 3, '2024-11-24', 'absent', 13),
(315, 5, 3, '2024-12-01', 'absent', 14),
(316, 5, 3, '2024-12-08', 'absent', 15),
(317, 5, 3, '2024-12-15', 'absent', 16),
(318, 5, 3, '2024-12-22', 'absent', 17),
(319, 5, 3, '2024-12-29', 'absent', 18),
(320, 5, 3, '2025-01-05', 'absent', 19),
(321, 5, 3, '2025-01-12', 'absent', 20),
(322, 7, 3, '2024-09-01', 'absent', 1),
(323, 7, 3, '2024-09-08', 'absent', 2),
(324, 7, 3, '2024-09-15', 'absent', 3),
(325, 7, 3, '2024-09-22', 'absent', 4),
(326, 7, 3, '2024-09-29', 'absent', 5),
(327, 7, 3, '2024-10-06', 'absent', 6),
(328, 7, 3, '2024-10-13', 'absent', 7),
(329, 7, 3, '2024-10-20', 'absent', 8),
(330, 7, 3, '2024-10-27', 'absent', 9),
(331, 7, 3, '2024-11-03', 'absent', 10),
(332, 7, 3, '2024-11-10', 'absent', 11),
(333, 7, 3, '2024-11-17', 'late', 12),
(334, 7, 3, '2024-11-24', 'absent', 13),
(335, 7, 3, '2024-12-01', 'absent', 14),
(336, 7, 3, '2024-12-08', 'absent', 15),
(337, 7, 3, '2024-12-15', 'absent', 16),
(338, 7, 3, '2024-12-22', 'absent', 17),
(339, 7, 3, '2024-12-29', 'absent', 18),
(340, 7, 3, '2025-01-05', 'absent', 19),
(341, 7, 3, '2025-01-12', 'absent', 20),
(342, 22, 3, '2024-09-01', 'absent', 1),
(343, 22, 3, '2024-09-08', 'absent', 2),
(344, 22, 3, '2024-09-15', 'absent', 3),
(345, 22, 3, '2024-09-22', 'absent', 4),
(346, 22, 3, '2024-09-29', 'absent', 5),
(347, 22, 3, '2024-10-06', 'absent', 6),
(348, 22, 3, '2024-10-13', 'absent', 7),
(349, 22, 3, '2024-10-20', 'absent', 8),
(350, 22, 3, '2024-10-27', 'absent', 9),
(351, 22, 3, '2024-11-03', 'absent', 10),
(352, 22, 3, '2024-11-10', 'absent', 11),
(353, 22, 3, '2024-11-17', 'absent', 12),
(354, 22, 3, '2024-11-24', 'absent', 13),
(355, 22, 3, '2024-12-01', 'absent', 14),
(356, 22, 3, '2024-12-08', 'absent', 15),
(357, 22, 3, '2024-12-15', 'absent', 16),
(358, 22, 3, '2024-12-22', 'absent', 17),
(359, 22, 3, '2024-12-29', 'absent', 18),
(360, 22, 3, '2025-01-05', 'absent', 19),
(361, 22, 3, '2025-01-12', 'absent', 20),
(363, 22, 4, '2024-09-01', 'absent', 1),
(364, 22, 4, '2024-09-08', 'absent', 2),
(365, 22, 4, '2024-09-15', 'absent', 3),
(366, 22, 4, '2024-09-22', 'absent', 4),
(367, 22, 4, '2024-09-29', 'absent', 5),
(368, 22, 4, '2024-10-06', 'absent', 6),
(369, 22, 4, '2024-10-13', 'absent', 7),
(370, 22, 4, '2024-10-20', 'absent', 8),
(371, 22, 4, '2024-10-27', 'absent', 9),
(372, 22, 4, '2024-11-03', 'absent', 10),
(373, 22, 4, '2024-11-10', 'absent', 11),
(374, 22, 4, '2024-11-17', 'absent', 12),
(375, 22, 4, '2024-11-24', 'absent', 13),
(376, 22, 4, '2024-12-01', 'absent', 14),
(377, 22, 4, '2024-12-08', 'absent', 15),
(378, 22, 4, '2024-12-15', 'absent', 16),
(379, 22, 4, '2024-12-22', 'absent', 17),
(380, 22, 4, '2024-12-29', 'absent', 18),
(381, 22, 4, '2025-01-05', 'absent', 19),
(382, 22, 4, '2025-01-12', 'absent', 20),
(383, 12, 4, '2024-09-01', 'absent', 1),
(384, 12, 4, '2024-09-08', 'absent', 2),
(385, 12, 4, '2024-09-15', 'absent', 3),
(386, 12, 4, '2024-09-22', 'absent', 4),
(387, 12, 4, '2024-09-29', 'absent', 5),
(388, 12, 4, '2024-10-06', 'absent', 6),
(389, 12, 4, '2024-10-13', 'absent', 7),
(390, 12, 4, '2024-10-20', 'absent', 8),
(391, 12, 4, '2024-10-27', 'absent', 9),
(392, 12, 4, '2024-11-03', 'absent', 10),
(393, 12, 4, '2024-11-10', 'absent', 11),
(394, 12, 4, '2024-11-17', 'absent', 12),
(395, 12, 4, '2024-11-24', 'absent', 13),
(396, 12, 4, '2024-12-01', 'absent', 14),
(397, 12, 4, '2024-12-08', 'absent', 15),
(398, 12, 4, '2024-12-15', 'absent', 16),
(399, 12, 4, '2024-12-22', 'absent', 17),
(400, 12, 4, '2024-12-29', 'absent', 18),
(401, 12, 4, '2025-01-05', 'absent', 19),
(402, 12, 4, '2025-01-12', 'absent', 20),
(403, 3, 4, '2024-09-01', 'absent', 1),
(404, 3, 4, '2024-09-08', 'absent', 2),
(405, 3, 4, '2024-09-15', 'absent', 3),
(406, 3, 4, '2024-09-22', 'absent', 4),
(407, 3, 4, '2024-09-29', 'absent', 5),
(408, 3, 4, '2024-10-06', 'absent', 6),
(409, 3, 4, '2024-10-13', 'absent', 7),
(410, 3, 4, '2024-10-20', 'absent', 8),
(411, 3, 4, '2024-10-27', 'absent', 9),
(412, 3, 4, '2024-11-03', 'absent', 10),
(413, 3, 4, '2024-11-10', 'absent', 11),
(414, 3, 4, '2024-11-17', 'absent', 12),
(415, 3, 4, '2024-11-24', 'absent', 13),
(416, 3, 4, '2024-12-01', 'absent', 14),
(417, 3, 4, '2024-12-08', 'absent', 15),
(418, 3, 4, '2024-12-15', 'absent', 16),
(419, 3, 4, '2024-12-22', 'absent', 17),
(420, 3, 4, '2024-12-29', 'absent', 18),
(421, 3, 4, '2025-01-05', 'absent', 19),
(422, 3, 4, '2025-01-12', 'absent', 20),
(423, 2, 5, '2024-09-01', 'absent', 1),
(424, 2, 5, '2024-09-08', 'absent', 2),
(425, 2, 5, '2024-09-15', 'absent', 3),
(426, 2, 5, '2024-09-22', 'absent', 4),
(427, 2, 5, '2024-09-29', 'absent', 5),
(428, 2, 5, '2024-10-06', 'absent', 6),
(429, 2, 5, '2024-10-13', 'absent', 7),
(430, 2, 5, '2024-10-20', 'absent', 8),
(431, 2, 5, '2024-10-27', 'absent', 9),
(432, 2, 5, '2024-11-03', 'absent', 10),
(433, 2, 5, '2024-11-10', 'absent', 11),
(434, 2, 5, '2024-11-17', 'absent', 12),
(435, 2, 5, '2024-11-24', 'absent', 13),
(436, 2, 5, '2024-12-01', 'absent', 14),
(437, 2, 5, '2024-12-08', 'absent', 15),
(438, 2, 5, '2024-12-15', 'absent', 16),
(439, 2, 5, '2024-12-22', 'absent', 17),
(440, 2, 5, '2024-12-29', 'absent', 18),
(441, 2, 5, '2025-01-05', 'absent', 19),
(442, 2, 5, '2025-01-12', 'absent', 20),
(443, 31, 5, '2024-09-01', 'absent', 1),
(444, 31, 5, '2024-09-08', 'absent', 2),
(445, 31, 5, '2024-09-15', 'absent', 3),
(446, 31, 5, '2024-09-22', 'absent', 4),
(447, 31, 5, '2024-09-29', 'absent', 5),
(448, 31, 5, '2024-10-06', 'absent', 6),
(449, 31, 5, '2024-10-13', 'absent', 7),
(450, 31, 5, '2024-10-20', 'absent', 8),
(451, 31, 5, '2024-10-27', 'absent', 9),
(452, 31, 5, '2024-11-03', 'absent', 10),
(453, 31, 5, '2024-11-10', 'absent', 11),
(454, 31, 5, '2024-11-17', 'absent', 12),
(455, 31, 5, '2024-11-24', 'absent', 13),
(456, 31, 5, '2024-12-01', 'absent', 14),
(457, 31, 5, '2024-12-08', 'absent', 15),
(458, 31, 5, '2024-12-15', 'absent', 16),
(459, 31, 5, '2024-12-22', 'absent', 17),
(460, 31, 5, '2024-12-29', 'absent', 18),
(461, 31, 5, '2025-01-05', 'absent', 19),
(462, 31, 5, '2025-01-12', 'absent', 20),
(463, 1, 5, '2024-09-01', 'absent', 1),
(464, 1, 5, '2024-09-08', 'absent', 2),
(465, 1, 5, '2024-09-15', 'absent', 3),
(466, 1, 5, '2024-09-22', 'absent', 4),
(467, 1, 5, '2024-09-29', 'absent', 5),
(468, 1, 5, '2024-10-06', 'absent', 6),
(469, 1, 5, '2024-10-13', 'absent', 7),
(470, 1, 5, '2024-10-20', 'absent', 8),
(471, 1, 5, '2024-10-27', 'absent', 9),
(472, 1, 5, '2024-11-03', 'absent', 10),
(473, 1, 5, '2024-11-10', 'absent', 11),
(474, 1, 5, '2024-11-17', 'absent', 12),
(475, 1, 5, '2024-11-24', 'absent', 13),
(476, 1, 5, '2024-12-01', 'absent', 14),
(477, 1, 5, '2024-12-08', 'absent', 15),
(478, 1, 5, '2024-12-15', 'absent', 16),
(479, 1, 5, '2024-12-22', 'absent', 17),
(480, 1, 5, '2024-12-29', 'absent', 18),
(481, 1, 5, '2025-01-05', 'absent', 19),
(482, 1, 5, '2025-01-12', 'absent', 20),
(484, 3, 5, '2024-09-01', 'absent', 1),
(485, 3, 5, '2024-09-08', 'absent', 2),
(486, 3, 5, '2024-09-15', 'absent', 3),
(487, 3, 5, '2024-09-22', 'absent', 4),
(488, 3, 5, '2024-09-29', 'absent', 5),
(489, 3, 5, '2024-10-06', 'absent', 6),
(490, 3, 5, '2024-10-13', 'absent', 7),
(491, 3, 5, '2024-10-20', 'absent', 8),
(492, 3, 5, '2024-10-27', 'absent', 9),
(493, 3, 5, '2024-11-03', 'absent', 10),
(494, 3, 5, '2024-11-10', 'absent', 11),
(495, 3, 5, '2024-11-17', 'absent', 12),
(496, 3, 5, '2024-11-24', 'absent', 13),
(497, 3, 5, '2024-12-01', 'absent', 14),
(498, 3, 5, '2024-12-08', 'absent', 15),
(499, 3, 5, '2024-12-15', 'absent', 16),
(500, 3, 5, '2024-12-22', 'absent', 17),
(501, 3, 5, '2024-12-29', 'absent', 18),
(502, 3, 5, '2025-01-05', 'absent', 19),
(503, 3, 5, '2025-01-12', 'absent', 20),
(504, 4, 5, '2024-09-01', 'absent', 1),
(505, 4, 5, '2024-09-08', 'absent', 2),
(506, 4, 5, '2024-09-15', 'absent', 3),
(507, 4, 5, '2024-09-22', 'absent', 4),
(508, 4, 5, '2024-09-29', 'absent', 5),
(509, 4, 5, '2024-10-06', 'absent', 6),
(510, 4, 5, '2024-10-13', 'absent', 7),
(511, 4, 5, '2024-10-20', 'absent', 8),
(512, 4, 5, '2024-10-27', 'absent', 9),
(513, 4, 5, '2024-11-03', 'absent', 10),
(514, 4, 5, '2024-11-10', 'absent', 11),
(515, 4, 5, '2024-11-17', 'absent', 12),
(516, 4, 5, '2024-11-24', 'absent', 13),
(517, 4, 5, '2024-12-01', 'absent', 14),
(518, 4, 5, '2024-12-08', 'absent', 15),
(519, 4, 5, '2024-12-15', 'absent', 16),
(520, 4, 5, '2024-12-22', 'absent', 17),
(521, 4, 5, '2024-12-29', 'absent', 18),
(522, 4, 5, '2025-01-05', 'absent', 19),
(523, 4, 5, '2025-01-12', 'absent', 20),
(524, 5, 5, '2024-09-01', 'absent', 1),
(525, 5, 5, '2024-09-08', 'absent', 2),
(526, 5, 5, '2024-09-15', 'absent', 3),
(527, 5, 5, '2024-09-22', 'absent', 4),
(528, 5, 5, '2024-09-29', 'absent', 5),
(529, 5, 5, '2024-10-06', 'absent', 6),
(530, 5, 5, '2024-10-13', 'absent', 7),
(531, 5, 5, '2024-10-20', 'absent', 8),
(532, 5, 5, '2024-10-27', 'absent', 9),
(533, 5, 5, '2024-11-03', 'absent', 10),
(534, 5, 5, '2024-11-10', 'absent', 11),
(535, 5, 5, '2024-11-17', 'absent', 12),
(536, 5, 5, '2024-11-24', 'absent', 13),
(537, 5, 5, '2024-12-01', 'absent', 14),
(538, 5, 5, '2024-12-08', 'absent', 15),
(539, 5, 5, '2024-12-15', 'absent', 16),
(540, 5, 5, '2024-12-22', 'absent', 17),
(541, 5, 5, '2024-12-29', 'absent', 18),
(542, 5, 5, '2025-01-05', 'absent', 19),
(543, 5, 5, '2025-01-12', 'absent', 20),
(544, 6, 5, '2024-09-01', 'absent', 1),
(545, 6, 5, '2024-09-08', 'absent', 2),
(546, 6, 5, '2024-09-15', 'absent', 3),
(547, 6, 5, '2024-09-22', 'absent', 4),
(548, 6, 5, '2024-09-29', 'absent', 5),
(549, 6, 5, '2024-10-06', 'absent', 6),
(550, 6, 5, '2024-10-13', 'absent', 7),
(551, 6, 5, '2024-10-20', 'absent', 8),
(552, 6, 5, '2024-10-27', 'absent', 9),
(553, 6, 5, '2024-11-03', 'absent', 10),
(554, 6, 5, '2024-11-10', 'absent', 11),
(555, 6, 5, '2024-11-17', 'absent', 12),
(556, 6, 5, '2024-11-24', 'absent', 13),
(557, 6, 5, '2024-12-01', 'absent', 14),
(558, 6, 5, '2024-12-08', 'absent', 15),
(559, 6, 5, '2024-12-15', 'absent', 16),
(560, 6, 5, '2024-12-22', 'absent', 17),
(561, 6, 5, '2024-12-29', 'absent', 18),
(562, 6, 5, '2025-01-05', 'absent', 19),
(563, 6, 5, '2025-01-12', 'absent', 20),
(564, 7, 5, '2024-09-01', 'absent', 1),
(565, 7, 5, '2024-09-08', 'absent', 2),
(566, 7, 5, '2024-09-15', 'absent', 3),
(567, 7, 5, '2024-09-22', 'absent', 4),
(568, 7, 5, '2024-09-29', 'absent', 5),
(569, 7, 5, '2024-10-06', 'absent', 6),
(570, 7, 5, '2024-10-13', 'absent', 7),
(571, 7, 5, '2024-10-20', 'absent', 8),
(572, 7, 5, '2024-10-27', 'absent', 9),
(573, 7, 5, '2024-11-03', 'absent', 10),
(574, 7, 5, '2024-11-10', 'absent', 11),
(575, 7, 5, '2024-11-17', 'absent', 12),
(576, 7, 5, '2024-11-24', 'absent', 13),
(577, 7, 5, '2024-12-01', 'absent', 14),
(578, 7, 5, '2024-12-08', 'absent', 15),
(579, 7, 5, '2024-12-15', 'absent', 16),
(580, 7, 5, '2024-12-22', 'absent', 17),
(581, 7, 5, '2024-12-29', 'absent', 18),
(582, 7, 5, '2025-01-05', 'absent', 19),
(583, 7, 5, '2025-01-12', 'absent', 20),
(584, 9, 1, '2024-09-01', 'absent', 1),
(585, 9, 1, '2024-09-08', 'absent', 2),
(586, 9, 1, '2024-09-15', 'absent', 3),
(587, 9, 1, '2024-09-22', 'absent', 4),
(588, 9, 1, '2024-09-29', 'absent', 5),
(589, 9, 1, '2024-10-06', 'absent', 6),
(590, 9, 1, '2024-10-13', 'absent', 7),
(591, 9, 1, '2024-10-20', 'absent', 8),
(592, 9, 1, '2024-10-27', 'absent', 9),
(593, 9, 1, '2024-11-03', 'absent', 10),
(594, 9, 1, '2024-11-10', 'absent', 11),
(595, 9, 1, '2024-11-17', 'absent', 12),
(596, 9, 1, '2024-11-24', 'absent', 13),
(597, 9, 1, '2024-12-01', 'absent', 14),
(598, 9, 1, '2024-12-08', 'absent', 15),
(599, 9, 1, '2024-12-15', 'absent', 16),
(600, 9, 1, '2024-12-22', 'absent', 17),
(601, 9, 1, '2024-12-29', 'absent', 18),
(602, 9, 1, '2025-01-05', 'absent', 19),
(603, 9, 1, '2025-01-12', 'absent', 20);

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
(1, 'Mathematics 101', '2024-09-01', '2025-01-15', 'Monday', '2024-2025', 1),
(2, 'Physics 101', '2024-09-01', '2025-01-15', 'Tuesday', '2024-2025', 1),
(3, 'Chemistry 101', '2024-09-01', '2025-01-15', 'Wednesday', '2024-2025', 1),
(4, 'Biology 101', '2024-09-01', '2025-01-15', 'Thursday', '2024-2025', 1),
(5, 'Computer Science 101', '2024-09-01', '2025-01-15', 'Friday', '2024-2025', 1),
(6, 'Literature 101', '2024-09-01', '2025-01-15', 'Monday', '2024-2025', 1);

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
(1, 'Hien', 'student_837@example.com', 4, 'Male'),
(2, 'Student 1', 'student1@example.com', 8, 'Other'),
(3, 'Student 2', 'student2@example.com', 8, 'Other'),
(4, 'Student 3', 'student3@example.com', 8, 'Other'),
(5, 'Student 4', 'student4@example.com', 8, 'Other'),
(6, 'Student 5', 'student5@example.com', 8, 'Other'),
(7, 'Student 6', 'student6@example.com', 8, 'Other'),
(8, 'Student 7', 'student7@example.com', 8, 'Other'),
(9, 'Student 8', 'student8@example.com', 8, 'Other'),
(10, 'Student 9', 'student9@example.com', 8, 'Other'),
(11, 'Student 10', 'student10@example.com', 8, 'Other'),
(12, 'Student 11', 'student11@example.com', 8, 'Other'),
(13, 'Student 12', 'student12@example.com', 8, 'Other'),
(14, 'Student 13', 'student13@example.com', 8, 'Other'),
(15, 'Student 14', 'student14@example.com', 8, 'Other'),
(16, 'Student 15', 'student15@example.com', 8, 'Other'),
(17, 'Student 16', 'student16@example.com', 8, 'Other'),
(18, 'Student 17', 'student17@example.com', 8, 'Other'),
(19, 'Student 18', 'student18@example.com', 8, 'Other'),
(20, 'Student 19', 'student19@example.com', 8, 'Other'),
(21, 'Student 20', 'student20@example.com', 8, 'Other'),
(22, 'Student 21', 'student21@example.com', 8, 'Other'),
(23, 'Student 22', 'student22@example.com', 8, 'Other'),
(24, 'Student 23', 'student23@example.com', 8, 'Other'),
(25, 'Student 24', 'student24@example.com', 8, 'Other'),
(26, 'Student 25', 'student25@example.com', 8, 'Other'),
(27, 'Student 26', 'student26@example.com', 8, 'Other'),
(28, 'Student 27', 'student27@example.com', 8, 'Other'),
(29, 'Student 28', 'student28@example.com', 8, 'Other'),
(30, 'Student 29', 'student29@example.com', 8, 'Other'),
(31, 'Student 30', 'student30@example.com', 8, 'Other');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `teachers`
--

CREATE TABLE `teachers` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `user_id` int(11) NOT NULL,
  `gender` enum('Male','Female','Other') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `teachers`
--

INSERT INTO `teachers` (`id`, `name`, `email`, `user_id`, `gender`) VALUES
(1, 'teacher1', 'teacher_22@example.com', 6, 'Female');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `hashed_password` varchar(255) NOT NULL,
  `role` enum('admin','student','teacher') NOT NULL,
  `first_login` tinyint(1) DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `username`, `hashed_password`, `role`, `first_login`, `created_at`, `updated_at`) VALUES
(4, 'stu', '56760663', 'student', 0, '2024-11-26 14:45:40', '2024-11-26 15:58:39'),
(5, 'admin1', 'be32', 'admin', 0, '2024-11-26 14:54:57', '2024-11-26 15:54:11'),
(6, 'teacher_22', '784ac634', 'teacher', 0, '2024-11-26 14:57:35', '2024-11-26 16:05:23'),
(8, 'student1', '123', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:56:46'),
(9, 'student2', 'hashed_password_2', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(10, 'student3', 'hashed_password_3', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(11, 'student4', 'hashed_password_4', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(12, 'student5', 'hashed_password_5', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(13, 'student6', 'hashed_password_6', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(14, 'student7', 'hashed_password_7', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(15, 'student8', 'hashed_password_8', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(16, 'student9', 'hashed_password_9', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(17, 'student10', 'hashed_password_10', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(18, 'student11', 'hashed_password_11', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(19, 'student12', 'hashed_password_12', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(20, 'student13', 'hashed_password_13', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(21, 'student14', 'hashed_password_14', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(22, 'student15', 'hashed_password_15', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(23, 'student16', 'hashed_password_16', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(24, 'student17', 'hashed_password_17', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(25, 'student18', 'hashed_password_18', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(26, 'student19', 'hashed_password_19', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(27, 'student20', 'hashed_password_20', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(28, 'student21', 'hashed_password_21', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(29, 'student22', 'hashed_password_22', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(30, 'student23', 'hashed_password_23', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(31, 'student24', 'hashed_password_24', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(32, 'student25', 'hashed_password_25', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(33, 'student26', 'hashed_password_26', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(34, 'student27', 'hashed_password_27', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(35, 'student28', 'hashed_password_28', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(36, 'student29', 'hashed_password_29', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(37, 'student30', 'hashed_password_30', 'student', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(38, 'admin', 'hashed_admin_password', 'admin', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02'),
(39, 'teacher', 'hashed_teacher_password', 'teacher', 1, '2024-11-26 15:53:02', '2024-11-26 15:53:02');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_attendance` (`student_id`,`course_id`,`date`,`session`),
  ADD KEY `course_id` (`course_id`);

--
-- Chỉ mục cho bảng `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

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
-- Chỉ mục cho bảng `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Teachers_Users` (`user_id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=604;

--
-- AUTO_INCREMENT cho bảng `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `csv_templates`
--
ALTER TABLE `csv_templates`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT cho bảng `teachers`
--
ALTER TABLE `teachers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

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
-- Các ràng buộc cho bảng `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `fk_students_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `teachers`
--
ALTER TABLE `teachers`
  ADD CONSTRAINT `FK_Teachers_Users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
