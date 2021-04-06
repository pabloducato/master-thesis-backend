package pl.edu.prz.master.thesis.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.dto.CourseDTO;
import pl.edu.prz.master.thesis.backend.security.TokenHelper;
import pl.edu.prz.master.thesis.backend.service.CourseService;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/courses", produces = APPLICATION_JSON_VALUE)
public class CourseController {

    private final TokenHelper tokenHelper;
    private final CourseService courseService;

    public CourseController(TokenHelper tokenHelper, CourseService courseService) {
        this.tokenHelper = tokenHelper;
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> getCourse(@RequestParam(name = "name", required = false) String name) {
        return courseService.getCourses(name);
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        courseService.createCourse(courseDTO.parseCourse());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PutMapping("/{id}")
    public void updateOrAddCourse(@Valid @RequestBody CourseDTO courseDTO, @PathVariable("id") Long id) {
        courseService.updateOrAddCourse(courseDTO.parseCourse(), id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable("id") Long id) {
        courseService.deactivateCourse(id);
    }
}
