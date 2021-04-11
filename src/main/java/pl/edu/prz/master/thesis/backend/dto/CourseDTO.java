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
@ApiModel(description = "Course object stored in database")
public class CourseDTO implements Serializable {

    @ApiModelProperty(notes = "The course's id")
    private Long id;

    @ApiModelProperty(notes = "The course's unit code")
    private String unitCode;

    @ApiModelProperty(notes = "The course's name")
    private String name;

    @ApiModelProperty(notes = "The course's duration of unit")
    private String durationOfUnit;

    @ApiModelProperty(notes = "The course's credits")
    private Long credits;

    @ApiModelProperty(notes = "The course whether is active")
    private boolean active;

    @ApiModelProperty(notes = "The course's semester'")
    private String semester;

    @ApiModelProperty(notes = "The course's department'")
    private String department;

    @ApiModelProperty(notes = "The course's department'")
    private String numberOfHours;

    @ApiModelProperty(notes = "The course's student ids'")
    private Set<Long> studentIds;

    @ApiModelProperty(notes = "The course's coordinator ids'")
    private Set<Long> courseCoordinatorIds;

}
