package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.edu.prz.master.thesis.backend.dto.UserDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Users")
@Data
@EqualsAndHashCode(exclude = {"id"})
@ToString()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Email
    private String email;

    @NotNull
    private String password;

    private Date lastPasswordModified;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.BLOCKED;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "photo_blob")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] photoBlob;

    @Column(name = "photo_content_length")
    private Integer photoContentLength;

    @Column(name = "photo_content_type", length = 50)
    private String photoContentType;

    public enum Role {
        ADMINISTRATOR,
        SUPERADMINISTRATOR,
        USER
    }

    public enum Status {
        ACTIVE,
        BLOCKED,
        INACTIVE
    }

    public UserDTO mapToDTO() {
        return UserDTO.builder()
                .id(this.getId())
                .email(this.getEmail())
                .password(this.getPassword())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .status(this.getStatus().toString())
                .role(this.getRole().toString())
                .photoBlob(this.getPhotoBlob())
                .photoContentLength(this.getPhotoContentLength())
                .photoContentType(this.getPhotoContentType())
                .build();
    }

}
