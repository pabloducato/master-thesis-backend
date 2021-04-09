package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;
import pl.edu.prz.master.thesis.backend.dto.CourseDTO;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COURSES")
public class Course implements Serializable {

    @Id
    @GeneratedValue(generator = "COURSE_SEQUENCE")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "STUDENT_ID", nullable = false, updatable = false, referencedColumnName = "ID")
    private Student student;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "COURSE_COORDINATOR_ID", nullable = false, updatable = false, referencedColumnName = "ID")
    private CourseCoordinator courseCoordinator;

    @Column(name = "COURSE_UNIT_CODE")
    private String courseUnitCode;

    @Column(name = "COURSE_NAME", nullable = false, unique = true, updatable = false)
    private String courseName;

    @Column(name = "COURSE_DURATION_OF_UNIT", nullable = false)
    private String courseDurationOfUnit;

    @Column(name = "COURSE_CREDITS", nullable = false)
    private Long courseCredits;

    @Column(name = "COURSE_IS_ACTIVE")
    private boolean courseActive;

    @Column(name = "COURSE_SEMESTER", nullable = false)
    private String courseSemester;

    @Column(name = "COURSE_DEPARTMENT", nullable = false)
    private String courseDepartment;

    @Column(name = "COURSE_NUMBER_OF_HOURS", nullable = false)
    private String courseNumberOfHours;

}
