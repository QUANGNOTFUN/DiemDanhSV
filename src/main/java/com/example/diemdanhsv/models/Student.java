package com.example.diemdanhsv.models;

public class Student {
    private int id;
    private String name;
    private String email;
    private Integer userId;

    // Constructor
    public Student(int id, String name, String email, Integer userId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
