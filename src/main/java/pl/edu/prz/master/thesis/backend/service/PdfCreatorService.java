package pl.edu.prz.master.thesis.backend.service;

import pl.edu.prz.master.thesis.backend.pdf.PdfCourseProtocol;
import pl.edu.prz.master.thesis.backend.pdf.PdfIndividualCurriculum;
import pl.edu.prz.master.thesis.backend.pdf.PdfTranscriptOfRecords;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface PdfCreatorService {

    ByteArrayOutputStream createPdfCourseProtocolFile(PdfCourseProtocol parsedPdfCourseProtocol) throws IOException;

    ByteArrayOutputStream createPdfTranscriptOfRecordsFile(PdfTranscriptOfRecords parsedPdfTranscriptOfRecords) throws IOException;

    ByteArrayOutputStream createPdfIndividualCurriculumFile(PdfIndividualCurriculum parsedIndividualCurriculum) throws IOException;

}
