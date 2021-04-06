package pl.edu.prz.master.thesis.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfSendingInstitution {

    @NotNull
    private Long id;

    @NotNull
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

    
}
