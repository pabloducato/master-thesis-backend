package pl.edu.prz.master.thesis.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitution;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendingInstitutionDTO {
    private Long id;

    @NotNull
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
    private List<Long> studentIds = new ArrayList<>();

    public SendingInstitution parseSendingInstitution() {
        assert this.getStudentIds() != null;
        return SendingInstitution.builder()
                .sendingInstitutionEmail(this.getSendingInstitutionEmail())
                .sendingInstitutionName(this.getSendingInstitutionName())
                .sendingInstitutionAddress(this.getSendingInstitutionAddress())
                .sendingInstitutionPostCode(this.getSendingInstitutionPostCode())
                .sendingInstitutionCountry(this.getSendingInstitutionCountry())
                .sendingInstitutionPhone(this.getSendingInstitutionPhone())
                .sendingInstitutionFax(this.getSendingInstitutionFax())
                .studentIds(new HashSet<>(this.getStudentIds()))
                .build();
    }
}
