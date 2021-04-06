package pl.edu.prz.master.thesis.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import pl.edu.prz.master.thesis.backend.entity.ReceivingInstitution;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceivingInstitutionDTO {
    private Long id;

    @NotNull
    @Email
    private String receivingInstitutionEmail;

    @NotNull
    private String receivingInstitutionName;

    @Nullable
    private String receivingInstitutionPatron;

    @Nullable
    private String receivingInstitutionWhere;

    @NotNull
    private String receivingInstitutionAddress;

    @NotNull
    private String receivingInstitutionPostCode;

    @NotNull
    private String receivingInstitutionCountry;

    @NotNull
    private String receivingInstitutionPhone;

    @NotNull
    private String receivingInstitutionFax;

    public ReceivingInstitution parseReceivingInstitution() {
        return ReceivingInstitution.builder()
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
