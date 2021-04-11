package pl.edu.prz.master.thesis.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.prz.master.thesis.backend.enums.DegreeOfStudy;
import pl.edu.prz.master.thesis.backend.enums.Sex;
import pl.edu.prz.master.thesis.backend.enums.StudyCycle;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Student object stored in database")
public class StudentDTO implements Serializable {

    @ApiModelProperty(notes = "The student's id")
    private Long id;

    @ApiModelProperty(notes = "The student's email")
    private String email;

    @ApiModelProperty(notes = "The student's matriculation number")
    private Long matriculationNumber;

    @ApiModelProperty(notes = "The student's academic year")
    private String academicYear;

    @ApiModelProperty(notes = "The student's field of study")
    private String fieldOfStudy;

    @ApiModelProperty(notes = "The student's department")
    private String department;

    @ApiModelProperty(notes = "The student's degree of study")
    private DegreeOfStudy degreeOfStudy;

    @ApiModelProperty(notes = "The student's semester")
    private String semester;

    @ApiModelProperty(notes = "The student's first name")
    private String firstName;

    @ApiModelProperty(notes = "The student's last name")
    private String lastName;

    @ApiModelProperty(notes = "The student's date of birth")
    private Date dateOfBirth;

    @ApiModelProperty(notes = "The student's period of study from")
    private Date periodOfStudyFrom;

    @ApiModelProperty(notes = "The student's period of study until")
    private Date periodOfStudyUntil;

    @ApiModelProperty(notes = "The student's place of birth")
    private String placeOfBirth;

    @ApiModelProperty(notes = "The student's nationality")
    private String nationality;

    @ApiModelProperty(notes = "The student's current address")
    private String currentAddress;

    @ApiModelProperty(notes = "The student's phone")
    private String phone;

    @ApiModelProperty(notes = "The student's sex")
    private Sex sex;

    @ApiModelProperty(notes = "The student's study cycle")
    private StudyCycle studyCycle;

    @ApiModelProperty(notes = "The student's photo blob")
    private byte[] photoBlob;

    @ApiModelProperty(notes = "The student's course ids")
    private Set<Long> courseIds;

    @ApiModelProperty(notes = "The student's sending institution ids")
    private Set<Long> sendingInstitutionIds;

}
