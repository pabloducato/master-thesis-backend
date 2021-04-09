package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SENDING_INSTITUTIONS")
public class SendingInstitution implements Serializable {

    @Id
    @GeneratedValue(generator = "SENDING_INSTITUTION_SEQUENCE")
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "POST_CODE", nullable = false)
    private String postCode;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "FAX", nullable = false)
    private String fax;

    @ManyToMany(mappedBy = "studentSendingInstitutions")
    private Set<Student> students;

}
