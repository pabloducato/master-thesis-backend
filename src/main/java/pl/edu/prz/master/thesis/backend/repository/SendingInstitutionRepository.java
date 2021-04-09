package pl.edu.prz.master.thesis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitution;

@Repository
public interface SendingInstitutionRepository extends JpaRepository<SendingInstitution, Long> {

}
