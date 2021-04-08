package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.edu.prz.master.thesis.backend.dto.UserDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "USER_SEQUENCE")
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "LAST_PASSWORD_MODIFIED")
    private Date lastPasswordModified;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "PHOTO_BLOB")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] photoBlob;

    @Column(name = "PHOTO_CONTENT_LENGTH")
    private Integer photoContentLength;

    @Column(name = "PHOTO_CONTENT_TYPE", length = 50)
    private String photoContentType;

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
