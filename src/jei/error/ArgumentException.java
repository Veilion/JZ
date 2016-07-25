package jei.error;

public class ArgumentException extends JeiException
{
	private static final long serialVersionUID = 1L;
	
	public ArgumentException() {
		super();
	}
	public ArgumentException(String message) {
		super(message);
	}
	public ArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
	public ArgumentException(Throwable cause) {
		super(cause);
	}
}
