package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.edu.prz.master.thesis.backend.dto.StudentDTO;

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
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @GeneratedValue(generator = "STUDENT_SEQUENCE")
    private Long id;

    @Column(name = "STUDENT_EMAIL", nullable = false, unique = true, updatable = false)
    private String studentEmail;

    @Column(name = "STUDENT_MATRICULATION_NUMBER", nullable = false)
    private Long studentMatriculationNumber;

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

    @Column(name = "DEPARTMENTAL_COORDINATOR_ACADEMIC_TITLE", nullable = false)
    private String departmentalCoordinatorAcademicTitle;

    @Column(name = "DEPARTMENTAL_COORDINATOR_FIRST_NAME", nullable = false)
    private String departmentalCoordinatorFirstName;

    @Column(name = "DEPARTMENTAL_COORDINATOR_LAST_NAME", nullable = false)
    private String departmentalCoordinatorLastName;

    @Column(name = "DEPARTMENTAL_COORDINATOR_PHONE", nullable = false)
    private String departmentalCoordinatorPhone;

    @Column(name = "DEPARTMENTAL_COORDINATOR_FAX", nullable = false)
    private String departmentalCoordinatorFax;

    @Column(name = "DEPARTMENTAL_COORDINATOR_EMAIL", nullable = false)
    private String departmentalCoordinatorEmail;

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

    @Column(name = "SEX", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex studentSex = Sex.HIDDEN;

    @Column(name = "STUDY_CYCLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudyCycle studyCycle;

    @Column(name = "PHOTO_BLOB")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] photoBlob;

    @OneToMany(mappedBy = "student")
    private List<Course> courses;

    @OneToMany(mappedBy = "student")
    private List<SendingInstitution> sendingInstitutions;

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
                .courses(this.getCourses())
                .sendingInstitutions(this.getSendingInstitutions())
                .build();
    }
}
