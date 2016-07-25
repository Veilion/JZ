package jz.error;

public class UnknownTypeException extends RuntimeException
{
	private static final long serialVersionUID = 2630126646416308145L;

	private static final String
	DEFAULT_MESSAGE = "method not implemented yet";

	public UnknownTypeException() {
		this(DEFAULT_MESSAGE);
	}
	public UnknownTypeException(String message) {
		super(message);
	}
	public UnknownTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
