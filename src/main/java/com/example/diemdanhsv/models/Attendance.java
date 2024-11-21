package com.example.diemdanhsv.models;

import java.time.LocalDate;

public class Attendance {
    private int id;
    private int studentId;
    private int courseId;
    private LocalDate date; // Thống nhất dùng LocalDate
    private String status;

    // Constructor với Date là LocalDate
    public Attendance(int id, int studentId, int courseId, LocalDate date, String status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
        this.status = status;
    }

    // Constructor mặc định
    public Attendance() {
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
