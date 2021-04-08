package pl.edu.prz.master.thesis.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.pdf.PdfCourseProtocol;
import pl.edu.prz.master.thesis.backend.pdf.PdfIndividualCurriculum;
import pl.edu.prz.master.thesis.backend.pdf.PdfTranscriptOfRecords;
import pl.edu.prz.master.thesis.backend.pdf.PdfCreator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfCreatorService {

    @Autowired
    private PdfCreator pdfCreator;

    public ByteArrayOutputStream createPdfCourseProtocolFile(PdfCourseProtocol parsedPdfCourseProtocol) throws IOException {
        return pdfCreator.createCourseProtocolDocument(parsedPdfCourseProtocol.getStudents(), parsedPdfCourseProtocol.getCoordinators(), parsedPdfCourseProtocol.getCourses());
    }

    public ByteArrayOutputStream createPdfTranscriptOfRecordsFile(PdfTranscriptOfRecords parsedPdfTranscriptOfRecords) throws IOException {
        return pdfCreator.createTranscriptOfRecordsDocument(parsedPdfTranscriptOfRecords.getStudents(), parsedPdfTranscriptOfRecords.getCourses(), parsedPdfTranscriptOfRecords.getSendingInstitutions());
    }

    public ByteArrayOutputStream createPdfIndividualCurriculumFile(PdfIndividualCurriculum parsedIndividualCurriculum) throws IOException {
        return pdfCreator.createIndividualCurriculumDocument(parsedIndividualCurriculum.getStudents(), parsedIndividualCurriculum.getCourses(), parsedIndividualCurriculum.getSendingInstitutions());
    }

}
