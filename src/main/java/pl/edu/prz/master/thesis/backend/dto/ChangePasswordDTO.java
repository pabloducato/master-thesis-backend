package pl.edu.prz.master.thesis.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Change password object stored in database")
public class ChangePasswordDTO implements Serializable {

    @ApiModelProperty(notes = "The new password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String newPassword;

    @ApiModelProperty(notes = "The old password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String oldPassword;
}
