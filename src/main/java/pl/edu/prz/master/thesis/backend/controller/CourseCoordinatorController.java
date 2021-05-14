package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.dto.CourseCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.entity.CourseCoordinator;
import pl.edu.prz.master.thesis.backend.service.CourseCoordinatorService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/course_coordinators", produces = APPLICATION_JSON_VALUE)
@Api(tags = "Course Coordinator Controller")
@CrossOrigin("*")
@AllArgsConstructor
public class CourseCoordinatorController {

    private final CourseCoordinatorService courseCoordinatorService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<CourseCoordinatorDTO> getCourseCoordinator(@RequestParam(name = "courseCoordinatorEmail", required = false) String courseCoordinatorEmail) {
        return courseCoordinatorService.getCourseCoordinators(courseCoordinatorEmail);
    }

    @GetMapping("/{id}")
    public CourseCoordinatorDTO getCourseCoordinatorById(@PathVariable Long id) {
        return courseCoordinatorService.getCourseCoordinatorById(id);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourseCoordinator(@Valid @RequestBody CourseCoordinatorDTO courseCoordinatorDTO) {
        courseCoordinatorService.createCourseCoordinator(modelMapper.map(courseCoordinatorDTO, CourseCoordinator.class));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @PutMapping("/{id}")
    public void updateOrAddCourseCoordinator(@Valid @RequestBody CourseCoordinatorDTO courseCoordinatorDTO, @PathVariable("id") Long id) {
        courseCoordinatorService.updateOrAddCourseCoordinator(modelMapper.map(courseCoordinatorDTO, CourseCoordinator.class), id);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourseCoordinator(@PathVariable("id") Long id) {
        courseCoordinatorService.deactivateCourseCoordinator(id);
    }
}
