package jei.error;

public class ReflectException extends JeiException
{
	private static final long serialVersionUID = 1L;
	
	public ReflectException() {
		super();
	}
	public ReflectException(String message, Throwable cause) {
		super(message, cause);
	}
	public ReflectException(String message) {
		super(message);
	}
	public ReflectException(Throwable cause) {
		super(cause);
	}
}
