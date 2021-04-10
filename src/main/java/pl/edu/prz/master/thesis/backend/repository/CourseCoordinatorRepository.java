package pl.edu.prz.master.thesis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.prz.master.thesis.backend.entity.CourseCoordinator;

import java.util.Optional;

@Repository
public interface CourseCoordinatorRepository extends JpaRepository<CourseCoordinator, Long> {

    Optional<CourseCoordinator> findByEmail(String email);

}
