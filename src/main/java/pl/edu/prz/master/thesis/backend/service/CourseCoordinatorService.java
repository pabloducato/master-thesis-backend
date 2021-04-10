package pl.edu.prz.master.thesis.backend.service;

import pl.edu.prz.master.thesis.backend.dto.CourseCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.entity.CourseCoordinator;

import java.util.List;

public interface CourseCoordinatorService {

    List<CourseCoordinatorDTO> getCourseCoordinators(String courseCoordinatorEmail);

    CourseCoordinatorDTO getCourseCoordinatorById(Long id);

    CourseCoordinatorDTO getCourseCoordinatorByEmail(String courseCoordinatorEmail);

    void createCourseCoordinator(CourseCoordinator courseCoordinator);

    CourseCoordinator fillEntity(CourseCoordinator courseCoordinator);

    void updateOrAddCourseCoordinator(CourseCoordinator courseCoordinator, Long id);

    void deactivateCourseCoordinator(Long id);

}
