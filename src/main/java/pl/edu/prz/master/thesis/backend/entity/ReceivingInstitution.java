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
@Table(name = "RECEIVING_INSTITUTIONS")
public class ReceivingInstitution implements Serializable {

    @Id
    @GeneratedValue(generator = "RECEIVING_INSTITUTION_SEQUENCE")
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true, updatable = false)
    private String email = "kancelaria@prz.edu.pl";

    @Column(name = "NAME", nullable = false)
    private String name = "Politechnika Rzeszowska";

    @Column(name = "PATRON", nullable = false)
    private String patron = "im. Ignacego Łukasiewicza";

    @Column(name = "WHERE_FROM", nullable = false)
    private String whereFrom = "w Rzeszowie";

    @Column(name = "ADDRESS", nullable = false)
    private String address = "Aleja Powstańców Warszawy 12";

    @Column(name = "POST_CODE", nullable = false)
    private String postCode = "35-959 Rzeszów";

    @Column(name = "COUNTRY", nullable = false)
    private String country = "Poland";

    @Column(name = "PHONE", nullable = false)
    private String phone = "+48178651100";

    @Column(name = "FAX", nullable = false)
    private String fax = "+48178541260";

}
