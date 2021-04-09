package pl.edu.prz.master.thesis.backend.service;

import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.CourseDTO;
import pl.edu.prz.master.thesis.backend.entity.Course;

import java.util.List;

@Service
public class CourseServiceImplementation implements CourseService {

    @Override
    public List<CourseDTO> getCourses(String name) {
        return null;
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        return null;
    }

    @Override
    public CourseDTO getCourseByName(String name) {
        return null;
    }

    @Override
    public void createCourse(Course course) {

    }

    @Override
    public Course fillEntity(Course course) {
        return null;
    }

    @Override
    public void updateOrAddCourse(Course course, Long id) {

    }

    @Override
    public void deactivateCourse(Long id) {

    }
}
