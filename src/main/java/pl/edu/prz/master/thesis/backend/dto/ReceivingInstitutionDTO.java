package pl.edu.prz.master.thesis.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.prz.master.thesis.backend.entity.ReceivingInstitution;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Receiving institution object stored in database")
public class ReceivingInstitutionDTO {

    @ApiModelProperty(notes = "The receiving institution's id")
    private Long id;

    @ApiModelProperty(notes = "The receiving institution's email")
    private String receivingInstitutionEmail;

    @ApiModelProperty(notes = "The receiving institution's name")
    private String receivingInstitutionName;

    @ApiModelProperty(notes = "The receiving institution's patron")
    private String receivingInstitutionPatron;

    @ApiModelProperty(notes = "The receiving institution's where")
    private String receivingInstitutionWhere;

    @ApiModelProperty(notes = "The receiving institution's address")
    private String receivingInstitutionAddress;

    @ApiModelProperty(notes = "The receiving institution's post code")
    private String receivingInstitutionPostCode;

    @ApiModelProperty(notes = "The receiving institution's country")
    private String receivingInstitutionCountry;

    @ApiModelProperty(notes = "The receiving institution's phone")
    private String receivingInstitutionPhone;

    @ApiModelProperty(notes = "The receiving institution's fax")
    private String receivingInstitutionFax;

    public ReceivingInstitution parseReceivingInstitution() {
        return ReceivingInstitution.builder()
                .receivingInstitutionEmail(this.getReceivingInstitutionEmail())
                .receivingInstitutionName(this.getReceivingInstitutionName())
                .receivingInstitutionPatron(this.getReceivingInstitutionPatron())
                .receivingInstitutionWhere(this.getReceivingInstitutionWhere())
                .receivingInstitutionAddress(this.getReceivingInstitutionAddress())
                .receivingInstitutionPostCode(this.getReceivingInstitutionPostCode())
                .receivingInstitutionCountry(this.getReceivingInstitutionCountry())
                .receivingInstitutionPhone(this.getReceivingInstitutionPhone())
                .receivingInstitutionFax(this.getReceivingInstitutionFax())
                .build();
    }

}
