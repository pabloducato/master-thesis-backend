package pl.edu.prz.master.thesis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.prz.master.thesis.backend.entity.ReceivingInstitution;

import java.util.Optional;

public interface ReceivingInstitutionRepository extends JpaRepository<ReceivingInstitution, Long> {
    Optional<ReceivingInstitution> findByReceivingInstitutionName(String receivingInstitutionName);

}
