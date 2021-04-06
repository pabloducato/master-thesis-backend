package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;
import pl.edu.prz.master.thesis.backend.dto.ReceivingInstitutionDTO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "ReceivingInstitutions")
@Data
@EqualsAndHashCode(exclude = {"id"})
@ToString()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceivingInstitution implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Email
    private String receivingInstitutionEmail = "kancelaria@prz.edu.pl";

    @NotNull
    private String receivingInstitutionName = "Politechnika Rzeszowska";

    @NotNull
    private String receivingInstitutionPatron = "im. Ignacego Łukasiewicza";

    @NotNull
    private String receivingInstitutionWhere = "w Rzeszowie";

    @NotNull
    private String receivingInstitutionAddress= "Aleja Powstańców Warszawy 12";

    @NotNull
    private String receivingInstitutionPostCode = "35-959 Rzeszów";

    @NotNull
    private String receivingInstitutionCountry = "Poland";

    @NotNull
    private String receivingInstitutionPhone = "+48178651100";

    @NotNull
    private String receivingInstitutionFax = "+48178541260";

    public ReceivingInstitutionDTO mapToDTO() {
        return ReceivingInstitutionDTO.builder()
                .id(this.getId())
                .receivingInstitutionEmail(this.getReceivingInstitutionEmail())
                .receivingInstitutionName(this.getReceivingInstitutionName())
                .receivingInstitutionPatron(this.getReceivingInstitutionPatron())
                .receivingInstitutionWhere(this.getReceivingInstitutionWhere())
                .receivingInstitutionAddress(this.getReceivingInstitutionAddress())
                .receivingInstitutionPostCode(this.getReceivingInstitutionPostCode())
                .receivingInstitutionCountry(this.getReceivingInstitutionCountry())
                .receivingInstitutionPhone(this.getReceivingInstitutionPhone())
                .receivingInstitutionFax(this.getReceivingInstitutionFax())
                .build();
    }
}
