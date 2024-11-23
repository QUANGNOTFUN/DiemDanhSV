package com.example.diemdanhsv.models;

import java.time.LocalDate;

public class Attendance {
    private int id;
    private int studentId;
    private int courseId;
    private LocalDate date;
    private String status;
    private String studentName;
    private String gender;


    // Constructor with all fields
    public Attendance(int id, int studentId, int courseId, LocalDate date, String status, String studentName, String gender) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
        this.status = status;
        this.studentName = studentName;
        this.gender = gender;
    }

    // Default constructor
    public Attendance() {
    }

    // Getters and Setters
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
