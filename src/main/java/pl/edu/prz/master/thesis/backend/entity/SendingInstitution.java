package pl.edu.prz.master.thesis.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
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

    @Nullable
    @Transient
    private Set<Long> studentIds = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinTable(
            name = "STUDENT_HAS_SENDING_INSTITUTIONS",
            joinColumns = {@JoinColumn(name = "SENDING_INSTITUTION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "STUDENT_ID")}
    )
    @Builder.Default
    private Set<Student> students = new HashSet<>();

}
