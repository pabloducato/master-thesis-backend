package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.CourseDTO;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.dto.StudentDTO;
import pl.edu.prz.master.thesis.backend.entity.Student;
import pl.edu.prz.master.thesis.backend.repository.CourseRepository;
import pl.edu.prz.master.thesis.backend.repository.SendingInstitutionRepository;
import pl.edu.prz.master.thesis.backend.repository.StudentRepository;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final SendingInstitutionRepository sendingInstitutionRepository;

    @Override
    public List<StudentDTO> getStudents(String studentEmail) {
        return null;
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        return null;
    }

    @Override
    public StudentDTO getStudentByEmail(String studentEmail) {
        return null;
    }

    @Override
    public void createStudent(Student student) {

    }

    @Override
    public Student fillEntity(Student student) {
        return null;
    }

    @Override
    public List<CourseDTO> getStudentCoursesById(Long id) {
        return null;
    }

    @Override
    public List<SendingInstitutionDTO> getStudentSendingInstitutionsById(Long id) {
        return null;
    }

    @Override
    public void updateOrAddStudent(Student student, Long id) {

    }

    @Override
    public void deactivateStudent(Long id) {

    }

    @Override
    public List<Student> getAllStudentsByAcademicYear(String academicYear) {
        return null;
    }
}
