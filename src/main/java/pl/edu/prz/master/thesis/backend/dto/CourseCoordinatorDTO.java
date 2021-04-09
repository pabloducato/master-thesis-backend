package pl.edu.prz.master.thesis.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.prz.master.thesis.backend.entity.Course;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Course coordinator object stored in database")
public class CourseCoordinatorDTO implements Serializable {

    @ApiModelProperty(notes = "The course coordinator's id")
    private Long id;

    @ApiModelProperty(notes = "The course coordinator's email")
    private String courseCoordinatorEmail;

    @ApiModelProperty(notes = "The course coordinator's academic title")
    private String courseCoordinatorAcademicTitle;

    @ApiModelProperty(notes = "The course coordinator's first name")
    private String courseCoordinatorFirstName;

    @ApiModelProperty(notes = "The course coordinator's last name")
    private String courseCoordinatorLastName;

    @ApiModelProperty(notes = "The course coordinator's phone")
    private String courseCoordinatorPhone;

    @ApiModelProperty(notes = "The course coordinator's fax")
    private String courseCoordinatorFax;

    @ApiModelProperty(notes = "The course coordinator's course ids")
    private List<Course> courses;

}
