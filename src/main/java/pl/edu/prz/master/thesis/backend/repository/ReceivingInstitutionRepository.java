package pl.edu.prz.master.thesis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.prz.master.thesis.backend.entity.ReceivingInstitution;

import java.util.Optional;

@Repository
public interface ReceivingInstitutionRepository extends JpaRepository<ReceivingInstitution, Long> {

    Optional<ReceivingInstitution> findByName(String name);

}
