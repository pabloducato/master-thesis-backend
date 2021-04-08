package pl.edu.prz.master.thesis.backend.pdf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfTranscriptOfRecords implements Serializable {

    @NotNull
    private List<PdfStudent> students;

    @NotNull
    private List<PdfCourse> courses;

    @NotNull
    private List<PdfSendingInstitution> sendingInstitutions;
}
