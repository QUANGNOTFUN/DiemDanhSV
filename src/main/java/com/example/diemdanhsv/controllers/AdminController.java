package com.example.diemdanhsv.controllers;

import com.example.diemdanhsv.models.Course;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;

public class AdminController {
    @FXML
    private TextField CourseNameField;
    @FXML
    private ListView<String> studentList;

//    @FXML
//    public void createCourse() {
//        String CourseName = CourseNameField.getText();
//        Course Course = new Course(CourseName);
//        // Thêm logic lưu môn học vào cơ sở dữ liệu
//    }

    @FXML
    public void addStudentToCourse() {
        // Logic để thêm danh sách sinh viên vào môn học từ danh sách hoặc thêm mới
    }
}
