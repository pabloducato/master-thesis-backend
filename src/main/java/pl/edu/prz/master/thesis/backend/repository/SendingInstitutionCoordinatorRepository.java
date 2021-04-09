package pl.edu.prz.master.thesis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitutionCoordinator;

import java.util.Optional;

@Repository
public interface SendingInstitutionCoordinatorRepository extends JpaRepository<SendingInstitutionCoordinator, Long> {
    Optional<SendingInstitutionCoordinator> findBySendingInstitutionCoordinatorEmail(String sendingInstitutionCoordinatorEmail);
}
