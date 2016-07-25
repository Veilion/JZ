package jei.error;

public class IllegalInvocationError extends JeiError
{
	private static final long serialVersionUID = 1L;
	
	public IllegalInvocationError() {
		super();
	}
	public IllegalInvocationError(String message, Throwable cause) {
		super(message, cause);
	}
	public IllegalInvocationError(String message) {
		super(message);
	}
	public IllegalInvocationError(Throwable cause) {
		super(cause);
	}
}
