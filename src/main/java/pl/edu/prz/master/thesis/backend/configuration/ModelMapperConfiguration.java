package pl.edu.prz.master.thesis.backend.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.prz.master.thesis.backend.dto.CourseCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.dto.CourseDTO;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.dto.StudentDTO;
import pl.edu.prz.master.thesis.backend.entity.Course;
import pl.edu.prz.master.thesis.backend.entity.CourseCoordinator;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitution;
import pl.edu.prz.master.thesis.backend.entity.Student;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<Set<Course>, Set<Long>> extractStudentEntityCourseIds = context -> context.getSource() == null ? null :
                context.getSource().stream().map(Course::getId).collect(Collectors.toSet());

        Converter<Set<SendingInstitution>, Set<Long>> extractStudentEntitySendingInstitutionIds = context -> context.getSource() == null ? null :
                context.getSource().stream().map(SendingInstitution::getId).collect(Collectors.toSet());

        Converter<Set<Course>, Set<Long>> extractCourseCoordinatorEntityCourseIds = context -> context.getSource() == null ? null :
                context.getSource().stream().map(Course::getId).collect(Collectors.toSet());

        Converter<Set<Student>, Set<Long>> extractSendingInstitutionEntityStudentIds = context -> context.getSource() == null ? null :
                context.getSource().stream().map(Student::getId).collect(Collectors.toSet());

        Converter<Set<CourseCoordinator>, Set<Long>> extractCourseEntityCourseCoordinatorIds = context -> context.getSource() == null ? null :
                context.getSource().stream().map(CourseCoordinator::getId).collect(Collectors.toSet());

        Converter<Set<Student>, Set<Long>> extractCourseEntityStudentIds = context -> context.getSource() == null ? null :
                context.getSource().stream().map(Student::getId).collect(Collectors.toSet());

        modelMapper.createTypeMap(Student.class, StudentDTO.class)
                .addMappings(mapper -> {
                    mapper.using(extractStudentEntityCourseIds).map(Student::getCourses, StudentDTO::setCourseIds);
                    mapper.using(extractStudentEntitySendingInstitutionIds).map(Student::getSendingInstitutions, StudentDTO::setSendingInstitutionIds);
                });

        modelMapper.createTypeMap(CourseCoordinator.class, CourseCoordinatorDTO.class)
                .addMappings(mapper -> {
                    mapper.using(extractCourseCoordinatorEntityCourseIds).map(CourseCoordinator::getCourses, CourseCoordinatorDTO::setCourseIds);
                });

        modelMapper.createTypeMap(SendingInstitution.class, SendingInstitutionDTO.class)
                .addMappings(mapper -> {
                    mapper.using(extractSendingInstitutionEntityStudentIds).map(SendingInstitution::getStudents, SendingInstitutionDTO::setStudentIds);
                });

        modelMapper.createTypeMap(Course.class, CourseDTO.class)
                .addMappings(mapper -> {
                    mapper.using(extractCourseEntityStudentIds).map(Course::getStudents, CourseDTO::setStudentIds);
                    mapper.using(extractCourseEntityCourseCoordinatorIds).map(Course::getCourseCoordinators, CourseDTO::setCourseCoordinatorIds);
                });

        return modelMapper;
    }

}
