package pl.edu.prz.master.thesis.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;
import pl.edu.prz.master.thesis.backend.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private Long id;

    @NotNull
    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String status;

    @NotNull
    private String role;

    @Nullable
    private byte[] photoBlob;

    @Nullable
    private Integer photoContentLength;

    @Nullable
    private String photoContentType;

    public User parseUser() {
        return User.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .status(User.Status.valueOf(this.getStatus()))
                .role(User.Role.valueOf(this.getRole()))
                .photoBlob(this.getPhotoBlob())
                .photoContentLength(this.getPhotoContentLength())
                .photoContentType(this.getPhotoContentType())
                .build();
    }

}
