package pl.edu.prz.master.thesis.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

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

    @Column(name = "UNIT_CODE")
    private String unitCode;

    @Column(name = "NAME", nullable = false, unique = true, updatable = false)
    private String name;

    @Column(name = "DURATION_OF_UNIT", nullable = false)
    private String durationOfUnit;

    @Column(name = "CREDITS", nullable = false)
    private Long credits;

    @Column(name = "WHETHER_ACTIVE")
    private boolean whetherActive;

    @Column(name = "SEMESTER", nullable = false)
    private String semester;

    @Column(name = "DEPARTMENT", nullable = false)
    private String department;

    @Column(name = "NUMBER_OF_HOURS", nullable = false)
    private String numberOfHours;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(SAVE_UPDATE)
    @JoinTable(
            name = "COURSE_HAS_COORDINATORS",
            joinColumns = @JoinColumn(name = "COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_COORDINATOR_ID"))
    private Set<CourseCoordinator> courseCoordinators = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(SAVE_UPDATE)
    @JoinTable(
            name = "STUDENT_HAS_COURSES",
            joinColumns = {@JoinColumn(name = "COURSE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "STUDENT_ID")}
    )
    private Set<Student> students = new HashSet<>();

    @Transient
    private Set<Long> studentIds = new HashSet<>();

    @Transient
    private Set<Long> courseCoordinatorIds = new HashSet<>();

}
