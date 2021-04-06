package pl.edu.prz.master.thesis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.prz.master.thesis.backend.entity.CourseCoordinator;

import java.util.Optional;

public interface CourseCoordinatorRepository extends JpaRepository<CourseCoordinator, Long> {
    Optional<CourseCoordinator> findByCourseCoordinatorEmail(String courseCoordinatorEmail);
}
