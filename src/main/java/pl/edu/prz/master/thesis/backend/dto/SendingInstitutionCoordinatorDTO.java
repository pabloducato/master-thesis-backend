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
@ApiModel(description = "Sending institution coordinator object stored in database.")
public class SendingInstitutionCoordinatorDTO {

    @ApiModelProperty(notes = "The sending institution coordinator's id")
    private Long id;

    @ApiModelProperty(notes = "The sending institution coordinator's email")
    private String email;

    @ApiModelProperty(notes = "The sending institution coordinator's academic title")
    private String academicTitle;

    @ApiModelProperty(notes = "The sending institution coordinator's first name")
    private String firstName;

    @ApiModelProperty(notes = "The sending institution coordinator's last name")
    private String lastName;

    @ApiModelProperty(notes = "The sending institution coordinator's phone")
    private String phone;

    @ApiModelProperty(notes = "The sending institution coordinator's fax")
    private String fax;

}
