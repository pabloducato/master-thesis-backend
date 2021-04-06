package pl.edu.prz.master.thesis.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;
import pl.edu.prz.master.thesis.backend.dto.StudentDTO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Entity
@Table(name = "Students")
@Data
@EqualsAndHashCode(exclude = {"id", "courseIds", "courses", "sendingInstitutionIds", "sendingInstitutions"})
@ToString(exclude = {"courseIds", "courses", "sendingInstitutionIds", "sendingInstitutions"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Email
    private String studentEmail;

    @NotNull
    private Long studentMatriculationNumber;

    @NotNull
    private String academicYear;

    @NotNull
    private String fieldOfStudy;

    @NotNull
    private String department;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DegreeOfStudy degreeOfStudy;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Nullable
    private String departmentalCoordinatorAcademicTitle;

    @Nullable
    private String departmentalCoordinatorFirstName;

    @Nullable
    private String departmentalCoordinatorLastName;

    @Nullable
    private String departmentalCoordinatorPhone;

    @Nullable
    private String departmentalCoordinatorFax;

    @Nullable
    private String departmentalCoordinatorEmail;

    @NotNull
    private String studentFirstName;

    @NotNull
    private String studentLastName;

    @NotNull
    private Date studentDateOfBirth;

    @NotNull
    private Date studentPeriodOfStudyFrom;

    @NotNull
    private Date studentPeriodOfStudyUntil;

    @NotNull
    private String studentPlaceOfBirth;

    @NotNull
    private String studentNationality;

    @Nullable
    private String studentCurrentAddress;

    @Nullable
    private String studentPhone;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Sex studentSex = Sex.HIDDEN;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StudyCycle studyCycle;

    @Column(name="photo_blob")
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] photoBlob;

    @Transient
    private Set<Long> courseIds = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(SAVE_UPDATE)
    @JoinTable(
            name = "StudentCourses",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    @Builder.Default
    private Set<Course> courses = new HashSet<>();

    @Transient
    private Set<Long> sendingInstitutionIds = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(SAVE_UPDATE)
    @JoinTable(
            name = "StudentSendingInstitutions",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "sending_institution_id")}
    )
    @Builder.Default
    private Set<SendingInstitution> sendingInstitutions = new HashSet<>();


    public enum Semester {
        S,
        W
    }

    public enum Sex {
        MALE,
        FEMALE,
        HIDDEN
    }

    public enum StudyCycle {
        FULLTIME,
        EXTERNAL
    }

    public enum DegreeOfStudy {
        BACHELOR,
        MASTER
    }

    public StudentDTO mapToDTO() {
        return StudentDTO.builder()
                .id(this.getId())
                .studentEmail(this.getStudentEmail())
                .studentMatriculationNumber(this.getStudentMatriculationNumber())
                .academicYear(this.getAcademicYear())
                .fieldOfStudy(this.getFieldOfStudy())
                .department(this.getDepartment())
                .studentFirstName(this.getStudentFirstName())
                .studentLastName(this.getStudentLastName())
                .studentDateOfBirth(this.getStudentDateOfBirth())
                .studentPeriodOfStudyFrom(this.getStudentPeriodOfStudyFrom())
                .studentPeriodOfStudyUntil(this.getStudentPeriodOfStudyUntil())
                .studentPlaceOfBirth(this.getStudentPlaceOfBirth())
                .studentNationality(this.getStudentNationality())
                .studentCurrentAddress(this.getStudentCurrentAddress())
                .studentPhone(this.getStudentPhone())
                .studentSex(this.getStudentSex().toString())
                .studyCycle(this.getStudyCycle().toString())
                .semester(this.getSemester().toString())
                .degreeOfStudy(this.getDegreeOfStudy().toString())
                .departmentalCoordinatorAcademicTitle(this.getDepartmentalCoordinatorAcademicTitle())
                .departmentalCoordinatorFirstName(this.getDepartmentalCoordinatorFirstName())
                .departmentalCoordinatorLastName(this.getDepartmentalCoordinatorLastName())
                .departmentalCoordinatorPhone(this.getDepartmentalCoordinatorPhone())
                .departmentalCoordinatorFax(this.getDepartmentalCoordinatorFax())
                .departmentalCoordinatorEmail(this.getDepartmentalCoordinatorEmail())
                .photoBlob(this.getPhotoBlob())
                .courseIds(this.getCourses()
                        .stream()
                        .map(Course::getId)
                        .collect(Collectors.toList()))
                .sendingInstitutionIds(this.getSendingInstitutions()
                        .stream()
                        .map(SendingInstitution::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
