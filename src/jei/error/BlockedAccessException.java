package jei.error;

public class BlockedAccessException extends JeiException
{
	private static final long serialVersionUID = 1L;
	
	public BlockedAccessException() {
		super();
	}
	public BlockedAccessException(String message, Throwable cause) {
		super(message, cause);
	}
	public BlockedAccessException(String message) {
		super(message);
	}
	public BlockedAccessException(Throwable cause) {
		super(cause);
	}
}
