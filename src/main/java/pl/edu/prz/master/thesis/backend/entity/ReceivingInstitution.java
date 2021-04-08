package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;
import pl.edu.prz.master.thesis.backend.dto.ReceivingInstitutionDTO;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RECEIVING_INSTITUTIONS")
public class ReceivingInstitution implements Serializable {

    @Id
    @GeneratedValue(generator = "RECEIVING_INSTITUTION_SEQUENCE")
    private Long id;

    @Column(name = "RECEIVING_INSTITUTION_EMAIL", nullable = false, unique = true, updatable = false)
    private String receivingInstitutionEmail = "kancelaria@prz.edu.pl";

    @Column(name = "RECEIVING_INSTITUTION_NAME", nullable = false)
    private String receivingInstitutionName = "Politechnika Rzeszowska";

    @Column(name = "RECEIVING_INSTITUTION_PATRON", nullable = false)
    private String receivingInstitutionPatron = "im. Ignacego Łukasiewicza";

    @Column(name = "RECEIVING_INSTITUTION_WHERE", nullable = false)
    private String receivingInstitutionWhere = "w Rzeszowie";

    @Column(name = "RECEIVING_INSTITUTION_ADDRESS", nullable = false)
    private String receivingInstitutionAddress = "Aleja Powstańców Warszawy 12";

    @Column(name = "RECEIVING_INSTITUTION_POST_CODE", nullable = false)
    private String receivingInstitutionPostCode = "35-959 Rzeszów";

    @Column(name = "RECEIVING_INSTITUTION_COUNTRY", nullable = false)
    private String receivingInstitutionCountry = "Poland";

    @Column(name = "RECEIVING_INSTITUTION_PHONE", nullable = false)
    private String receivingInstitutionPhone = "+48178651100";

    @Column(name = "RECEIVING_INSTITUTION_FAX", nullable = false)
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
