package pl.edu.prz.master.thesis.backend.service;

import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.CourseDTO;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.dto.StudentDTO;
import pl.edu.prz.master.thesis.backend.entity.Course;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitution;
import pl.edu.prz.master.thesis.backend.entity.Student;
import pl.edu.prz.master.thesis.backend.repository.CourseRepository;
import pl.edu.prz.master.thesis.backend.repository.SendingInstitutionRepository;
import pl.edu.prz.master.thesis.backend.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final SendingInstitutionRepository sendingInstitutionRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, SendingInstitutionRepository sendingInstitutionRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.sendingInstitutionRepository = sendingInstitutionRepository;
    }

    public List<StudentDTO> getStudents(String studentEmail) {
        if (studentEmail != null && !studentEmail.equals("")) {
            return Collections.singletonList(getStudentByEmail(studentEmail));
        }

        return studentRepository.findAll()
                .stream()
                .map(Student::mapToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id) {
        return studentRepository.findById(id).get().mapToDTO();
    }


    public StudentDTO getStudentByEmail(String studentEmail) {
        return studentRepository.findByStudentEmail(studentEmail).orElseThrow(() -> new EntityNotFoundException("Unable to find student with email " + studentEmail)).mapToDTO();
    }

    public void createStudent(Student student) {
        studentRepository.save(fillEntity(student));
    }

    private Student fillEntity(Student student) {
        student.setCourses(student.getCourseIds()
                .stream()
                .map(courseRepository::getOne)
                .collect(Collectors.toSet()));
        student.setSendingInstitutions(student.getSendingInstitutionIds()
                .stream()
                .map(sendingInstitutionRepository::getOne)
                .collect(Collectors.toSet()));
        return student;
    }

    public List<CourseDTO> getStudentCoursesById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Unable to find com.example.erasmus.model.Student with id " + id);
        }
        Student student = studentRepository.getOne(id);

        return courseRepository.findByStudentsContaining(student)
                .stream()
                .map(Course::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<SendingInstitutionDTO> getStudentSendingInstitutionsById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Unable to find com.example.erasmus.model.Student with id " + id);
        }
        Student student = studentRepository.getOne(id);

        return sendingInstitutionRepository.findByStudentsContaining(student)
                .stream()
                .map(SendingInstitution::mapToDTO)
                .collect(Collectors.toList());
    }

    public void updateOrAddStudent(Student student, Long id) {
        student.setId(id);
        studentRepository.save(fillEntity(student));
    }

    public void deactivateStudent(Long id) {
        Student student = studentRepository.getOne(id);
        studentRepository.deleteById(student.getId());
    }

    public List<Student> getAllStudentsByAcademicYear(String academicYear) {
        return studentRepository.findStudentsByAcademicYear(academicYear);
    }

}
