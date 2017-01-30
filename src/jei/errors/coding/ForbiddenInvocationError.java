package jei.errors.coding;

public class ForbiddenInvocationError extends CodingError
{
    private static final long serialVersionUID = 1L;

    public ForbiddenInvocationError() {
        super();
    }

    public ForbiddenInvocationError(String message) {
        super(message);
    }

    public ForbiddenInvocationError(Throwable cause) {
        super(cause);
    }

    public ForbiddenInvocationError(String message, Throwable cause) {
        super(message, cause);
    }
}
