package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.CourseDTO;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.dto.StudentDTO;
import pl.edu.prz.master.thesis.backend.entity.Student;
import pl.edu.prz.master.thesis.backend.repository.CourseRepository;
import pl.edu.prz.master.thesis.backend.repository.SendingInstitutionRepository;
import pl.edu.prz.master.thesis.backend.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final SendingInstitutionRepository sendingInstitutionRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getStudents(String studentEmail) {
        if (studentEmail != null && !studentEmail.equals("")) {
            return Collections.singletonList(getStudentByEmail(studentEmail));
        }
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Unable to find student with id " + id));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Unable to find student with email " + email));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public void createStudent(Student student) {
        studentRepository.save(fillEntity(student));
    }

    @Override
    public Student fillEntity(Student student) {
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

    @Override
    public List<CourseDTO> getStudentCoursesById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Unable to find com.example.erasmus.model.Student with id " + id);
        }
        Student student = studentRepository.getOne(id);

        return courseRepository.findByStudentsContaining(student)
                .stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SendingInstitutionDTO> getStudentSendingInstitutionsById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Unable to find student with id " + id);
        }
        Student student = studentRepository.getOne(id);
        return sendingInstitutionRepository.findByStudentsContaining(student)
                .stream()
                .map(sendingInstitution -> modelMapper.map(sendingInstitution, SendingInstitutionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateOrAddStudent(Student student, Long id) {
        student.setId(id);
        studentRepository.save(fillEntity(student));
    }

    @Override
    public void deactivateStudent(Long id) {
        Student student = studentRepository.getOne(id);
        studentRepository.deleteById(student.getId());
    }

    @Override
    public List<StudentDTO> getAllStudentsByAcademicYear(String academicYear) {
        return studentRepository.findStudentsByAcademicYear(academicYear)
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

}
