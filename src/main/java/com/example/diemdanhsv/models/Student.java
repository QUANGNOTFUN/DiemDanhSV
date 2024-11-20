package com.example.diemdanhsv.models;
import javafx.beans.property.*;

public class Student {
    private int id;
    private String name;
    private String email;
    private Integer userId;
    private BooleanProperty selected; // Thuộc tính checkbox

    // Constructor
    public Student(int id, String name, String email, Integer userId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.selected = new SimpleBooleanProperty(false); // Mặc định là chưa chọn

    }

    public Student(){
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
    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
    public BooleanProperty selectedProperty() {
        return selected;
    }
}
