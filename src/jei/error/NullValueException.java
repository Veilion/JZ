package jei.error;

public class NullValueException extends JeiException
{
	private static final long serialVersionUID = 1L;
	
	public NullValueException() {
		super();
	}
	public NullValueException(String message, Throwable cause) {
		super(message, cause);
	}
	public NullValueException(String message) {
		super(message);
	}
	public NullValueException(Throwable cause) {
		super(cause);
	}
}
