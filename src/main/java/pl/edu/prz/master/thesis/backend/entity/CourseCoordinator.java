package pl.edu.prz.master.thesis.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.Nullable;
import pl.edu.prz.master.thesis.backend.dto.CourseCoordinatorDTO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "CourseCoordinators")
@Data
@EqualsAndHashCode(exclude = {"id", "ownedCourseIds", "ownedCourses", "courseIds", "courses"})
@ToString(exclude = {"ownedCourseIds", "ownedCourses", "courses"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCoordinator implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Email
    private String courseCoordinatorEmail;

    @NotNull
    private String courseCoordinatorAcademicTitle;

    @NotNull
    private String courseCoordinatorFirstName;

    @NotNull
    private String courseCoordinatorLastName;

    @Nullable
    private String courseCoordinatorPhone;

    @Nullable
    private String courseCoordinatorFax;

    @Nullable
    @Transient
    private Set<Long> courseIds = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinTable(
            name = "CourseOwnedCoordinators",
            joinColumns = {@JoinColumn(name = "course_coordinator_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    @Builder.Default
    private Set<Course> courses = new HashSet<>();


    public CourseCoordinatorDTO mapToDTO() {
        return CourseCoordinatorDTO.builder()
                .id(this.getId())
                .courseCoordinatorEmail(this.getCourseCoordinatorEmail())
                .courseCoordinatorAcademicTitle(this.getCourseCoordinatorAcademicTitle())
                .courseCoordinatorFirstName(this.getCourseCoordinatorFirstName())
                .courseCoordinatorLastName(this.getCourseCoordinatorLastName())
                .courseCoordinatorPhone(this.getCourseCoordinatorPhone())
                .courseCoordinatorFax(this.getCourseCoordinatorFax())
                .courseIds(this.getCourses().stream()
                        .map(Course::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
