package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.pdf.PdfBox;
import pl.edu.prz.master.thesis.backend.pdf.PdfCourseProtocol;
import pl.edu.prz.master.thesis.backend.pdf.PdfIndividualCurriculum;
import pl.edu.prz.master.thesis.backend.pdf.PdfTranscriptOfRecords;
import pl.edu.prz.master.thesis.backend.exception.PDFNotCreatedException;
import pl.edu.prz.master.thesis.backend.service.PdfCreatorService;

import javax.servlet.ServletContext;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@Api(tags = "Pdf Creator Controller")
@RequestMapping(value = "/api/pdf", produces = APPLICATION_JSON_VALUE)
public class PdfCreatorController {

    @Autowired
    private PdfCreatorService pdfCreatorService;

    @Autowired
    private ServletContext servletContext;

    @PostMapping("/protocol")
    @ResponseStatus(HttpStatus.CREATED)
    public PdfBox createPdfCourseProtocolFile(@RequestBody PdfCourseProtocol pdfCourseProtocol) {
        try {
            ByteArrayOutputStream stream = pdfCreatorService.createPdfCourseProtocolFile(pdfCourseProtocol);
            byte[] encodedBytes = Base64.getEncoder().encode(stream.toByteArray());
            String encodedString = new String(encodedBytes);
//            System.out.println(pdfCourseProtocol);
//            System.out.println(encodedString);
            return new PdfBox(encodedString);

        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.toString());
            throw new PDFNotCreatedException();
        }
    }

    @PostMapping("/transcript_of_records")
    @ResponseStatus(HttpStatus.CREATED)
    public PdfBox createPdfTranscriptOfRecordsFile(@RequestBody PdfTranscriptOfRecords pdfTranscriptOfRecords) {
        try {
            ByteArrayOutputStream stream = pdfCreatorService.createPdfTranscriptOfRecordsFile(pdfTranscriptOfRecords);
            byte[] encodedBytes = Base64.getEncoder().encode(stream.toByteArray());
            String encodedString = new String(encodedBytes);
//            System.out.println(pdfTranscriptOfRecords);
//            System.out.println(encodedString);
            return new PdfBox(encodedString);

        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.toString());
            throw new PDFNotCreatedException();
        }
    }

    @PostMapping("/individual_curriculum")
    @ResponseStatus(HttpStatus.CREATED)
    public PdfBox createPdfIndividualCurriculumFile(@RequestBody PdfIndividualCurriculum pdfIndividualCurriculum) {
        try {
            ByteArrayOutputStream stream = pdfCreatorService.createPdfIndividualCurriculumFile(pdfIndividualCurriculum);
            byte[] encodedBytes = Base64.getEncoder().encode(stream.toByteArray());
            String encodedString = new String(encodedBytes);
//            System.out.println(pdfIndividualCurriculum);
//            System.out.println(encodedString);
            return new PdfBox(encodedString);

        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.toString());
            throw new PDFNotCreatedException();
        }
    }

}
