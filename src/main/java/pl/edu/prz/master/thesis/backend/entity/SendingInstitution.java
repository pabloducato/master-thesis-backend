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
@Table(name = "SENDING_INSTITUTIONS")
public class SendingInstitution implements Serializable {

    @Id
    @GeneratedValue(generator = "SENDING_INSTITUTION_SEQUENCE")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "STUDENT_ID", nullable = false, updatable = false, referencedColumnName = "ID")
    private Student student;

    @Column(name = "SENDING_INSTITUTION_EMAIL", nullable = false, unique = true, updatable = false)
    private String sendingInstitutionEmail;

    @Column(name = "SENDING_INSTITUTION_NAME", nullable = false)
    private String sendingInstitutionName;

    @Column(name = "SENDING_INSTITUTION_ADDRESS", nullable = false)
    private String sendingInstitutionAddress;

    @Column(name = "SENDING_INSTITUTION_POST_CODE", nullable = false)
    private String sendingInstitutionPostCode;

    @Column(name = "SENDING_INSTITUTION_COUNTRY", nullable = false)
    private String sendingInstitutionCountry;

    @Column(name = "SENDING_INSTITUTION_PHONE", nullable = false)
    private String sendingInstitutionPhone;

    @Column(name = "SENDING_INSTITUTION_FAX", nullable = false)
    private String sendingInstitutionFax;

}
