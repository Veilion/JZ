package jei.error;

public class NullArgumentException extends ArgumentException
{
	private static final long serialVersionUID = 1L;
	
	public NullArgumentException() {
		super("argument cannot be null");
	}
	public NullArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
	public NullArgumentException(String message) {
		super(message);
	}
	public NullArgumentException(Throwable cause) {
		super(cause);
	}
}
