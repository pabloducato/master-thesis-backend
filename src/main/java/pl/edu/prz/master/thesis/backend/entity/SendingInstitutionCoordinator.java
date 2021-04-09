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

    @Column(name = "SENDING_INSTITUTION_COORDINATOR_EMAIL", nullable = false, unique = true, updatable = false)
    private String sendingInstitutionCoordinatorEmail;

    @Column(name = "SENDING_INSTITUTION_COORDINATOR_ACADEMIC_TITLE", nullable = false)
    private String sendingInstitutionCoordinatorAcademicTitle;

    @Column(name = "SENDING_INSTITUTION_COORDINATOR_FIRST_NAME", nullable = false)
    private String sendingInstitutionCoordinatorFirstName;

    @Column(name = "SENDING_INSTITUTION_COORDINATOR_LAST_NAME", nullable = false)
    private String sendingInstitutionCoordinatorLastName;

    @Column(name = "SENDING_INSTITUTION_COORDINATOR_PHONE", nullable = false)
    private String sendingInstitutionCoordinatorPhone;

    @Column(name = "SENDING_INSTITUTION_COORDINATOR_FAX", nullable = false)
    private String sendingInstitutionCoordinatorFax;

}
