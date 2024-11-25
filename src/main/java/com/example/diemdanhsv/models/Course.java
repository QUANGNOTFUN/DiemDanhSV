package com.example.diemdanhsv.models;

import java.time.LocalDate;

public class Course {
    private int id;
    private String name;
    private LocalDate startDate; // Dùng LocalDate thay vì String
    private LocalDate endDate;
    private String day;
    private String academicYear;
    private int semester;

    // Constructor không tham số
    public Course() {
    }

    // Constructor đầy đủ tham số
    public Course(int id, String name, LocalDate startDate, LocalDate endDate, String day, String academicYear, int semester) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.day = day;
        this.academicYear = academicYear;
        this.semester = semester;
    }

    // Getter và Setter
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", day='" + day + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", semester=" + semester +
                '}';
    }
}
