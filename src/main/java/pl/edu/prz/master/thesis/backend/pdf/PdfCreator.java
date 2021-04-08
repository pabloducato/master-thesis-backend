package pl.edu.prz.master.thesis.backend.pdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.vandeseer.easytable.TableDrawer;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.Table;
import org.vandeseer.easytable.structure.cell.CellText;
import org.vandeseer.easytable.settings.HorizontalAlignment;
import org.vandeseer.easytable.settings.VerticalAlignment;
import pl.edu.prz.master.thesis.backend.exception.PDFNotCreatedException;
import pl.edu.prz.master.thesis.backend.service.StudentService;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.edu.prz.master.thesis.backend.pdf.PdfStyle.*;

@Component
@Slf4j
public class PdfCreator {
    @Autowired
    private PdfStyle style;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ResourceLoader resourceLoader;

    public PDFont ownFont;
    public PDFont ownNarrowFont;
    public PDFont ownFontNarrowBold;
    public PDFont ownFontBoldBlack;
    public PDFont timesNewRomanNormal;
    public PDFont timesNewRomanBold;
    public PDDocument document;

    private Table createCourseProtocolTable(List<PdfStudent> students, List<PdfCoordinator> coordinators, List<PdfCourse> courses) throws IOException {

        ownFont = PDType0Font.load(document, new File("C:/Windows/Fonts/calibri.ttf"));

        final Table.TableBuilder tableCourseProtocolBuilder = Table.builder()
                .addColumnsOfWidth(30, 220, 80, 55, 55, 55)
                .fontSize(10)
                .font(ownFont)
                .borderColor(Color.BLACK);

        final Row headerRow = Row.builder()
                .add(CellText.builder().text("Lp.").horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                .add(CellText.builder().text("Nazwiska i imiona").borderWidth(1).build())
                .add(CellText.builder().text("Nr albumu").borderWidth(1).build())
                .add(CellText.builder().text("Ocena PRz").borderWidth(1).build())
                .add(CellText.builder().text("Ocena ECTS").borderWidth(1).build())
                .add(CellText.builder().text("Data").borderWidth(1).build())
                .backgroundColor(Color.LIGHT_GRAY)
                .textColor(Color.BLACK)
                .font(ownFont).fontSize(12)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .build();

        tableCourseProtocolBuilder.addRow(headerRow);

        int lp = 1;
        List<String> studentFirstNames = new LinkedList<>();
        List<String> studentLastNames = new LinkedList<>();
        List<Long> studentMatriculationNumbers = new LinkedList<>();

        if (students.size() <= 25) {
            for (int i = 0; i < students.size(); i++) {
                for (PdfStudent student : students) {
                    studentFirstNames = students.stream().map(PdfStudent::getStudentFirstName).collect(Collectors.toList());
                    studentLastNames = students.stream().map(PdfStudent::getStudentLastName).collect(Collectors.toList());
                    studentMatriculationNumbers = students.stream().map(PdfStudent::getStudentMatriculationNumber).collect(Collectors.toList());

                }

                tableCourseProtocolBuilder.addRow(Row.builder()
                        .add(CellText.builder().text("" + lp + ".").horizontalAlignment(HorizontalAlignment.LEFT).borderWidth(1).build())
                        .add(CellText.builder().text("" + studentFirstNames.get(i) + " " + studentLastNames.get(i)).horizontalAlignment(HorizontalAlignment.LEFT).borderWidth(1).build())
                        .add(CellText.builder().text("" + studentMatriculationNumbers.get(i).toString()).horizontalAlignment(HorizontalAlignment.LEFT).borderWidth(1).build())
                        .add(CellText.builder().text("").horizontalAlignment(HorizontalAlignment.LEFT).borderWidth(1).build())
                        .add(CellText.builder().text("").horizontalAlignment(HorizontalAlignment.LEFT).borderWidth(1).build())
                        .add(CellText.builder().text("").horizontalAlignment(HorizontalAlignment.LEFT).borderWidth(1).build())
                        .font(ownFont).fontSize(12)
                        .textColor(Color.BLACK)
                        .backgroundColor(Color.WHITE)
                        .horizontalAlignment(HorizontalAlignment.LEFT)
                        .build())
                        .wordBreak(true);
                lp++;
            }
        } else {
            throw new PDFNotCreatedException();
        }
        return tableCourseProtocolBuilder.build();
    }

    private TableDrawer createTableDrawer(Table table) {
        return TableDrawer.builder()
                .table(table)
                .startX(50)
                .startY(630)
                .build();
    }

    private TableDrawer createTableTranscriptOfRecordsFirstDrawer(Table table) {
        return TableDrawer.builder()
                .table(table)
                .startX(50)
                .startY(680)
                .build();
    }

    private TableDrawer createTableTranscriptOfRecordsSecondDrawer(Table table) {
        return TableDrawer.builder()
                .table(table)
                .startX(50)
                .startY(505)
                .build();
    }

    private TableDrawer createTableIndividualCurriculumFirstDrawer(Table table) {
        return TableDrawer.builder()
                .table(table)
                .startX(50)
                .startY(575)
                .build();
    }

    private TableDrawer createTableIndividualCurriculumSecondDrawer(Table table) {
        return TableDrawer.builder()
                .table(table)
                .startX(50)
                .startY(560)
                .build();
    }

    public ByteArrayOutputStream createCourseProtocolDocument(List<PdfStudent> students, List<PdfCoordinator> coordinators, List<PdfCourse> courses) throws IOException {
        List<String> courseNames = new LinkedList<>();
        List<String> semesters = new LinkedList<>();
        List<String> courseCoordinatorAcademicTitles = new LinkedList<>();
        List<String> courseCoordinatorFirstNames = new LinkedList<>();
        List<String> courseCoordinatorLastNames = new LinkedList<>();
        List<String> studentAcademicYears = new LinkedList<>();
        String semester;

        for (int i = 0; i < coordinators.size(); i++) {
            for (PdfCoordinator coordinator : coordinators) {
                courseCoordinatorAcademicTitles = coordinators.stream().map(PdfCoordinator::getCourseCoordinatorAcademicTitle).collect(Collectors.toList());
                courseCoordinatorFirstNames = coordinators.stream().map(PdfCoordinator::getCourseCoordinatorFirstName).collect(Collectors.toList());
                courseCoordinatorLastNames = coordinators.stream().map(PdfCoordinator::getCourseCoordinatorLastName).collect(Collectors.toList());
            }
        }

        for (int i = 0; i < courses.size(); i++) {
            for (PdfCourse course : courses) {
                courseNames = courses.stream().map(PdfCourse::getName).collect(Collectors.toList());
                semesters = courses.stream().map(PdfCourse::getSemester).collect(Collectors.toList());
            }
        }

        for (int i = 0; i < students.size(); i++) {
            for (PdfStudent student : students) {
                studentAcademicYears = students.stream().map(PdfStudent::getAcademicYear).collect(Collectors.toList());
            }
        }

        if (semesters.get(0).contains("S")) {
            semester = "letni";
        } else if (semesters.get(0).contains("W")) {
            semester = "zimowy";
        } else {
            semester = semesters.get(0);
        }

        document = new PDDocument();
        PDPage page = new PDPage(PAGE_FORMAT);
        ownFont = PDType0Font.load(document, new File("C:/Windows/Fonts/calibri.ttf"));
        document.addPage(page);
        float startY = PAGE_FORMAT.getHeight();
        PDDocumentInformation pdd = document.getDocumentInformation();
        pdd.setAuthor("Paweł Kocan");
        pdd.setTitle("Course Protocol");
        pdd.setCreator("Paweł Kocan");
        pdd.setSubject("Course Protocol Document");
        Calendar date = Calendar.getInstance();
        pdd.setCreationDate(date);
        pdd.setModificationDate(date);
        pdd.setKeywords("Bachelor - IT system for managing Erasmus+ student services");
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        Table table = createCourseProtocolTable(students, coordinators, courses);
        float result1 = table.getHeight();
        TableDrawer drawer = createTableDrawer(table);
        drawer.contentStream(contentStream).draw();
        contentStream.beginText();
        contentStream.setFont(ownFont, 12);
        contentStream.newLineAtOffset(50, 765);
        String text1 = "Wydział Elektrotechniki i Informatyki Politechniki Rzeszowskiej";
        String text2 = "                                                         PROTOKÓŁ ZALICZENIA PRZEDMIOTU";
        String text3 = "Przedmiot:" + " " + courseNames.get(0);
        String text4 = "Semestr" + " " + semester + " " + studentAcademicYears.get(0) + " ";
        String text5 = "Koordynator przedmiotu:" + " " + courseCoordinatorAcademicTitles.get(0) + " " + courseCoordinatorFirstNames.get(0) + " " + courseCoordinatorLastNames.get(0);
        String text6 = "                                                                                                        podpis:";
        String text7 = "Przelicznik Ocena PRz -> Ocena ECTS: 5.0 - A, 4.5 - B, 4 - C, 3.5 - D, 3 - E, 2.5 - FX, 2 - F";
        contentStream.setLeading(30.0f);
        contentStream.showText(text1);
        contentStream.newLine();
        contentStream.showText(text2);
        contentStream.newLine();
        contentStream.showText(text3);
        contentStream.newLine();
        contentStream.showText(text4);
        contentStream.newLine();
        contentStream.showText(text5);
        contentStream.newLine();
        contentStream.newLineAtOffset(0, -result1);
        contentStream.setLeading(50.0f);
        contentStream.newLine();
        contentStream.showText(text6);
        contentStream.newLine();
        contentStream.showText(text7);
        contentStream.endText();
        contentStream.setNonStrokingColor(Color.BLACK);
        contentStream.addRect(385, -result1 + 549, 155, 41);
        contentStream.fill();
        contentStream.setNonStrokingColor(Color.WHITE);
        contentStream.addRect(386, -result1 + 550, 153, 38);
        contentStream.fill();
        contentStream.close();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();
        return byteArrayOutputStream;
    }

    private Table createTranscriptOfRecordsFirstTable(List<PdfStudent> students, List<PdfCourse> courses, List<PdfSendingInstitution> sendingInstitutions) throws IOException {

        List<String> studentFirstNames = new LinkedList<>();
        List<String> studentLastNames = new LinkedList<>();
        List<String> studentDatesOfBirth = new LinkedList<>();
        List<String> studentPeriodsOfStudyFrom = new LinkedList<>();
        List<String> studentPeriodsOfStudyUntil = new LinkedList<>();
        List<String> studentPlacesOfBirth = new LinkedList<>();
        List<String> sendingInstitutionNames = new LinkedList<>();
        List<String> studentDepartments = new LinkedList<>();
        List<String> studentDepartmentalCoordinatorAcademicTitles = new LinkedList<>();
        List<String> studentDepartmentalCoordinatorFirstNames = new LinkedList<>();
        List<String> studentDepartmentalCoordinatorLastNames = new LinkedList<>();
        List<String> studentDepartmentalCoordinatorPhones = new LinkedList<>();
        List<String> studentDepartmentalCoordinatorFaxes = new LinkedList<>();
        List<String> studentDepartmentalCoordinatorEmails = new LinkedList<>();

        for (int i = 0; i < students.size(); i++) {
            for (PdfStudent student : students) {
                studentDepartmentalCoordinatorAcademicTitles = students.stream().map(PdfStudent::getDepartmentalCoordinatorAcademicTitle).collect(Collectors.toList());
                studentFirstNames = students.stream().map(PdfStudent::getStudentFirstName).collect(Collectors.toList());
                studentLastNames = students.stream().map(PdfStudent::getStudentLastName).collect(Collectors.toList());
                studentDatesOfBirth = students.stream().map(PdfStudent::getStudentDateOfBirth).collect(Collectors.toList());
                studentPeriodsOfStudyFrom = students.stream().map(PdfStudent::getStudentPeriodOfStudyFrom).collect(Collectors.toList());
                studentPeriodsOfStudyUntil = students.stream().map(PdfStudent::getStudentPeriodOfStudyUntil).collect(Collectors.toList());
                studentPlacesOfBirth = students.stream().map(PdfStudent::getStudentPlaceOfBirth).collect(Collectors.toList());
                studentDepartments = students.stream().map(PdfStudent::getDepartment).collect(Collectors.toList());
                studentDepartmentalCoordinatorFirstNames = students.stream().map(PdfStudent::getDepartmentalCoordinatorFirstName).collect(Collectors.toList());
                studentDepartmentalCoordinatorLastNames = students.stream().map(PdfStudent::getDepartmentalCoordinatorLastName).collect(Collectors.toList());
                studentDepartmentalCoordinatorPhones = students.stream().map(PdfStudent::getDepartmentalCoordinatorPhone).collect(Collectors.toList());
                studentDepartmentalCoordinatorFaxes = students.stream().map(PdfStudent::getDepartmentalCoordinatorFax).collect(Collectors.toList());
                studentDepartmentalCoordinatorEmails = students.stream().map(PdfStudent::getDepartmentalCoordinatorEmail).collect(Collectors.toList());
            }
        }

        for (int j = 0; j < sendingInstitutions.size(); j++) {
            for (PdfSendingInstitution sendingInstitution : sendingInstitutions) {
                sendingInstitutionNames = sendingInstitutions.stream().map(PdfSendingInstitution::getSendingInstitutionName).collect(Collectors.toList());
            }
        }

        ownFont = PDType0Font.load(document, new File("C:/Windows/Fonts/arial.ttf"));
        ownFontNarrowBold = PDType0Font.load(document, new File("C:/Windows/Fonts/ARIALNB.ttf"));

        final Table.TableBuilder tableOneTranscriptOfRecordsBuilder = Table.builder()
                .addColumnsOfWidth(495)
                .fontSize(10)
                .font(ownFont)
                .borderColor(Color.BLACK);

        final Row headerRowOne = Row.builder()
                .add(CellText.builder().text("Name of Sending Institution:" + " " + sendingInstitutionNames.get(0) + "\n" + "Faculty/Department:" + " " + studentDepartments.get(0) + "\n" + "Name of Dept. Coordinator:" + " " + studentDepartmentalCoordinatorAcademicTitles.get(0) + " " + studentDepartmentalCoordinatorFirstNames.get(0) + " " + studentDepartmentalCoordinatorLastNames.get(0) + "\n" + "Tel:" + " " + studentDepartmentalCoordinatorPhones.get(0) + "    " + "Fax:" + " " + studentDepartmentalCoordinatorFaxes.get(0) + "    " + "E-Mail:" + " " + studentDepartmentalCoordinatorEmails.get(0)).horizontalAlignment(HorizontalAlignment.LEFT).borderWidth(1).build())
                .backgroundColor(Color.WHITE)
                .textColor(Color.BLACK)
                .font(ownFont).fontSize(10)
                .horizontalAlignment(HorizontalAlignment.LEFT)
                .build();

        final Row headerRowTwo = Row.builder()
                .add(CellText.builder().text("Surname of the student:" + " " + studentLastNames.get(0) + "    " + "First name:" + " " + studentFirstNames.get(0) + "\n" + "Date and place of birth:" + " " + studentDatesOfBirth.get(0) + " " + studentPlacesOfBirth.get(0) + "\n" + "Period of study: from" + " " + studentPeriodsOfStudyFrom.get(0) + " " + "until" + " " + studentPeriodsOfStudyUntil.get(0)).horizontalAlignment(HorizontalAlignment.LEFT).borderWidth(1).build())
                .backgroundColor(Color.WHITE)
                .textColor(Color.BLACK)
                .font(ownFont).fontSize(10)
                .horizontalAlignment(HorizontalAlignment.LEFT)
                .build();

        final Row headerRowThree = Row.builder()
                .add(CellText.builder().text("Name of Receiving Institution: Rzeszow University of Technology PL RZESZOW01" + "\n" + "Powstańców Warszawy 12, 35-959 Rzeszów, Poland" + "\n" + "Faculty/Department of Electrical and Computer Engineering" + "\n" + "Name of Dept. Coordinator: dr inż. Sławomir Samolej" + "\n" + "Tel:" + " " + "+48177432055" + "    " + "Fax:" + " " + "+48178542910" + "    " + "E-Mail:" + " " + "ssamolej@prz.edu.pl").horizontalAlignment(HorizontalAlignment.LEFT).borderWidth(1).build())
                .backgroundColor(Color.WHITE)
                .textColor(Color.BLACK)
                .font(ownFont).fontSize(10)
                .horizontalAlignment(HorizontalAlignment.LEFT)
                .build();

        tableOneTranscriptOfRecordsBuilder.addRow(headerRowOne);
        tableOneTranscriptOfRecordsBuilder.addRow(headerRowTwo);
        tableOneTranscriptOfRecordsBuilder.addRow(headerRowThree);

        return tableOneTranscriptOfRecordsBuilder.build();

    }

    private Table createTranscriptOfRecordsSecondTable(List<PdfStudent> students, List<PdfCourse> courses, List<PdfSendingInstitution> sendingInstitutions) throws IOException {

        ownFont = PDType0Font.load(document, new File("C:/Windows/Fonts/arial.ttf"));

        final Table.TableBuilder tableOneTranscriptOfRecordsBuilder = Table.builder()
                .addColumnsOfWidth(42, 197, 46, 70, 70, 70)
                .fontSize(10)
                .font(ownFont)
                .borderColor(Color.BLACK);

        final Row headerOneRow = Row.builder()
                .add(CellText.builder().text("Course Unit Code").horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                .add(CellText.builder().text("Title of the Course Unit").horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                .add(CellText.builder().text("Duration of Course Unit").horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                .add(CellText.builder().text("Local Grade").horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                .add(CellText.builder().text("ECTS Grade").horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                .add(CellText.builder().text("ECTS Credit").horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                .backgroundColor(Color.WHITE)
                .textColor(Color.BLACK)
                .font(ownFont).fontSize(10)
                .horizontalAlignment(HorizontalAlignment.LEFT)
                .build();

        tableOneTranscriptOfRecordsBuilder.addRow(headerOneRow);

        List<String> courseUnitCodes = new LinkedList<>();
        List<String> courseUnitNames = new LinkedList<>();
        List<String> coursesDuration = new LinkedList<>();
        List<Long> coursesECTSCredits = new LinkedList<>();

        if (courses.size() <= 10) {
            for (int i = 0; i < courses.size(); i++) {
                for (PdfCourse course : courses) {
                    courseUnitCodes = courses.stream().map(PdfCourse::getCourseUnitCode).collect(Collectors.toList());
                    courseUnitNames = courses.stream().map(PdfCourse::getName).collect(Collectors.toList());
                    coursesDuration = courses.stream().map(PdfCourse::getDurationOfCourseUnit).collect(Collectors.toList());
                    coursesECTSCredits = courses.stream().map(PdfCourse::getCredits).collect(Collectors.toList());
                }

                tableOneTranscriptOfRecordsBuilder.addRow(Row.builder()
                        .add(CellText.builder().text("" + courseUnitCodes.get(i)).horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                        .add(CellText.builder().text("" + courseUnitNames.get(i)).horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                        .add(CellText.builder().text("" + coursesDuration.get(i)).horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                        .add(CellText.builder().text("").horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                        .add(CellText.builder().text("").horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                        .add(CellText.builder().text("" + coursesECTSCredits.get(i)).horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                        .backgroundColor(Color.WHITE)
                        .textColor(Color.BLACK)
                        .font(ownFont).fontSize(10)
                        .horizontalAlignment(HorizontalAlignment.LEFT)
                        .build())
                        .wordBreak(true);
            }
        } else {
            throw new PDFNotCreatedException();
        }

        return tableOneTranscriptOfRecordsBuilder.build();
    }

    public ByteArrayOutputStream createTranscriptOfRecordsDocument(List<PdfStudent> students, List<PdfCourse> courses, List<PdfSendingInstitution> sendingInstitutions) throws IOException {

        List<String> studentAcademicYears = new LinkedList<>();
        List<String> studentFieldsOfStudy = new LinkedList<>();

        for (int i = 0; i < students.size(); i++) {
            for (PdfStudent student : students) {
                studentAcademicYears = students.stream().map(PdfStudent::getAcademicYear).collect(Collectors.toList());
                studentFieldsOfStudy = students.stream().map(PdfStudent::getFieldOfStudy).collect(Collectors.toList());
            }
        }

        document = new PDDocument();
        PDPage page = new PDPage(PAGE_FORMAT);
        ownNarrowFont = PDType0Font.load(document, new File("C:/Windows/Fonts/ARIALN.ttf"));
        ownFont = PDType0Font.load(document, new File("C:/Windows/Fonts/arial.ttf"));
        document.addPage(page);

        String image = Image.class.getResource("/prz.png").getFile();
        PDImageXObject pdImage = PDImageXObject.createFromFile(image, document);
        String titleImage = Image.class.getResource("/ects.png").getFile();
        PDImageXObject pdImage2 = PDImageXObject.createFromFile(titleImage, document);

        float startY = PAGE_FORMAT.getHeight();
        PDDocumentInformation pdd = document.getDocumentInformation();
        pdd.setAuthor("Paweł Kocan");
        pdd.setTitle("Transcript of Records");
        pdd.setCreator("Paweł Kocan");
        pdd.setSubject("Transcript of Records Document");
        Calendar date = Calendar.getInstance();
        pdd.setCreationDate(date);
        pdd.setModificationDate(date);
        pdd.setKeywords("Bachelor - IT system for managing Erasmus+ student services");
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.drawXObject(pdImage, 50, 735, 200, 61);
        contentStream.drawXObject(pdImage2, 165, 695, 270, 30);
        contentStream.setLeading(0.0f);
        Table table = createTranscriptOfRecordsFirstTable(students, courses, sendingInstitutions);
        TableDrawer drawer = createTableTranscriptOfRecordsFirstDrawer(table);
        float result4 = table.getHeight();
        drawer.contentStream(contentStream).draw();
        Table table2 = createTranscriptOfRecordsSecondTable(students, courses, sendingInstitutions);
        TableDrawer drawer2 = createTableTranscriptOfRecordsSecondDrawer(table2);
        float result5 = table2.getHeight();
        drawer2.contentStream(contentStream).draw();
        contentStream.beginText();
        contentStream.setFont(ownNarrowFont, 12);
        float drawFooter = result4 + result5;
        contentStream.newLineAtOffset(50, 677);
        String titleText1 = "ACADEMIC YEAR" + " " + studentAcademicYears.get(0) + " " + "FIELD OF STUDY:" + " " + studentFieldsOfStudy.get(0);
        String text1 = "RECEIVING INSTITUTION";
        String text2 = "Departmental Coordinator's signature                                                                Institutional Coordinator's signature";
        String text3 = "............................................................                                                                ........................................................";
        String text4 = "Date: ...................................................                                                                Date: ..............................................";
        String text5 = "                                                             ______________________________                                    ";
        String text6 = "                                                                        Stamp of the Institution                                                 ";
        String text7 = "Description of the grading system: Local grade - ECTS Grade: 5.0 - A, 4.5 - B, 4 - C, 3.5 - D, 3 - E, 2.5 - FX, 2 - F";
        contentStream.setLeading(25.0f);
        contentStream.showText(titleText1);
        contentStream.newLine();
        contentStream.newLineAtOffset(0, -drawFooter);
        contentStream.setFont(ownFontNarrowBold, 12);
        contentStream.showText(text1);
        contentStream.newLine();
        contentStream.setFont(ownNarrowFont, 12);
        contentStream.showText(text2);
        contentStream.newLine();
        contentStream.showText(text3);
        contentStream.newLine();
        contentStream.showText(text4);
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText(text5);
        contentStream.setLeading(12.5f);
        contentStream.newLine();
        contentStream.showText(text6);
        contentStream.setLeading(25.0f);
        contentStream.newLine();
        contentStream.showText(text7);
        contentStream.endText();
        contentStream.close();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();
        return byteArrayOutputStream;
    }

    private Table createIndividualCurriculumFirstTable(List<PdfStudent> students, List<PdfCourse> courses, List<PdfSendingInstitution> sendingInstitutions) throws IOException {

        List<String> studentAcademicYears = new LinkedList<>();

        for (int i = 0; i < students.size(); i++) {
            for (PdfStudent student : students) {
                studentAcademicYears = students.stream().map(PdfStudent::getAcademicYear).collect(Collectors.toList());
            }
        }

        timesNewRomanNormal = PDType0Font.load(document, new File("C:/Windows/Fonts/times.ttf"));
        timesNewRomanBold = PDType0Font.load(document, new File("C:/Windows/Fonts/timesbd.ttf"));

        final Table.TableBuilder tableOneIndividualCurriculumBuilder = Table.builder()
                .addColumnsOfWidth(495)
                .fontSize(11)
                .font(timesNewRomanNormal)
                .borderColor(Color.BLACK);

        final Row headerRowOne = Row.builder()
                .add(CellText.builder().text("Rok akademicki" + " " + studentAcademicYears.get(0)).horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                .backgroundColor(Color.WHITE)
                .textColor(Color.BLACK)
                .font(timesNewRomanNormal).fontSize(11)
                .horizontalAlignment(HorizontalAlignment.LEFT)
                .build();

        tableOneIndividualCurriculumBuilder.addRow(headerRowOne);

        return tableOneIndividualCurriculumBuilder.build();

    }

    private Table createIndividualCurriculumSecondTable(List<PdfStudent> students, List<PdfCourse> courses, List<PdfSendingInstitution> sendingInstitutions) throws IOException {

        timesNewRomanNormal = PDType0Font.load(document, new File("C:/Windows/Fonts/times.ttf"));
        timesNewRomanBold = PDType0Font.load(document, new File("C:/Windows/Fonts/timesbd.ttf"));

        final Table.TableBuilder tableTwoIndividualCurriculumBuilder = Table.builder()
                .addColumnsOfWidth(247, 248)
                .fontSize(11)
                .font(timesNewRomanNormal)
                .borderColor(Color.BLACK);

        final Row headerRowTwo = Row.builder()
                .add(CellText.builder().text("Przedmiot").horizontalAlignment(HorizontalAlignment.LEFT).borderWidth(1).verticalAlignment(VerticalAlignment.MIDDLE).build())
                .add(CellText.builder().text("Wydział prowadzący zajęcia; stopień studiów;" + "\n" + "semestr; liczba godzin; punkty ECTS").horizontalAlignment(HorizontalAlignment.CENTER).borderWidth(1).build())
                .backgroundColor(Color.WHITE)
                .textColor(Color.BLACK)
                .font(timesNewRomanNormal).fontSize(11)
                .horizontalAlignment(HorizontalAlignment.LEFT)
                .build();

        tableTwoIndividualCurriculumBuilder.addRow(headerRowTwo);

        List<String> courseUnitNames = new LinkedList<>();
        List<Long> coursesECTSCredits = new LinkedList<>();
        List<String> courseDepartments = new LinkedList<>();
        List<String> courseSemesters = new LinkedList<>();
        List<String> semesters = new LinkedList<>();
        List<String> studentDegreesOfStudy = new LinkedList<>();
        List<String> degreesOfStudy = new LinkedList<>();
        List<String> courseNumbersOfHours = new LinkedList<>();

        for (int i = 0; i < students.size(); i++) {
            for (PdfStudent student : students) {
                studentDegreesOfStudy = students.stream().map(PdfStudent::getDegreeOfStudy).collect(Collectors.toList());
            }

        }

        if (courses.size() <= 10) {
            for (int i = 0; i < courses.size(); i++) {
                for (PdfCourse course : courses) {
                    courseUnitNames = courses.stream().map(PdfCourse::getName).collect(Collectors.toList());
                    coursesECTSCredits = courses.stream().map(PdfCourse::getCredits).collect(Collectors.toList());
                    courseDepartments = courses.stream().map(PdfCourse::getDepartment).collect(Collectors.toList());
                    courseSemesters = courses.stream().map(PdfCourse::getSemester).collect(Collectors.toList());
                    courseNumbersOfHours = courses.stream().map(PdfCourse::getNumberOfHours).collect(Collectors.toList());

                    if (courseSemesters.get(i).contains("S")) {
                        ((LinkedList<String>) semesters).push("L");
                    } else {
                        ((LinkedList<String>) semesters).push("Z");
                    }

                    if (studentDegreesOfStudy.get(0).contains("B")) {
                        ((LinkedList<String>) degreesOfStudy).push("I st.");
                    } else {
                        ((LinkedList<String>) degreesOfStudy).push("II st.");
                    }
                }


                tableTwoIndividualCurriculumBuilder.addRow(Row.builder()
                        .add(CellText.builder().text("" + courseUnitNames.get(i)).horizontalAlignment(HorizontalAlignment.LEFT).verticalAlignment(VerticalAlignment.MIDDLE).borderWidth(1).build())
                        .add(CellText.builder().text("" + courseDepartments.get(i) + " " + degreesOfStudy.get(i) + "; " + "Sem." + " " + semesters.get(i) + ";" + " " + courseNumbersOfHours.get(i) + "; " + coursesECTSCredits.get(i) + " " + "ECTS").horizontalAlignment(HorizontalAlignment.CENTER).verticalAlignment(VerticalAlignment.MIDDLE).borderWidth(1).build())
                        .backgroundColor(Color.WHITE)
                        .textColor(Color.BLACK)
                        .font(timesNewRomanNormal).fontSize(11)
                        .horizontalAlignment(HorizontalAlignment.LEFT)
                        .build())
                        .wordBreak(true);
            }
        } else {
            throw new PDFNotCreatedException();
        }

        return tableTwoIndividualCurriculumBuilder.build();

    }

    public List<String> FormatTextFunction(String textName, float width, int fontSize) throws IOException {
        int lastSpace = -1;
        List<String> lines = new ArrayList<String>();
        while (textName.length() > 0) {
            int spaceIndex = textName.indexOf(' ', lastSpace + 1);
            if (spaceIndex < 0)
                spaceIndex = textName.length();
            String subString = textName.substring(0, spaceIndex);
            float size = fontSize * timesNewRomanNormal.getStringWidth(subString) / 1000;
            if (size > width) {
                if (lastSpace < 0)
                    lastSpace = spaceIndex;
                subString = textName.substring(0, lastSpace);
                lines.add(subString);
                textName = textName.substring(lastSpace).trim();
                lastSpace = -1;
            } else if (spaceIndex == textName.length()) {
                lines.add(textName);
                textName = "";
            } else {
                lastSpace = spaceIndex;
            }
        }
        return lines;
    }

    public ByteArrayOutputStream createIndividualCurriculumDocument(List<PdfStudent> students, List<PdfCourse> courses, List<PdfSendingInstitution> sendingInstitutions) throws IOException {

        List<String> studentSexes = new LinkedList<>();
        List<String> studentNationalities = new LinkedList<>();
        List<String> studentFirstNames = new LinkedList<>();
        List<String> studentLastNames = new LinkedList<>();
        List<String> studentFieldsOfStudy = new LinkedList<>();
        List<String> studentAcademicYears = new LinkedList<>();
        String sex1;
        String sex2 = new String();
        String sex3 = new String();
        List<String> studentSendingInstitutionNames = new LinkedList<>();
        List<String> studentSendingInstitutionCountries = new LinkedList<>();

        for (int i = 0; i < students.size(); i++) {
            for (PdfStudent student : students) {
                studentSexes = students.stream().map(PdfStudent::getStudentSex).collect(Collectors.toList());
                studentNationalities = students.stream().map(PdfStudent::getStudentNationality).collect(Collectors.toList());
                studentFirstNames = students.stream().map(PdfStudent::getStudentFirstName).collect(Collectors.toList());
                studentLastNames = students.stream().map(PdfStudent::getStudentLastName).collect(Collectors.toList());
                studentFieldsOfStudy = students.stream().map(PdfStudent::getFieldOfStudy).collect(Collectors.toList());
                studentAcademicYears = students.stream().map(PdfStudent::getAcademicYear).collect(Collectors.toList());
            }
        }

        for (int i = 0; i < sendingInstitutions.size(); i++) {
            for (PdfSendingInstitution sendingInstitution : sendingInstitutions) {
                studentSendingInstitutionNames = sendingInstitutions.stream().map(PdfSendingInstitution::getSendingInstitutionName).collect(Collectors.toList());
                studentSendingInstitutionCountries = sendingInstitutions.stream().map(PdfSendingInstitution::getSendingInstitutionCountry).collect(Collectors.toList());
            }
        }

        int result = 0;

        for (int i = 0; i < courses.size(); i++) {
            for (PdfCourse course : courses) {
                result = courses.stream().map(PdfCourse::getCredits).mapToInt(Long::intValue).sum();
            }
        }


        if (studentSexes.get(0).contains("FEMALE")) {
            sex1 = "studentki";
            sex2 = "studentka";
            sex3 = "mogła";
        } else if (studentSexes.get(0).contains("MALE")) {
            sex1 = "studenta";
            sex2 = "student";
            sex3 = "mógł";
        } else {
            sex1 = "studenta";
            sex2 = "student";
            sex3 = "mógł";
        }

        document = new PDDocument();
        PDPage page = new PDPage(PAGE_FORMAT);
        ownFontNarrowBold = PDType0Font.load(document, new File("C:/Windows/Fonts/ARIALNB.ttf"));
        ownNarrowFont = PDType0Font.load(document, new File("C:/Windows/Fonts/ARIALN.ttf"));
        ownFont = PDType0Font.load(document, new File("C:/Windows/Fonts/arial.ttf"));
        ownFontBoldBlack = PDType0Font.load(document, new File("C:/Windows/Fonts/ariblk.ttf"));
        timesNewRomanNormal = PDType0Font.load(document, new File("C:/Windows/Fonts/times.ttf"));
        timesNewRomanBold = PDType0Font.load(document, new File("C:/Windows/Fonts/timesbd.ttf"));
        document.addPage(page);
        PDDocumentInformation pdd = document.getDocumentInformation();
        pdd.setAuthor("Paweł Kocan");
        pdd.setTitle("Individual Curriculum");
        pdd.setCreator("Paweł Kocan");
        pdd.setSubject("Individual Curriculum Document");
        Calendar date = Calendar.getInstance();
        pdd.setCreationDate(date);
        pdd.setModificationDate(date);
        pdd.setKeywords("Bachelor - IT system for managing Erasmus+ student services");
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        PDRectangle mediabox = page.getMediaBox();
        float margin = 72;
        float width = mediabox.getWidth() - 2 * margin;
        float startX = mediabox.getLowerLeftX() + margin;
        float startY = mediabox.getUpperRightY() - margin;
        contentStream.setLeading(17.5f);
        contentStream.beginText();
        contentStream.setFont(timesNewRomanBold, 14);
        String titleText1 = "Indywidualny program nauczania dla" + " " + sex1 + " " + "z" + " " + studentNationalities.get(0) + ":";
        String titleText2 = "" + studentFirstNames.get(0) + " " + studentLastNames.get(0);
        String titleText3 = "na Wydziale Elektrotechniki i Informatyki Politechniki Rzeszowskiej,";
        String titleText4 = "kierunek" + " " + studentFieldsOfStudy.get(0) + ", studia indywidualne";
        String bodyText1 = "Podstawa prawna:";
        String bodyText2 = "-  Regulamin studiów w Politechnice Rzeszowskiej";
        String resolution1 = "(Uchwała Nr 49/2017 Senatu Politechniki Rzeszowskiej im. Ignacego Łukasiewicza z dnia 20 kwietnia 2017 r.);";
        String bodyText3 = "-  Oferta kursów w Politechnice Rzeszowskiej dla studentów z programu Erasmus+ na lata";
        String bodyText4 = "-  Porozumienie pomiędzy";
        String bodyText5 = "a Politechniką Rzeszowską zawarte w ramach programu Erasmus+;";
        String bodyText6 = "-  Uchwała";
        String resolution2 = "14/9/2018 Rady WEiI PRz z 19 września 2018 r.";
        String bodyText7 = "w sprawie liczby ustalenia liczby godzin";
        String bodyText8 = "do rozliczania zajęć prowadzonych w języku obcym według indywidualnych planów i programów kształcenia, w tym dla cudzoziemców studiujących w ramach programu Erasmus+ lub w trybie indywidualnym oraz z tytułu opieki nad studentem studiującym w ramach programu Erasmus+.";
        int fontSize = 14;
        float titleWidth1 = timesNewRomanBold.getStringWidth(titleText1) / 1000 * fontSize;
        float titleWidth2 = timesNewRomanBold.getStringWidth(titleText2) / 1000 * fontSize;
        float titleWidth3 = timesNewRomanBold.getStringWidth(titleText3) / 1000 * fontSize;
        float titleWidth4 = timesNewRomanBold.getStringWidth(titleText4) / 1000 * fontSize;
        float titleHeight = timesNewRomanBold.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
        contentStream.moveTextPositionByAmount((page.getMediaBox().getWidth() - titleWidth1) / 2, page.getMediaBox().getHeight() - 40 - titleHeight);
        contentStream.drawString(titleText1);
        contentStream.endText();
        contentStream.beginText();
        contentStream.moveTextPositionByAmount((page.getMediaBox().getWidth() - titleWidth2) / 2, page.getMediaBox().getHeight() - 58 - titleHeight);
        contentStream.drawString(titleText2);
        contentStream.endText();
        contentStream.beginText();
        contentStream.moveTextPositionByAmount((page.getMediaBox().getWidth() - titleWidth3) / 2, page.getMediaBox().getHeight() - 74 - titleHeight);
        contentStream.drawString(titleText3);
        contentStream.endText();
        contentStream.beginText();
        contentStream.moveTextPositionByAmount((page.getMediaBox().getWidth() - titleWidth4) / 2, page.getMediaBox().getHeight() - 92 - titleHeight);
        contentStream.drawString(titleText4);
        contentStream.endText();
        int fontSize2 = 20;
        List<String> lines = new ArrayList<String>();
        int lastSpace = -1;
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 710);
        contentStream.setFont(timesNewRomanNormal, 11);
        contentStream.setLeading(14.5f);
        contentStream.showText(bodyText1);
        contentStream.newLine();
        contentStream.showText(bodyText2);
        contentStream.showText(" ");
        lines = FormatTextFunction(resolution1, width, 20);
        for (String line : lines) {
            contentStream.showText(line);
            contentStream.newLineAtOffset(0, -14.5f);
        }
        contentStream.showText(bodyText3 + " ");
        contentStream.showText(studentAcademicYears.get(0) + ";");
        contentStream.newLine();
        contentStream.showText(bodyText4 + " " + studentSendingInstitutionNames.get(0) + ", " + studentSendingInstitutionCountries.get(0) + " ");
        lines = FormatTextFunction(bodyText5, width, 26);
        for (String line : lines) {
            contentStream.showText(line);
            contentStream.newLineAtOffset(0, -14.5f);
        }
        contentStream.showText(bodyText6 + " " + resolution2);
        contentStream.showText(bodyText7 + " ");
        contentStream.newLine();
        lines = FormatTextFunction(bodyText8, width, 10);
        for (String line : lines) {
            contentStream.showText(line);
            contentStream.newLineAtOffset(0, -14.5f);
        }
        contentStream.endText();
        Table table = createIndividualCurriculumFirstTable(students, courses, sendingInstitutions);
        TableDrawer drawer = createTableIndividualCurriculumFirstDrawer(table);
        drawer.contentStream(contentStream).draw();
        Table table2 = createIndividualCurriculumSecondTable(students, courses, sendingInstitutions);
        TableDrawer drawer2 = createTableIndividualCurriculumSecondDrawer(table2);
        drawer2.contentStream(contentStream).draw();
        float result1 = table.getHeight();
        float result2 = table2.getHeight();
        float drawFooter = result1 + result2;
        String footerText1 = "Łącznie w/w" + " " + sex2 + " " + "będzie" + " " + sex3 + " " + "zdobyć" + " " + result + " " + "punktów ECTS.";
        String footerText2 = "Opiekunem ww. studenta będzie dr inż. Sławomir Samolej,";
        String footerText3 = "WEiI, Katedra Informatyki i Automatyki (ssamolej@prz.edu.pl, tel. +48 17 7432055)";
        String footerText4 = "Rzeszów, ...................................... r.";
        contentStream.beginText();
        contentStream.newLineAtOffset(50, -drawFooter + 550);
        contentStream.setFont(timesNewRomanNormal, 11);
        contentStream.setLeading(14.5f);
        contentStream.showText(footerText1);
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText(footerText2);
        contentStream.newLine();
        contentStream.showText(footerText3);
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText(footerText4);
        contentStream.endText();
        contentStream.close();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();
        return byteArrayOutputStream;
    }

}
