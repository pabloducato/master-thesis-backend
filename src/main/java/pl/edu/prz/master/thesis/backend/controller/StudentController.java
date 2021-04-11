package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.dto.CourseDTO;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.dto.StudentDTO;
import pl.edu.prz.master.thesis.backend.entity.Student;
import pl.edu.prz.master.thesis.backend.service.StudentService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = "Student Controller")
@RequestMapping(value = "/api/students", produces = APPLICATION_JSON_VALUE)
@CrossOrigin("*")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}/courses")
    public List<CourseDTO> getStudentCoursesById(@PathVariable Long id) {
        return studentService.getStudentCoursesById(id);
    }

    @GetMapping("/{id}/sending_institutions")
    public List<SendingInstitutionDTO> getStudentSendingInstitutionsById(@PathVariable Long id) {
        return studentService.getStudentSendingInstitutionsById(id);
    }

    @GetMapping
    public List<StudentDTO> getStudent(@RequestParam(name = "studentEmail", required = false) String studentEmail) {
        return studentService.getStudents(studentEmail);
    }

    @GetMapping("/{academicYear}/season")
    public List<StudentDTO> getAllStudentsByAcademicYear(@PathVariable("academicYear") String academicYear) {
        return studentService.getAllStudentsByAcademicYear(academicYear);
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.createStudent(modelMapper.map(studentDTO, Student.class));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @PutMapping("/{id}")
    public void updateOrAddStudent(@Valid @RequestBody StudentDTO studentDTO, @PathVariable("id") Long id) {
        studentService.updateOrAddStudent(modelMapper.map(studentDTO, Student.class), id);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deactivateStudent(id);
    }

}
