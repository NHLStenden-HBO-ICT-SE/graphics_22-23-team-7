package errors.math;

public class NegativeNumException extends Exception {
    public NegativeNumException() {
        super("Number must not be negative");
    }
    public NegativeNumException(Throwable err) {
        super("Number must not be negative", err);
    }
}
