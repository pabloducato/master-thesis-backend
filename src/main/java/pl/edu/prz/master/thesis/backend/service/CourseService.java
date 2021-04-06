package pl.edu.prz.master.thesis.backend.service;

import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.CourseDTO;
import pl.edu.prz.master.thesis.backend.entity.Course;
import pl.edu.prz.master.thesis.backend.repository.CourseCoordinatorRepository;
import pl.edu.prz.master.thesis.backend.repository.CourseRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseCoordinatorRepository courseCoordinatorRepository;

    public CourseService(CourseRepository courseRepository, CourseCoordinatorRepository courseCoordinatorRepository) {
        this.courseRepository = courseRepository;
        this.courseCoordinatorRepository = courseCoordinatorRepository;
    }

    public List<CourseDTO> getCourses(String name) {
        if (name != null && !name.equals("")) {
            return Collections.singletonList(getCourseByName(name));
        }
        return courseRepository.findAll()
                .stream()
                .map(Course::mapToDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long id) {
        return courseRepository.findById(id).get().mapToDTO();
    }

    public CourseDTO getCourseByName(String name) {
        return courseRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Unable to find course with name " + name)).mapToDTO();
    }

    public void createCourse(Course course) {
        courseRepository.save(fillEntity(course));
    }

    private Course fillEntity(Course course) {
        course.setCourseCoordinators(course.getCourseCoordinatorIds()
                .stream()
                .map(courseCoordinatorRepository::getOne)
                .collect(Collectors.toSet()));
        return course;
    }

    public void updateOrAddCourse(Course course, Long id) {
        course.setId(id);
        courseRepository.save(fillEntity(course));
    }

    public void deactivateCourse(Long id) {
        Course course = courseRepository.getOne(id);
        courseRepository.deleteById(course.getId());
    }
}
