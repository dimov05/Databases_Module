package course.springdata.intro.exception;

public class NotExistingEntityException extends RuntimeException {
    public NotExistingEntityException() {
    }

    public NotExistingEntityException(String message) {
        super(message);
    }

    public NotExistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistingEntityException(Throwable cause) {
        super(cause);
    }
}
