package pl.edu.prz.master.thesis.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfCourseProtocol {

    @NotNull
    private List<PdfStudent> students;

    @NotNull
    private List<PdfCoordinator> coordinators;

    @NotNull
    private List<PdfCourse> courses;
}
