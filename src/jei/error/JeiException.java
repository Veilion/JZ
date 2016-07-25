package jei.error;

public class JeiException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public JeiException() {
		super();
	}
	public JeiException(String message, Throwable cause) {
		super(message, cause);
	}
	public JeiException(String message) {
		super(message);
	}
	public JeiException(Throwable cause) {
		super(cause);
	}
}
