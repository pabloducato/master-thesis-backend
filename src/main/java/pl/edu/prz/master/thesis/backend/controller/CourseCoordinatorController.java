package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.dto.CourseCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.repository.CourseCoordinatorRepository;
import pl.edu.prz.master.thesis.backend.security.TokenHelper;
import pl.edu.prz.master.thesis.backend.service.CourseCoordinatorService;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = "Course Coordinator Controller")
@RequestMapping(value = "/api/course_coordinators", produces = APPLICATION_JSON_VALUE)
public class CourseCoordinatorController {

    private final CourseCoordinatorService courseCoordinatorService;
    private final TokenHelper tokenHelper;
    private final CourseCoordinatorRepository courseCoordinatorRepository;

    public CourseCoordinatorController(CourseCoordinatorService courseCoordinatorService, TokenHelper tokenHelper, CourseCoordinatorRepository courseCoordinatorRepository) {
        this.courseCoordinatorService = courseCoordinatorService;
        this.tokenHelper = tokenHelper;
        this.courseCoordinatorRepository = courseCoordinatorRepository;
    }

    @GetMapping
    public List<CourseCoordinatorDTO> getCourseCoordinator(@RequestParam(name = "courseCoordinatorEmail", required = false) String courseCoordinatorEmail) {
        return courseCoordinatorService.getCourseCoordinators(courseCoordinatorEmail);
    }

    @GetMapping("/{id}")
    public CourseCoordinatorDTO getCourseCoordinatorById(@PathVariable Long id) {
        return courseCoordinatorService.getCourseCoordinatorById(id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourseCoordinator(@Valid @RequestBody CourseCoordinatorDTO courseCoordinatorDTO) {
        courseCoordinatorService.createCourseCoordinator(courseCoordinatorDTO.parseCourseCoordinator());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PutMapping("/{id}")
    public void updateOrAddCourseCoordinator(@Valid @RequestBody CourseCoordinatorDTO courseCoordinatorDTO, @PathVariable("id") Long id) {
        courseCoordinatorService.updateOrAddCourseCoordinator(courseCoordinatorDTO.parseCourseCoordinator(), id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourseCoordinator(@PathVariable("id") Long id) {
        courseCoordinatorService.deactivateCourseCoordinator(id);
    }
}
