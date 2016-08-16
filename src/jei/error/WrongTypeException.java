package jei.error;

public class WrongTypeException extends JeiException
{
	private static final long serialVersionUID = 1L;
	
	public WrongTypeException() {
		super();
	}
	public WrongTypeException(String message, Throwable cause) {
		super(message, cause);
	}
	public WrongTypeException(String message) {
		super(message);
	}
	public WrongTypeException(Throwable cause) {
		super(cause);
	}
}
