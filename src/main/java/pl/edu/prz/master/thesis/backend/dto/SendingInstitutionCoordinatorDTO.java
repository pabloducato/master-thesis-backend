package pl.edu.prz.master.thesis.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitutionCoordinator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendingInstitutionCoordinatorDTO {
    private Long id;

    @NotNull
    @Email
    private String sendingInstitutionCoordinatorEmail;

    @NotNull
    private String sendingInstitutionCoordinatorAcademicTitle;

    @NotNull
    private String sendingInstitutionCoordinatorFirstName;

    @NotNull
    private String sendingInstitutionCoordinatorLastName;

    @NotNull
    private String sendingInstitutionCoordinatorPhone;

    @NotNull
    private String sendingInstitutionCoordinatorFax;

    public SendingInstitutionCoordinator parseSendingInstitutionCoordinator() {
        return SendingInstitutionCoordinator.builder()
                .sendingInstitutionCoordinatorEmail(this.getSendingInstitutionCoordinatorEmail())
                .sendingInstitutionCoordinatorAcademicTitle(this.getSendingInstitutionCoordinatorAcademicTitle())
                .sendingInstitutionCoordinatorFirstName(this.getSendingInstitutionCoordinatorFirstName())
                .sendingInstitutionCoordinatorLastName(this.getSendingInstitutionCoordinatorLastName())
                .sendingInstitutionCoordinatorPhone(this.getSendingInstitutionCoordinatorPhone())
                .sendingInstitutionCoordinatorFax(this.getSendingInstitutionCoordinatorFax())
                .build();
    }
}
