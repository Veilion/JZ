package jei.error;

public class ParseException extends JeiException
{
	private static final long serialVersionUID = 1L;
	
	public ParseException() {
		super();
	}
	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}
	public ParseException(String message) {
		super(message);
	}
	public ParseException(Throwable cause) {
		super(cause);
	}
}
