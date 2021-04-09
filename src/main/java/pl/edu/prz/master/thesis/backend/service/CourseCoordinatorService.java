package pl.edu.prz.master.thesis.backend.service;

import pl.edu.prz.master.thesis.backend.dto.CourseCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.entity.CourseCoordinator;

import java.util.List;

public interface CourseCoordinatorService {

    public List<CourseCoordinatorDTO> getCourseCoordinators(String courseCoordinatorEmail);

    public CourseCoordinatorDTO getCourseCoordinatorById(Long id);

    public CourseCoordinatorDTO getCourseCoordinatorByEmail(String courseCoordinatorEmail);

    public void createCourseCoordinator(CourseCoordinator courseCoordinator);

    private CourseCoordinator fillEntity(CourseCoordinator courseCoordinator) {
        return courseCoordinator;
    }

    public void updateOrAddCourseCoordinator(CourseCoordinator courseCoordinator, Long id);

    public void deactivateCourseCoordinator(Long id);

}
