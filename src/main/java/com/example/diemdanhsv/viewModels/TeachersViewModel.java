package com.example.diemdanhsv.viewModels;

import com.example.diemdanhsv.models.Course;
import com.example.diemdanhsv.models.Teacher;
import com.example.diemdanhsv.repository.CourseRepository;
import com.example.diemdanhsv.repository.TeachersRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.SQLException;
import java.time.LocalDate;

public class TeachersViewModel {
    private final StringProperty name = new SimpleStringProperty();
    private final TeachersRepository _teacherRepo = new TeachersRepository();
    private final CourseRepository _courseRepo = new CourseRepository();

    public TeachersViewModel() {}

    public void getInFoTeacherVM(int userId) throws SQLException {
        Teacher teacher = _teacherRepo.getInfoTeacherLogin(userId);

        this.setName(teacher.getName());
    }

    public void addCourseToDatabaseVM(String nameCourse, LocalDate startDate, LocalDate endDate, String semesterValue) {
        _courseRepo.addCourseToDatabase(nameCourse, startDate, endDate, semesterValue);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
