package pl.edu.prz.master.thesis.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.lang.Nullable;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "SendingInstitutions")
@Data
@EqualsAndHashCode(exclude = {"id", "students", "studentIds"})
@ToString(exclude = {"students"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendingInstitution implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Email
    private String sendingInstitutionEmail;

    @NotNull
    private String sendingInstitutionName;

    @NotNull
    private String sendingInstitutionAddress;

    @NotNull
    private String sendingInstitutionPostCode;

    @NotNull
    private String sendingInstitutionCountry;

    @NotNull
    private String sendingInstitutionPhone;

    @NotNull
    private String sendingInstitutionFax;

    @Nullable
    @Transient
    private Set<Long> studentIds = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(
            name = "StudentSendingInstitutions",
            joinColumns = {@JoinColumn(name = "sending_institution_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    @Builder.Default
    private Set<Student> students = new HashSet<>();

    public SendingInstitutionDTO mapToDTO() {
        return SendingInstitutionDTO.builder()
                .id(this.getId())
                .sendingInstitutionEmail(this.getSendingInstitutionEmail())
                .sendingInstitutionName(this.getSendingInstitutionName())
                .sendingInstitutionAddress(this.getSendingInstitutionAddress())
                .sendingInstitutionPostCode(this.getSendingInstitutionPostCode())
                .sendingInstitutionCountry(this.getSendingInstitutionCountry())
                .sendingInstitutionPhone(this.getSendingInstitutionPhone())
                .sendingInstitutionFax(this.getSendingInstitutionFax())
                .studentIds(this.getStudents().stream()
                        .map(Student::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
