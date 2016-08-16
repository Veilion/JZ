package jei.error;

public class NamingException extends JeiException
{
	private static final long serialVersionUID = 1L;
	
	public NamingException() {
		super();
	}
	public NamingException(String message, Throwable cause) {
		super(message, cause);
	}
	public NamingException(String message) {
		super(message);
	}
	public NamingException(Throwable cause) {
		super(cause);
	}
}
