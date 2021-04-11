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
@Table(name = "COURSE_COORDINATORS")
public class CourseCoordinator implements Serializable {

    @Id
    @GeneratedValue(generator = "COURSE_COORDINATOR_SEQUENCE")
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "ACADEMIC_TITLE", nullable = false)
    private String academicTitle;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "FAX", nullable = false)
    private String fax;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(SAVE_UPDATE)
    @JoinTable(
            name = "COURSE_HAS_COORDINATORS",
            joinColumns = @JoinColumn(name = "COURSE_COORDINATOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private Set<Course> courses = new HashSet<>();

    @Transient
    private Set<Long> courseIds = new HashSet<>();

}
