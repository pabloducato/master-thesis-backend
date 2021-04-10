package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.dto.CourseDTO;
import pl.edu.prz.master.thesis.backend.entity.Course;
import pl.edu.prz.master.thesis.backend.service.CourseService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/courses", produces = APPLICATION_JSON_VALUE)
@Api(tags = "Course Controller")
@CrossOrigin("*")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<CourseDTO> getCourse(@RequestParam(name = "name", required = false) String name) {
        return courseService.getCourses(name);
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        courseService.createCourse(modelMapper.map(courseDTO, Course.class));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @PutMapping("/{id}")
    public void updateOrAddCourse(@Valid @RequestBody CourseDTO courseDTO, @PathVariable("id") Long id) {
        courseService.updateOrAddCourse(modelMapper.map(courseDTO, Course.class), id);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable("id") Long id) {
        courseService.deactivateCourse(id);
    }

}
