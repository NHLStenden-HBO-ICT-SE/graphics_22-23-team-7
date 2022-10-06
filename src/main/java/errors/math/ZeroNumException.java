package errors.math;

public class ZeroNumException extends Exception{
    public ZeroNumException() {
        this(new Throwable("Number must not be 0"));
    }
    public ZeroNumException(String msg) {
        this(new Throwable(msg));
    }
    public ZeroNumException(Throwable err) {
        super(err);
    }
}
