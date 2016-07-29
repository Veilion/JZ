package jei.error;

public class NotStaticException extends ReflectException 
{
	private static final long serialVersionUID = 1L;
	
	public NotStaticException() {
		super();
	}
	public NotStaticException(String message, Throwable cause) {
		super(message, cause);
	}
	public NotStaticException(String message) {
		super(message);
	}
	public NotStaticException(Throwable cause) {
		super(cause);
	}
}
