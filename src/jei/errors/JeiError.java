package jei.errors;

public class JeiError extends Error implements JeiThrowable
{
    private static final long serialVersionUID = 1L;

    public JeiError() {
        super();
    }

    public JeiError(String message) {
        super(message);
    }

    public JeiError(Throwable cause) {
        super(cause);
    }

    public JeiError(String message, Throwable cause) {
        super(message, cause);
    }
}
