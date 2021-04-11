package pl.edu.prz.master.thesis.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Sending institution object stored in database.")
public class SendingInstitutionDTO {

    @ApiModelProperty(notes = "The sending institution's id")
    private Long id;

    @ApiModelProperty(notes = "The sending institution's email")
    private String email;

    @ApiModelProperty(notes = "The sending institution's name")
    private String name;

    @ApiModelProperty(notes = "The sending institution's address")
    private String address;

    @ApiModelProperty(notes = "The sending institution's post code")
    private String postCode;

    @ApiModelProperty(notes = "The sending institution's country")
    private String country;

    @ApiModelProperty(notes = "The sending institution's phone")
    private String phone;

    @ApiModelProperty(notes = "The sending institution's fax")
    private String fax;

    @ApiModelProperty(notes = "The sending institution's student ids")
    private Set<Long> studentIds;

}
