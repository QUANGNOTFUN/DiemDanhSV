package com.example.diemdanhsv.viewModels;

import com.example.diemdanhsv.models.Course;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentsViewModel {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty className = new SimpleStringProperty();
    private final ObservableList<Course> courses = FXCollections.observableArrayList();

    public StudentsViewModel() {}

    public StudentsViewModel(String id, String name, String className, ObservableList<Course> courses) {
        this.id.set(id);
        this.name.set(name);
        this.className.set(className);
        if (courses != null) {
            this.courses.addAll(courses);
        }
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getClassName() {
        return className.get();
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    public ObservableList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }
}
