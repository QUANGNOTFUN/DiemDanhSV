package com.example.diemdanhsv.viewModels;

import com.example.diemdanhsv.models.Course;
import com.example.diemdanhsv.models.Student;
import com.example.diemdanhsv.repository.CourseRepository;
import com.example.diemdanhsv.repository.StudentsRepository;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class StudentsViewModel {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty semester = new SimpleIntegerProperty();
    private final ObservableList<Course> courses = FXCollections.observableArrayList();
    private final StudentsRepository _studentRepo = new StudentsRepository();
    private final CourseRepository _courseRepo = new CourseRepository();

    public StudentsViewModel() {}

    // Lấy thông tin sinh viên và các môn học
    public void getInfoLoginVM(int userId, int semester) throws SQLException {
        // Lấy thông tin sinh viên từ repository
        Student student = _studentRepo.getInfoLogin(userId);
        System.out.println(student.getName());
        if (student == null) {
            throw new RuntimeException("Không tìm thấy thông tin sinh viên với ID: " + userId);
        }

        setId(student.getId());
        setName(student.getName());

        // Lấy danh sách các môn học của sinh viên trong học kỳ
        List<Course> coursesList = _courseRepo.getCourseLogin(student.getId(), semester);

        setCourses(coursesList);
    }

    // get set

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getSemester() {
        return semester.get();
    }

    public IntegerProperty semesterProperty() {
        return semester;
    }

    public ObservableList<Course> getCourses() {
        return courses;
    }

    public Course getCourse(int id) {
        for (Course course : this.courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public void setCourses(List<Course> coursesList) {
        this.courses.clear();
        this.courses.addAll(coursesList);
    }

    public void removeCourses() {
        this.courses.removeAll();
    }

    public int getId() { return id.get(); }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() { return id; }
}
