package jei.error;

public class NoValueException extends JeiException
{
	private static final long serialVersionUID = 1L;
	
	public NoValueException() {
		super();
	}
	public NoValueException(String message, Throwable cause) {
		super(message, cause);
	}
	public NoValueException(String message) {
		super(message);
	}
	public NoValueException(Throwable cause) {
		super(cause);
	}
}
