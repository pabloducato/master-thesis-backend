package pl.edu.prz.master.thesis.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Receiving institution object stored in database")
public class ReceivingInstitutionDTO {

    @ApiModelProperty(notes = "The receiving institution's id")
    private Long id;

    @ApiModelProperty(notes = "The receiving institution's email")
    private String email;

    @ApiModelProperty(notes = "The receiving institution's name")
    private String name;

    @ApiModelProperty(notes = "The receiving institution's patron")
    private String patron;

    @ApiModelProperty(notes = "The receiving institution's where")
    private String where;

    @ApiModelProperty(notes = "The receiving institution's address")
    private String address;

    @ApiModelProperty(notes = "The receiving institution's post code")
    private String postCode;

    @ApiModelProperty(notes = "The receiving institution's country")
    private String country;

    @ApiModelProperty(notes = "The receiving institution's phone")
    private String phone;

    @ApiModelProperty(notes = "The receiving institution's fax")
    private String fax;

}
