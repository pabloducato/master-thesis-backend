package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.CourseCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.entity.CourseCoordinator;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CourseCoordinatorServiceImplementation implements CourseCoordinatorService {

    @Override
    public List<CourseCoordinatorDTO> getCourseCoordinators(String courseCoordinatorEmail) {
        return null;
    }

    @Override
    public CourseCoordinatorDTO getCourseCoordinatorById(Long id) {
        return null;
    }

    @Override
    public CourseCoordinatorDTO getCourseCoordinatorByEmail(String courseCoordinatorEmail) {
        return null;
    }

    @Override
    public void createCourseCoordinator(CourseCoordinator courseCoordinator) {

    }

    @Override
    public void updateOrAddCourseCoordinator(CourseCoordinator courseCoordinator, Long id) {

    }

    @Override
    public void deactivateCourseCoordinator(Long id) {

    }
}
