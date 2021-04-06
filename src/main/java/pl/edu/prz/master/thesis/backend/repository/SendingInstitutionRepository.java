package pl.edu.prz.master.thesis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitution;
import pl.edu.prz.master.thesis.backend.entity.Student;

import java.util.List;
import java.util.Optional;

public interface SendingInstitutionRepository extends JpaRepository<SendingInstitution, Long> {
    List<SendingInstitution> findByStudentsContaining(Student student);

    Optional<SendingInstitution> findBySendingInstitutionName(String sendingInstitutionName);

}
