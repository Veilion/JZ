package jei.error;

public class UnreachableError extends JeiError
{
	private static final long serialVersionUID = 1L;
	
	public UnreachableError() {
		super();
	}
	public UnreachableError(String message, Throwable cause) {
		super(message, cause);
	}
	public UnreachableError(String message) {
		super(message);
	}
	public UnreachableError(Throwable cause) {
		super(cause);
	}
}
