package jei.errors.coding;

import jei.errors.JeiError;

public class CodingError extends JeiError
{
    private static final long serialVersionUID = 1L;

    public CodingError() {
        super();
    }

    public CodingError(String message) {
        super(message);
    }

    public CodingError(Throwable cause) {
        super(cause);
    }

    public CodingError(String message, Throwable cause) {
        super(message, cause);
    }
}
