package pl.edu.prz.master.thesis.backend.exception;

public class ValidPeriodsException extends RuntimeException {
    public ValidPeriodsException() {
        super("One or more of periods are wrong.");
    }
}
