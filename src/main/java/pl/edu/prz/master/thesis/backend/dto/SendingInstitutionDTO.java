package pl.edu.prz.master.thesis.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Sending institution object stored in database.")
public class SendingInstitutionDTO {

    @ApiModelProperty(notes = "The sending institution's id")
    private Long id;

    @ApiModelProperty(notes = "The sending institution's email")
    private String sendingInstitutionEmail;

    @ApiModelProperty(notes = "The sending institution's name")
    private String sendingInstitutionName;

    @ApiModelProperty(notes = "The sending institution's address")
    private String sendingInstitutionAddress;

    @ApiModelProperty(notes = "The sending institution's post code")
    private String sendingInstitutionPostCode;

    @ApiModelProperty(notes = "The sending institution's country")
    private String sendingInstitutionCountry;

    @ApiModelProperty(notes = "The sending institution's phone")
    private String sendingInstitutionPhone;

    @ApiModelProperty(notes = "The sending institution's fax")
    private String sendingInstitutionFax;

    public SendingInstitution parseSendingInstitution() {
        return SendingInstitution.builder()
                .sendingInstitutionEmail(this.getSendingInstitutionEmail())
                .sendingInstitutionName(this.getSendingInstitutionName())
                .sendingInstitutionAddress(this.getSendingInstitutionAddress())
                .sendingInstitutionPostCode(this.getSendingInstitutionPostCode())
                .sendingInstitutionCountry(this.getSendingInstitutionCountry())
                .sendingInstitutionPhone(this.getSendingInstitutionPhone())
                .sendingInstitutionFax(this.getSendingInstitutionFax())
                .build();
    }
}
