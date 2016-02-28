package jz.error;

public class UnreachableException extends RuntimeException
{
	private static final long serialVersionUID = -7796119663196221706L;

	private static final String
		DEFAULT_MESSAGE = "normally unreachable point reached";
	
	public UnreachableException() {
		this(DEFAULT_MESSAGE);
	}
	public UnreachableException(String message) {
		this(message, null);
	}
	public UnreachableException(Throwable cause) {
		this(DEFAULT_MESSAGE, cause);
	}
	public UnreachableException(String message, Throwable cause) {
		super(message + "\nIf this Exception keeps on appearing, please contact the developers of JZ", cause);
	}

	
}
