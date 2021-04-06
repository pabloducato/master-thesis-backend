package pl.edu.prz.master.thesis.backend.exception;

public class PDFNotCreatedException extends RuntimeException {
    public PDFNotCreatedException() {
        super("PDF was not created");
    }
}

