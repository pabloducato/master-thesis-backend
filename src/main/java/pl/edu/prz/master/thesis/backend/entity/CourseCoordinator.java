package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;
import pl.edu.prz.master.thesis.backend.dto.CourseCoordinatorDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COURSE_COORDINATORS")
public class CourseCoordinator implements Serializable {

    @Id
    @GeneratedValue(generator = "COURSE_COORDINATOR_SEQUENCE")
    private Long id;

    @Column(name = "COURSE_COORDINATOR_EMAIL", nullable = false, unique = true, updatable = false)
    private String courseCoordinatorEmail;

    @Column(name = "COURSE_COORDINATOR_ACADEMIC_TITLE", nullable = false)
    private String courseCoordinatorAcademicTitle;

    @Column(name = "COURSE_COORDINATOR_FIRST_NAME", nullable = false)
    private String courseCoordinatorFirstName;

    @Column(name = "COURSE_COORDINATOR_LAST_NAME", nullable = false)
    private String courseCoordinatorLastName;

    @Column(name = "COURSE_COORDINATOR_PHONE", nullable = false)
    private String courseCoordinatorPhone;

    @Column(name = "COURSE_COORDINATOR_FAX", nullable = false)
    private String courseCoordinatorFax;

    @OneToMany(mappedBy = "course_coordinator")
    private List<Course> courses;


    public CourseCoordinatorDTO mapToDTO() {
        return CourseCoordinatorDTO.builder()
                .id(this.getId())
                .courseCoordinatorEmail(this.getCourseCoordinatorEmail())
                .courseCoordinatorAcademicTitle(this.getCourseCoordinatorAcademicTitle())
                .courseCoordinatorFirstName(this.getCourseCoordinatorFirstName())
                .courseCoordinatorLastName(this.getCourseCoordinatorLastName())
                .courseCoordinatorPhone(this.getCourseCoordinatorPhone())
                .courseCoordinatorFax(this.getCourseCoordinatorFax())
                .courses(this.getCourses())
                .build();
    }
}
