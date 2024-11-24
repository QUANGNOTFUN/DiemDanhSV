package com.example.diemdanhsv.models;

import java.time.LocalDate;

public class Course {
    private int id;
    private String name;
    private String code;
    private LocalDate startDate;  // Đổi sang LocalDate để phù hợp với kiểu dữ liệu trong cơ sở dữ liệu
    private LocalDate endDate;    // Đổi sang LocalDate để phù hợp với kiểu dữ liệu trong cơ sở dữ liệu
    private String day;
    private String academicYear;
    private int semester;
    private LocalDate createdAt;  // Cập nhật lại kiểu dữ liệu
    private LocalDate updatedAt;  // Cập nhật lại kiểu dữ liệu

    // Constructor
    public Course(int id, String name, String code, LocalDate startDate, LocalDate endDate,
                  String day, String academicYear, int semester, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.day = day;
        this.academicYear = academicYear;
        this.semester = semester;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getter and Setter Methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
