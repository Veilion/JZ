package jei.errors.arguments;

import jei.errors.JeiException;

public class ArgumentException extends JeiException
{
    private static final long serialVersionUID = 1L;

    public ArgumentException() {
        super();
    }

    public ArgumentException(String message) {
        super(message);
    }

    public ArgumentException(Throwable cause) {
        super(cause);
    }

    public ArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
