package pl.edu.prz.master.thesis.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfCoordinator {

    @NotNull
    private Long id;

    @NotNull
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

}
