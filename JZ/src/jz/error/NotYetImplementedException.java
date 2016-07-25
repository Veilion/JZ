package jz.error;

public class NotYetImplementedException extends RuntimeException
{
	private static final long serialVersionUID = -2670628720536174755L;
	
	private static final String
		DEFAULT_MESSAGE = "method not implemented yet";
	
	public NotYetImplementedException() {
		this(DEFAULT_MESSAGE);
	}
	public NotYetImplementedException(String message) {
		super(message);
	}
	public NotYetImplementedException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
