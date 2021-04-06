package pl.edu.prz.master.thesis.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.lang.Nullable;
import pl.edu.prz.master.thesis.backend.dto.CourseDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Entity
@Table(name = "Courses")
@Data
@EqualsAndHashCode(exclude = {"id", "students", "studentIds", "owner", "courseCoordinatorIds", "courseCoordinators"})
@ToString(exclude = {"students", "owner", "courseCoordinators"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_unit_code")
    private String courseUnitCode;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String durationOfCourseUnit;

    @NotNull
    private Long credits;

    private boolean active;

    @NotNull
    private String semester;

    @NotNull
    private String department;

    @NotNull
    private String numberOfHours;

    @Nullable
    @Transient
    private Set<Long> studentIds = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(
            name = "StudentCourses",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    @Builder.Default
    private Set<Student> students = new HashSet<>();

    @Transient
    private Set<Long> courseCoordinatorIds = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(SAVE_UPDATE)
    @JoinTable(
            name = "CourseOwnedCoordinators",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_coordinator_id")}
    )
    @Builder.Default
    private Set<CourseCoordinator> courseCoordinators = new HashSet<>();

    public CourseDTO mapToDTO() {
        return CourseDTO.builder()
                .id(this.getId())
                .courseUnitCode(this.getCourseUnitCode())
                .name(this.getName())
                .durationOfCourseUnit(this.getDurationOfCourseUnit())
                .credits(this.getCredits())
                .active(this.isActive())
                .semester(this.getSemester())
                .department(this.getDepartment())
                .numberOfHours(this.getNumberOfHours())
                .studentIds(this.getStudents().stream()
                        .map(Student::getId)
                        .collect(Collectors.toList()))
                .courseCoordinatorIds(this.getCourseCoordinators().stream()
                        .map(CourseCoordinator::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
