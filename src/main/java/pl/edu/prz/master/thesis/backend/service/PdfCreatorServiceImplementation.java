package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.pdf.PdfCourseProtocol;
import pl.edu.prz.master.thesis.backend.pdf.PdfCreator;
import pl.edu.prz.master.thesis.backend.pdf.PdfIndividualCurriculum;
import pl.edu.prz.master.thesis.backend.pdf.PdfTranscriptOfRecords;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@Slf4j
@AllArgsConstructor
public class PdfCreatorServiceImplementation implements PdfCreatorService {

    @Autowired
    private final PdfCreator pdfCreator;

    @Override
    public ByteArrayOutputStream createPdfCourseProtocolFile(PdfCourseProtocol parsedPdfCourseProtocol) throws IOException {
        return pdfCreator.createCourseProtocolDocument(parsedPdfCourseProtocol.getStudents(), parsedPdfCourseProtocol.getCoordinators(), parsedPdfCourseProtocol.getCourses());
    }

    @Override
    public ByteArrayOutputStream createPdfTranscriptOfRecordsFile(PdfTranscriptOfRecords parsedPdfTranscriptOfRecords) throws IOException {
        return pdfCreator.createTranscriptOfRecordsDocument(parsedPdfTranscriptOfRecords.getStudents(), parsedPdfTranscriptOfRecords.getCourses(), parsedPdfTranscriptOfRecords.getSendingInstitutions());
    }

    @Override
    public ByteArrayOutputStream createPdfIndividualCurriculumFile(PdfIndividualCurriculum parsedIndividualCurriculum) throws IOException {
        return pdfCreator.createIndividualCurriculumDocument(parsedIndividualCurriculum.getStudents(), parsedIndividualCurriculum.getCourses(), parsedIndividualCurriculum.getSendingInstitutions());
    }
}
