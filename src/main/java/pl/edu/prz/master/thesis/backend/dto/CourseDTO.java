package pl.edu.prz.master.thesis.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.prz.master.thesis.backend.entity.Course;
import pl.edu.prz.master.thesis.backend.entity.CourseCoordinator;
import pl.edu.prz.master.thesis.backend.entity.Student;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Course object stored in database")
public class CourseDTO implements Serializable {

    @ApiModelProperty(notes = "The course's id")
    private Long id;

    @ApiModelProperty(notes = "The course's unit code")
    private String courseUnitCode;

    @ApiModelProperty(notes = "The course's name")
    private String courseName;

    @ApiModelProperty(notes = "The course's duration of unit")
    private String courseDurationOfUnit;

    @ApiModelProperty(notes = "The course's credits")
    private Long courseCredits;

    @ApiModelProperty(notes = "The course whether is active")
    private boolean courseActive;

    @ApiModelProperty(notes = "The course's semester'")
    private String courseSemester;

    @ApiModelProperty(notes = "The course's department'")
    private String courseDepartment;

    @ApiModelProperty(notes = "The course's department'")
    private String courseNumberOfHours;

    @ApiModelProperty(notes = "The course's student ids'")
    private List<Student> courseStudents;

    @ApiModelProperty(notes = "The course's coordinator ids'")
    private List<CourseCoordinator> courseCoordinators;

    public Course parseCourse() {
        return Course.builder()
                .courseUnitCode(this.getCourseUnitCode())
                .courseName(this.getCourseName())
                .courseDurationOfUnit(this.getCourseDurationOfUnit())
                .courseCredits(this.getCourseCredits())
                .courseActive(this.isCourseActive())
                .courseSemester(this.getCourseSemester())
                .courseDepartment(this.getCourseDepartment())
                .courseNumberOfHours(this.getCourseNumberOfHours())
                .build();
    }
}
