package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.CourseCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.entity.CourseCoordinator;
import pl.edu.prz.master.thesis.backend.repository.CourseCoordinatorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CourseCoordinatorServiceImplementation implements CourseCoordinatorService {

    private final CourseCoordinatorRepository courseCoordinatorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CourseCoordinatorDTO> getCourseCoordinators(String courseCoordinatorEmail) {
        if (courseCoordinatorEmail != null && !courseCoordinatorEmail.equals("")) {
            return Collections.singletonList(getCourseCoordinatorByEmail(courseCoordinatorEmail));
        }

        return courseCoordinatorRepository.findAll()
                .stream()
                .map(courseCoordinator -> modelMapper.map(courseCoordinator, CourseCoordinatorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseCoordinatorDTO getCourseCoordinatorById(Long id) {
        CourseCoordinator courseCoordinator = courseCoordinatorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Unable to find course coordinator with id " + id));
        return modelMapper.map(courseCoordinator, CourseCoordinatorDTO.class);
    }

    @Override
    public CourseCoordinatorDTO getCourseCoordinatorByEmail(String email) {
        CourseCoordinator courseCoordinator = courseCoordinatorRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Unable to find course coordinator with email " + email));
        return modelMapper.map(courseCoordinator, CourseCoordinatorDTO.class);
    }

    @Override
    public void createCourseCoordinator(CourseCoordinator courseCoordinator) {
        courseCoordinatorRepository.save(fillEntity(courseCoordinator));
    }

    @Override
    public CourseCoordinator fillEntity(CourseCoordinator courseCoordinator) {
        return courseCoordinator;
    }

    @Override
    public void updateOrAddCourseCoordinator(CourseCoordinator courseCoordinator, Long id) {
        courseCoordinator.setId(id);
        courseCoordinatorRepository.save(fillEntity(courseCoordinator));
    }

    @Override
    public void deactivateCourseCoordinator(Long id) {
        CourseCoordinator courseCoordinator = courseCoordinatorRepository.getOne(id);
        courseCoordinatorRepository.deleteById(courseCoordinator.getId());
    }
}
