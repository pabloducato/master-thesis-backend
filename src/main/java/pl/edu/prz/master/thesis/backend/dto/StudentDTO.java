package pl.edu.prz.master.thesis.backend.dto;

import lombok.*;
import org.springframework.lang.Nullable;
import pl.edu.prz.master.thesis.backend.entity.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO implements Serializable {
    private Long id;

    @NotNull
    @Email
    private String studentEmail;

    @NotNull
    private String academicYear;

    @NotNull
    private String fieldOfStudy;

    @NotNull
    private String department;

    @NotNull
    private String degreeOfStudy;

    @NotNull
    private String semester;

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
    private Long studentMatriculationNumber;

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

    @NotNull
    private String studentCurrentAddress;

    @NotNull
    private String studentPhone;

    @Nullable
    private String studentSex;

    @NotNull
    private String studyCycle;

    @Nullable
    private byte[] photoBlob;

    @Nullable
    private List<Long> courseIds = new ArrayList<>();

    @Nullable
    private List<Long> sendingInstitutionIds = new ArrayList<>();

    public Student parseStudent() {
        return Student.builder()
                .studentEmail(this.getStudentEmail())
                .studentMatriculationNumber(this.getStudentMatriculationNumber())
                .academicYear(this.getAcademicYear())
                .fieldOfStudy(this.getFieldOfStudy())
                .department(this.getDepartment())
                .degreeOfStudy(Student.DegreeOfStudy.valueOf(this.getDegreeOfStudy()))
                .studentFirstName(this.getStudentFirstName())
                .studentLastName(this.getStudentLastName())
                .studentDateOfBirth(this.getStudentDateOfBirth())
                .studentPeriodOfStudyFrom(this.getStudentPeriodOfStudyFrom())
                .studentPeriodOfStudyUntil(this.getStudentPeriodOfStudyUntil())
                .studentPlaceOfBirth(this.getStudentPlaceOfBirth())
                .studentNationality(this.getStudentNationality())
                .studentCurrentAddress(this.getStudentCurrentAddress())
                .studentPhone(this.getStudentPhone())
                .studentSex(Student.Sex.valueOf(this.getStudentSex()))
                .studyCycle(Student.StudyCycle.valueOf(this.getStudyCycle()))
                .semester(Student.Semester.valueOf(this.getSemester()))
                .departmentalCoordinatorAcademicTitle(this.getDepartmentalCoordinatorAcademicTitle())
                .departmentalCoordinatorFirstName(this.getDepartmentalCoordinatorFirstName())
                .departmentalCoordinatorLastName(this.getDepartmentalCoordinatorLastName())
                .departmentalCoordinatorPhone(this.getDepartmentalCoordinatorPhone())
                .departmentalCoordinatorFax(this.getDepartmentalCoordinatorFax())
                .departmentalCoordinatorEmail(this.getDepartmentalCoordinatorEmail())
                .photoBlob(this.getPhotoBlob())
                .courseIds(new HashSet<>(this.getCourseIds()))
                .sendingInstitutionIds(new HashSet<>(this.getSendingInstitutionIds()))
                .build();
    }
}
