package jei.error;

public class JeiError extends Error 
{
	private static final long serialVersionUID = 1L;
	
	public JeiError() {
		super();
	}
	public JeiError(String message, Throwable cause) {
		super(message, cause);
	}
	public JeiError(String message) {
		super(message);
	}
	public JeiError(Throwable cause) {
		super(cause);
	}
}
