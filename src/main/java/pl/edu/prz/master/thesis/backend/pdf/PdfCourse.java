package pl.edu.prz.master.thesis.backend.pdf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfCourse {

    @NotNull
    private Long id;

    @NotNull
    private String courseUnitCode;

    @NotNull
    private String name;

    @NotNull
    private String durationOfCourseUnit;

    @NotNull
    private Long credits;

    private boolean active;

    @NotNull
    private Long courseCoordinatorIds;

    @NotNull
    private String semester;

    @NotNull
    private String department;

    @NotNull
    private String numberOfHours;

}
