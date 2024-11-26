package com.example.diemdanhsv.models;

import java.time.LocalDate;

public class Attendance {
    private int id;
    private int studentId;
    private int courseId;
    private LocalDate date;
    private int session;
    private String status;
    private String studentName;
    private String courseName;
    private String gender;
    private String Session;  // Thêm trường session

    // Constructor với tất cả các trường
    public Attendance(int id, int studentId, int courseId, LocalDate date, String status, String studentName, String gender, String Session) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
        this.status = status;
        this.studentName = studentName;
        this.gender = gender;
        this.Session = Session;  // Khởi tạo session
    }

    // Constructor khác với tất cả các trường, bao gồm session
    public Attendance(int id, int studentId, LocalDate date, String status, String studentName, String gender, String courseName, int courseId, String session) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.status = status;
        this.studentName = studentName;
        this.gender = gender;
        this.courseName = courseName;
        this.courseId = courseId; // Khởi tạo courseId
        this.Session = session;  // Khởi tạo session
    }

    // Constructor mặc định
    public Attendance() {
    }

    public Attendance(int id, int studentId, int courseId, LocalDate date, String status, String studentName, String gender) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
        this.status = status;
        this.studentName = studentName;
        this.gender = gender;
    }

    // Getters và Setters
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter và Setter cho session
    public String getSession() {
        return Session;
    }

    public void setSession(String Session) {
        this.Session = Session;
    }

    public int getSessionInt() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }
}
