package com.example.diemdanhsv.models;

import java.time.LocalDateTime;

public class Student {
    private int id;
    private String name;
    private int userId;
    private String gender;
    private String status;
    private LocalDateTime date;

    // Constructor with all fields including date
    public Student(int id, String name, int userId, String gender, String status, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.gender = gender;
        this.status = status;
        this.date = date;
    }

    // Existing constructor
    public Student(int id, String name, int userId, String gender, String status) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.gender = gender;
        this.status = status;
    }

    public Student(int id, String name, String gender, String status) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.status = status;
    }
    // Default constructor
    public Student() {
    }

    // Getters and Setters
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}