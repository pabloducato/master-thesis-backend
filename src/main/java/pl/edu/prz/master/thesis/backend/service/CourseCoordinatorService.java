package pl.edu.prz.master.thesis.backend.service;

import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.CourseCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.entity.CourseCoordinator;
import pl.edu.prz.master.thesis.backend.repository.CourseCoordinatorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseCoordinatorService {
    private final CourseCoordinatorRepository courseCoordinatorRepository;

    public CourseCoordinatorService(CourseCoordinatorRepository courseCoordinatorRepository) {
        this.courseCoordinatorRepository = courseCoordinatorRepository;
    }

    public List<CourseCoordinatorDTO> getCourseCoordinators(String courseCoordinatorEmail) {
        if (courseCoordinatorEmail != null && !courseCoordinatorEmail.equals("")) {
            return Collections.singletonList(getCourseCoordinatorByEmail(courseCoordinatorEmail));
        }

        return courseCoordinatorRepository.findAll()
                .stream()
                .map(CourseCoordinator::mapToDTO)
                .collect(Collectors.toList());
    }

    public CourseCoordinatorDTO getCourseCoordinatorById(Long id) {
        return courseCoordinatorRepository.findById(id).get().mapToDTO();
    }


    public CourseCoordinatorDTO getCourseCoordinatorByEmail(String courseCoordinatorEmail) {
        return courseCoordinatorRepository.findByCourseCoordinatorEmail(courseCoordinatorEmail).orElseThrow(() -> new EntityNotFoundException("Unable to find course coordinator with email " + courseCoordinatorEmail)).mapToDTO();
    }

    public void createCourseCoordinator(CourseCoordinator courseCoordinator) {
        courseCoordinatorRepository.save(fillEntity(courseCoordinator));
    }

    private CourseCoordinator fillEntity(CourseCoordinator courseCoordinator) {
        return courseCoordinator;
    }

    public void updateOrAddCourseCoordinator(CourseCoordinator courseCoordinator, Long id) {
        courseCoordinator.setId(id);
        courseCoordinatorRepository.save(fillEntity(courseCoordinator));
    }

    public void deactivateCourseCoordinator(Long id) {
        CourseCoordinator courseCoordinator = courseCoordinatorRepository.getOne(id);
        courseCoordinatorRepository.deleteById(courseCoordinator.getId());
    }
}
