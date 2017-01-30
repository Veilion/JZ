package jei.errors;

public class JeiException extends RuntimeException implements JeiThrowable
{
    private static final long serialVersionUID = 1L;

    public JeiException() {
        super();
    }

    public JeiException(String message) {
        super(message);
    }

    public JeiException(Throwable cause) {
        super(cause);
    }

    public JeiException(String message, Throwable cause) {
        super(message, cause);
    }
}
