package errors.math;

public class NegativeNumException extends Exception {
    public NegativeNumException() {
        this(new Throwable("Number must not be negative"));
    }
    public NegativeNumException(String msg) {
        this(new Throwable(msg));
    }
    public NegativeNumException(Throwable err) {
        super(err);
    }
}
