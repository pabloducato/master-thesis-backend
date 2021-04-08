package pl.edu.prz.master.thesis.backend.pdf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfStudent implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private String studentEmail;

    @NotNull
    private Long studentMatriculationNumber;

    @NotNull
    private String academicYear;

    @NotNull
    private String fieldOfStudy;

    @NotNull
    private String department;

    @NotNull
    private String degreeOfStudy;

    @NotNull
    private String semester;

    @NotNull
    private String studentFirstName;

    @NotNull
    private String studentLastName;

    @NotNull
    private List<Long> courseIds;

    @Nullable
    private String departmentalCoordinatorAcademicTitle;

    @NotNull
    private String departmentalCoordinatorFirstName;

    @NotNull
    private String departmentalCoordinatorLastName;

    @NotNull
    private String departmentalCoordinatorPhone;

    @NotNull
    private String departmentalCoordinatorFax;

    @NotNull
    private String departmentalCoordinatorEmail;

    @NotNull
    private String studentDateOfBirth;

    @NotNull
    private String studentPeriodOfStudyFrom;

    @NotNull
    private String studentPeriodOfStudyUntil;

    @NotNull
    private String studentPlaceOfBirth;

    @NotNull
    private String studentNationality;

    @NotNull
    private String studentCurrentAddress;

    @NotNull
    private String studentPhone;

    @NotNull
    private String studentSex;

    @NotNull
    private String studyCycle;

}
