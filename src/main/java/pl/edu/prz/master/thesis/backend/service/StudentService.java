package pl.edu.prz.master.thesis.backend.service;

import pl.edu.prz.master.thesis.backend.dto.CourseDTO;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.dto.StudentDTO;
import pl.edu.prz.master.thesis.backend.entity.Student;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getStudents(String studentEmail);

    StudentDTO getStudentById(Long id);

    StudentDTO getStudentByEmail(String studentEmail);

    void createStudent(Student student);

    Student fillEntity(Student student);

    List<CourseDTO> getStudentCoursesById(Long id);

    List<SendingInstitutionDTO> getStudentSendingInstitutionsById(Long id);

    void updateOrAddStudent(Student student, Long id);

    void deactivateStudent(Long id);

    List<StudentDTO> getAllStudentsByAcademicYear(String academicYear);

}
