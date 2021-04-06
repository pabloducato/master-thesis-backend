package pl.edu.prz.master.thesis.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import pl.edu.prz.master.thesis.backend.entity.Course;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO implements Serializable {
    private Long id;

    @Nullable
    private String courseUnitCode;

    @NotNull
    private String name;

    @NotNull
    private String durationOfCourseUnit;

    @NotNull
    private Long credits;

    @NotNull
    private boolean active;

    @Nullable
    private List<Long> studentIds = new ArrayList<>();

    @Nullable
    private List<Long> courseCoordinatorIds = new ArrayList<>();

    @NotNull
    private String semester;

    @NotNull
    private String department;

    @NotNull
    private String numberOfHours;

    public Course parseCourse() {
        return Course.builder()
                .courseUnitCode(this.getCourseUnitCode())
                .name(this.getName())
                .durationOfCourseUnit(this.getDurationOfCourseUnit())
                .credits(this.getCredits())
                .active(this.isActive())
                .studentIds(new HashSet<>(this.getStudentIds()))
                .courseCoordinatorIds(new HashSet<>(this.getCourseCoordinatorIds()))
                .semester(this.getSemester())
                .department(this.getDepartment())
                .numberOfHours(this.getNumberOfHours())
                .build();
    }
}
