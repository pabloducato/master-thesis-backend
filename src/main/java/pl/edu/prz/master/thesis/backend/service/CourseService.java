package pl.edu.prz.master.thesis.backend.service;

import pl.edu.prz.master.thesis.backend.dto.CourseDTO;
import pl.edu.prz.master.thesis.backend.entity.Course;

import java.util.List;

public interface CourseService {

    List<CourseDTO> getCourses(String name);

    CourseDTO getCourseById(Long id);

    CourseDTO getCourseByName(String name);

    void createCourse(Course course);

    Course fillEntity(Course course);

    void updateOrAddCourse(Course course, Long id);

    void deactivateCourse(Long id);

}
