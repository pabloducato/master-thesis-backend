package pl.edu.prz.master.thesis.backend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.prz.master.thesis.backend.entity.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO implements Serializable {

    @ApiModelProperty(notes = "The student's id")
    private Long id;

    @ApiModelProperty(notes = "The student's email")
    private String studentEmail;

    @ApiModelProperty(notes = "The student's academic year")
    private String academicYear;

    @ApiModelProperty(notes = "The student's field of study")
    private String fieldOfStudy;

    @ApiModelProperty(notes = "The student's department")
    private String department;

    @ApiModelProperty(notes = "The student's degree of study")
    private String degreeOfStudy;

    @ApiModelProperty(notes = "The student's semester")
    private String semester;

    @ApiModelProperty(notes = "The student's departmental coordinator academic title")
    private String departmentalCoordinatorAcademicTitle;

    @ApiModelProperty(notes = "The student's departmental coordinator first name")
    private String departmentalCoordinatorFirstName;

    @ApiModelProperty(notes = "The student's departmental coordinator last name")
    private String departmentalCoordinatorLastName;

    @ApiModelProperty(notes = "The student's departmental coordinator phone")
    private String departmentalCoordinatorPhone;

    @ApiModelProperty(notes = "The student's departmental coordinator fax")
    private String departmentalCoordinatorFax;

    @ApiModelProperty(notes = "The student's departmental coordinator email")
    private String departmentalCoordinatorEmail;

    @ApiModelProperty(notes = "The student's matriculation number")
    private Long studentMatriculationNumber;

    @ApiModelProperty(notes = "The student's first name")
    private String studentFirstName;

    @ApiModelProperty(notes = "The student's last name")
    private String studentLastName;

    @ApiModelProperty(notes = "The student's date of birth")
    private Date studentDateOfBirth;

    @ApiModelProperty(notes = "The student's period of study from")
    private Date studentPeriodOfStudyFrom;

    @ApiModelProperty(notes = "The student's period of study until")
    private Date studentPeriodOfStudyUntil;

    @ApiModelProperty(notes = "The student's place of birth")
    private String studentPlaceOfBirth;

    @ApiModelProperty(notes = "The student's nationality")
    private String studentNationality;

    @ApiModelProperty(notes = "The student's current address")
    private String studentCurrentAddress;

    @ApiModelProperty(notes = "The student's phone")
    private String studentPhone;

    @ApiModelProperty(notes = "The student's sex")
    private String studentSex;

    @ApiModelProperty(notes = "The student's study cycle")
    private String studyCycle;

    @ApiModelProperty(notes = "The student's photo blob")
    private byte[] photoBlob;

    @ApiModelProperty(notes = "The student's courses")
    private List<Course> courses;

    @ApiModelProperty(notes = "The student's sending institution ids")
    private List<SendingInstitution> sendingInstitutions;

    public Student parseStudent() {
        return Student.builder()
                .studentEmail(this.getStudentEmail())
                .studentMatriculationNumber(this.getStudentMatriculationNumber())
                .academicYear(this.getAcademicYear())
                .fieldOfStudy(this.getFieldOfStudy())
                .department(this.getDepartment())
                .degreeOfStudy(DegreeOfStudy.valueOf(this.getDegreeOfStudy()))
                .studentFirstName(this.getStudentFirstName())
                .studentLastName(this.getStudentLastName())
                .studentDateOfBirth(this.getStudentDateOfBirth())
                .studentPeriodOfStudyFrom(this.getStudentPeriodOfStudyFrom())
                .studentPeriodOfStudyUntil(this.getStudentPeriodOfStudyUntil())
                .studentPlaceOfBirth(this.getStudentPlaceOfBirth())
                .studentNationality(this.getStudentNationality())
                .studentCurrentAddress(this.getStudentCurrentAddress())
                .studentPhone(this.getStudentPhone())
                .studentSex(Sex.valueOf(this.getStudentSex()))
                .studyCycle(StudyCycle.valueOf(this.getStudyCycle()))
                .semester(Semester.valueOf(this.getSemester()))
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
