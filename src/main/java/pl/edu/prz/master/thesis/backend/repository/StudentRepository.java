package pl.edu.prz.master.thesis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.prz.master.thesis.backend.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentEmail(String studentEmail);

    List<Student> findAll();

    List<Student> findStudentsByAcademicYear(String academicYear);
}
