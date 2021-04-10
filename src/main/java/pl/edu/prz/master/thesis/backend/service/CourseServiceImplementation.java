package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
@Slf4j
@AllArgsConstructor
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseCoordinatorRepository courseCoordinatorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CourseDTO> getCourses(String name) {
        if (name != null && !name.equals("")) {
            return Collections.singletonList(getCourseByName(name));
        }
        return courseRepository.findAll()
                .stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Unable to find course with id " + id));
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public CourseDTO getCourseByName(String name) {
        Course course = courseRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Unable to find course with name " + name));
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public void createCourse(Course course) {
        courseRepository.save(fillEntity(course));
    }

    @Override
    public Course fillEntity(Course course) {
        course.setCourseCoordinators(course.getCourseCoordinatorIds()
                .stream()
                .map(courseCoordinatorRepository::getOne)
                .collect(Collectors.toSet()));
        return course;
    }

    @Override
    public void updateOrAddCourse(Course course, Long id) {
        course.setId(id);
        courseRepository.save(fillEntity(course));
    }

    @Override
    public void deactivateCourse(Long id) {
        Course course = courseRepository.getOne(id);
        courseRepository.deleteById(course.getId());
    }
}
