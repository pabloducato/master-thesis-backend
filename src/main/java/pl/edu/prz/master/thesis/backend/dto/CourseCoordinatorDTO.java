package pl.edu.prz.master.thesis.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import pl.edu.prz.master.thesis.backend.entity.CourseCoordinator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCoordinatorDTO implements Serializable {
    private Long id;

    @NotNull
    @Email
    private String courseCoordinatorEmail;

    @NotNull
    private String courseCoordinatorAcademicTitle;

    @NotNull
    private String courseCoordinatorFirstName;

    @NotNull
    private String courseCoordinatorLastName;

    @NotNull
    private String courseCoordinatorPhone;

    @NotNull
    private String courseCoordinatorFax;

    @Nullable
    private List<Long> courseIds = new ArrayList<>();

    public CourseCoordinator parseCourseCoordinator() {
        assert this.getCourseIds() != null;
        return CourseCoordinator.builder()
                .courseCoordinatorEmail(this.getCourseCoordinatorEmail())
                .courseCoordinatorAcademicTitle(this.getCourseCoordinatorAcademicTitle())
                .courseCoordinatorFirstName(this.getCourseCoordinatorFirstName())
                .courseCoordinatorLastName(this.getCourseCoordinatorLastName())
                .courseCoordinatorPhone(this.getCourseCoordinatorPhone())
                .courseCoordinatorFax(this.getCourseCoordinatorFax())
                .courseIds(new HashSet<>(this.getCourseIds()))
                .build();
    }
}
