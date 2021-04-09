package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.edu.prz.master.thesis.backend.dto.StudentDTO;
import pl.edu.prz.master.thesis.backend.enums.DegreeOfStudy;
import pl.edu.prz.master.thesis.backend.enums.Semester;
import pl.edu.prz.master.thesis.backend.enums.Sex;
import pl.edu.prz.master.thesis.backend.enums.StudyCycle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @Column(name = "STUDENT_EMAIL", nullable = false, unique = true, updatable = false)
    private String studentEmail;

    @Column(name = "STUDENT_MATRICULATION_NUMBER", nullable = false)
    private Long studentMatriculationNumber;

    @Column(name = "STUDENT_ACADEMIC_YEAR", nullable = false)
    private String studentAcademicYear;

    @Column(name = "STUDENT_FIELD_OF_STUDY", nullable = false)
    private String studentFieldOfStudy;

    @Column(name = "STUDENT_DEPARTMENT", nullable = false)
    private String studentDepartment;

    @Column(name = "STUDENT_DEGREE_OF_STUDY", nullable = false)
    @Enumerated(EnumType.STRING)
    private DegreeOfStudy studentDegreeOfStudy;

    @Column(name = "STUDENT_SEMESTER", nullable = false)
    @Enumerated(EnumType.STRING)
    private Semester studentSemester;

    @Column(name = "STUDENT_FIRST_NAME", nullable = false)
    private String studentFirstName;

    @Column(name = "STUDENT_LAST_NAME", nullable = false)
    private String studentLastName;

    @Column(name = "STUDENT_DATE_OF_BIRTH", nullable = false)
    private Date studentDateOfBirth;

    @Column(name = "STUDENT_PERIOD_OF_STUDY_FROM", nullable = false)
    private Date studentPeriodOfStudyFrom;

    @Column(name = "STUDENT_PERIOD_OF_STUDY_UNTIL", nullable = false)
    private Date studentPeriodOfStudyUntil;

    @Column(name = "STUDENT_PLACE_OF_BIRTH", nullable = false)
    private String studentPlaceOfBirth;

    @Column(name = "STUDENT_NATIONALITY", nullable = false)
    private String studentNationality;

    @Column(name = "STUDENT_CURRENT_ADDRESS", nullable = false)
    private String studentCurrentAddress;

    @Column(name = "STUDENT_PHONE", nullable = false)
    private String studentPhone;

    @Column(name = "STUDENT_SEX", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex studentSex = Sex.HIDDEN;

    @Column(name = "STUDY_CYCLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudyCycle studyCycle;

    @Column(name = "STUDENT_PHOTO_BLOB")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] photoBlob;

    @OneToMany(mappedBy = "student")
    private List<Course> courses;

    @OneToMany(mappedBy = "student")
    private List<SendingInstitution> sendingInstitutions;

    @OneToMany(mappedBy = "student")
    private List<SendingInstitutionCoordinator> sendingInstitutionCoordinators;

}
