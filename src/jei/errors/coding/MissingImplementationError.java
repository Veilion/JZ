package jei.errors.coding;

public class MissingImplementationError extends CodingError
{
    private static final long serialVersionUID = 1L;

    public MissingImplementationError() {
        super();
    }

    public MissingImplementationError(String message) {
        super(message);
    }

    public MissingImplementationError(Throwable cause) {
        super(cause);
    }

    public MissingImplementationError(String message, Throwable cause) {
        super(message, cause);
    }
}
