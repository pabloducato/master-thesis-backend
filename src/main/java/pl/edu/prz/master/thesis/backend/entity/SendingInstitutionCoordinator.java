package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;
import org.springframework.lang.Nullable;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionCoordinatorDTO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "SendingInstitutionCoordinators")
@Data
@EqualsAndHashCode(exclude = {"id"})
@ToString()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendingInstitutionCoordinator implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Email
    private String sendingInstitutionCoordinatorEmail;

    @NotNull
    private String sendingInstitutionCoordinatorAcademicTitle;

    @NotNull
    private String sendingInstitutionCoordinatorFirstName;

    @NotNull
    private String sendingInstitutionCoordinatorLastName;

    @Nullable
    private String sendingInstitutionCoordinatorPhone;

    @Nullable
    private String sendingInstitutionCoordinatorFax;

    public SendingInstitutionCoordinatorDTO mapToDTO() {
        return SendingInstitutionCoordinatorDTO.builder()
                .id(this.getId())
                .sendingInstitutionCoordinatorEmail(this.getSendingInstitutionCoordinatorEmail())
                .sendingInstitutionCoordinatorAcademicTitle(this.getSendingInstitutionCoordinatorAcademicTitle())
                .sendingInstitutionCoordinatorFirstName(this.getSendingInstitutionCoordinatorFirstName())
                .sendingInstitutionCoordinatorLastName(this.getSendingInstitutionCoordinatorLastName())
                .sendingInstitutionCoordinatorPhone(this.getSendingInstitutionCoordinatorPhone())
                .sendingInstitutionCoordinatorFax(this.getSendingInstitutionCoordinatorFax())
                .build();
    }
}
