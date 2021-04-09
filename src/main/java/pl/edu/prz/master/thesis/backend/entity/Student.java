package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.edu.prz.master.thesis.backend.enums.DegreeOfStudy;
import pl.edu.prz.master.thesis.backend.enums.Semester;
import pl.edu.prz.master.thesis.backend.enums.Sex;
import pl.edu.prz.master.thesis.backend.enums.StudyCycle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STUDENTS")
public class Student implements Serializable {

    @Id
    @GeneratedValue(generator = "STUDENT_SEQUENCE")
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "MATRICULATION_NUMBER", nullable = false)
    private Long matriculationNumber;

    @Column(name = "ACADEMIC_YEAR", nullable = false)
    private String academicYear;

    @Column(name = "FIELD_OF_STUDY", nullable = false)
    private String fieldOfStudy;

    @Column(name = "DEPARTMENT", nullable = false)
    private String department;

    @Column(name = "DEGREE_OF_STUDY", nullable = false)
    @Enumerated(EnumType.STRING)
    private DegreeOfStudy degreeOfStudy;

    @Column(name = "SEMESTER", nullable = false)
    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "STUDENT_LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private Date dateOfBirth;

    @Column(name = "PERIOD_OF_STUDY_FROM", nullable = false)
    private Date periodOfStudyFrom;

    @Column(name = "PERIOD_OF_STUDY_UNTIL", nullable = false)
    private Date periodOfStudyUntil;

    @Column(name = "PLACE_OF_BIRTH", nullable = false)
    private String placeOfBirth;

    @Column(name = "NATIONALITY", nullable = false)
    private String nationality;

    @Column(name = "CURRENT_ADDRESS", nullable = false)
    private String currentAddress;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "SEX", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex = Sex.HIDDEN;

    @Column(name = "STUDY_CYCLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudyCycle studyCycle;

    @Column(name = "PHOTO_BLOB")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] photoBlob;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "STUDENT_HAS_COURSES",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private Set<Course> studentCourses;

}
