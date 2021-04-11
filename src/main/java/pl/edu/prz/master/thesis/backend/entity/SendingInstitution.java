package pl.edu.prz.master.thesis.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

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

    @Transient
    private Set<Long> studentIds = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(SAVE_UPDATE)
    @JoinTable(
            name = "STUDENT_HAS_SENDING_INSTITUTIONS",
            joinColumns = {@JoinColumn(name = "SENDING_INSTITUTION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "STUDENT_ID")}
    )
    private Set<Student> students = new HashSet<>();

}
