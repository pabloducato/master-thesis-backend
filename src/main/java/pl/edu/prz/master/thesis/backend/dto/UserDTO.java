package pl.edu.prz.master.thesis.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.prz.master.thesis.backend.entity.Role;
import pl.edu.prz.master.thesis.backend.entity.Status;
import pl.edu.prz.master.thesis.backend.entity.User;

import java.io.Serializable;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "User object stored in database")
public class UserDTO implements Serializable {

    @ApiModelProperty(notes = "The user's id")
    private Long id;

    @ApiModelProperty(notes = "The user's email")
    private String email;

    @ApiModelProperty(notes = "The user's password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty(notes = "The user's first name")
    private String firstName;

    @ApiModelProperty(notes = "The user's last name")
    private String lastName;

    @ApiModelProperty(notes = "The user's status")
    private Status status;

    @ApiModelProperty(notes = "The user's role")
    private Role role;

    @ApiModelProperty(notes = "The user's photo blob")
    private byte[] photoBlob;

    @ApiModelProperty(notes = "The user's photo content length")
    private Integer photoContentLength;

    @ApiModelProperty(notes = "The user's photo content type")
    private String photoContentType;

    public User parseUser() {
        return User.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .status(Status.valueOf(this.getStatus().name()))
                .role(Role.valueOf(this.getRole().name()))
                .photoBlob(this.getPhotoBlob())
                .photoContentLength(this.getPhotoContentLength())
                .photoContentType(this.getPhotoContentType())
                .build();
    }

}
