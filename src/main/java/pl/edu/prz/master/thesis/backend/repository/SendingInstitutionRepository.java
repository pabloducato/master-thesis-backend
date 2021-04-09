package pl.edu.prz.master.thesis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitution;

import java.util.Optional;

@Repository
public interface SendingInstitutionRepository extends JpaRepository<SendingInstitution, Long> {
    Optional<SendingInstitution> findBySendingInstitutionName(String sendingInstitutionName);

}
