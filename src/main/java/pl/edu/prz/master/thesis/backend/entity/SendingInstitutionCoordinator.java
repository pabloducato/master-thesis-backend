package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SENDING_INSTITUTION_COORDINATORS")
public class SendingInstitutionCoordinator implements Serializable {

    @Id
    @GeneratedValue(generator = "SENDING_INSTITUTION_COORDINATOR_SEQUENCE")
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "ACADEMIC_TITLE", nullable = false)
    private String academicTitle;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "FAX", nullable = false)
    private String fax;

}
