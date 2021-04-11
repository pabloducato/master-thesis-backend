package pl.edu.prz.master.thesis.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Course coordinator object stored in database")
public class CourseCoordinatorDTO implements Serializable {

    @ApiModelProperty(notes = "The course coordinator's id")
    private Long id;

    @ApiModelProperty(notes = "The course coordinator's email")
    private String email;

    @ApiModelProperty(notes = "The course coordinator's academic title")
    private String academicTitle;

    @ApiModelProperty(notes = "The course coordinator's first name")
    private String firstName;

    @ApiModelProperty(notes = "The course coordinator's last name")
    private String lastName;

    @ApiModelProperty(notes = "The course coordinator's phone")
    private String phone;

    @ApiModelProperty(notes = "The course coordinator's fax")
    private String fax;

    @ApiModelProperty(notes = "The course coordinator's course ids")
    private Set<Long> courseIds;

}
