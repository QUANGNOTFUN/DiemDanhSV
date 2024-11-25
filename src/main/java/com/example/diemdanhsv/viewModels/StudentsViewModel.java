package com.example.diemdanhsv.viewModels;

import com.example.diemdanhsv.databaseConnect.DatabaseConnection;
import com.example.diemdanhsv.models.Course;
import com.example.diemdanhsv.models.Student;
import com.example.diemdanhsv.repository.CourseRepository;
import com.example.diemdanhsv.repository.StudentsRepository;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentsViewModel {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty semester = new SimpleIntegerProperty();
    private final ObservableList<Course> courses = FXCollections.observableArrayList();
    private final StudentsRepository _studentRepo = new StudentsRepository();
    private final CourseRepository _courseRepo = new CourseRepository();

    public StudentsViewModel() {}

    public String getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set("MSSV: " + id); // Thêm tiền tố
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set("Tên: " + name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setInfoLogin(Student student) {
        this.id.set("MSSV: " + student.getId());
        this.name.set("Tên: " + student.getName());
    }
    public void setCoursesLogin(List<Course> coursesList) {
        courses.clear();
        courses.addAll(coursesList);
    }

    // Lấy thông tin sinh viên và các môn học
    public void getInfoLoginVM(int userId, int semester, Connection conn) throws SQLException {
        // Lấy thông tin sinh viên từ repository
        Student student = _studentRepo.getInfoLogin(userId, conn);
        List<Course> coursesList = _courseRepo.getCourseLogin(student.getId(), semester, conn);

        // Kiểm tra nếu thông tin sinh viên có tồn tại
        if (student != null) {
            setInfoLogin(student);
            setCoursesLogin(coursesList);
        } else {
            throw new RuntimeException("Không tìm thấy sinh viên với ID: " + userId);
        }
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

    public void removeCourses() {
        this.courses.removeAll();
    }
}
