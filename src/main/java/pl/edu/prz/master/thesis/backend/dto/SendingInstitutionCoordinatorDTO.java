package pl.edu.prz.master.thesis.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitutionCoordinator;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "Sending institution coordinator object stored in database.")
public class SendingInstitutionCoordinatorDTO {

    @ApiModelProperty(notes = "The sending institution coordinator's id")
    private Long id;

    @ApiModelProperty(notes = "The sending institution coordinator's email")
    private String sendingInstitutionCoordinatorEmail;

    @ApiModelProperty(notes = "The sending institution coordinator's academic title")
    private String sendingInstitutionCoordinatorAcademicTitle;

    @ApiModelProperty(notes = "The sending institution coordinator's first name")
    private String sendingInstitutionCoordinatorFirstName;

    @ApiModelProperty(notes = "The sending institution coordinator's last name")
    private String sendingInstitutionCoordinatorLastName;

    @ApiModelProperty(notes = "The sending institution coordinator's phone")
    private String sendingInstitutionCoordinatorPhone;

    @ApiModelProperty(notes = "The sending institution coordinator's fax")
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
