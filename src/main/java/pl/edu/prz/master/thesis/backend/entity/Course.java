package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    @ManyToMany(mappedBy = "studentCourses")
    private Set<Student> students;

}
