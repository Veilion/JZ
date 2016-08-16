package jei.error;

import jei.$;

public class UndefinedEntryException extends JeiException
{
	private static final long serialVersionUID = 1L;
	
	public UndefinedEntryException() {
		super();
	}
	public UndefinedEntryException(int index, int min, int max) {
		super($.format("index: {0}, bounds: {1} to {2}", index, min, max));
	}
	public UndefinedEntryException(String message, Throwable cause) {
		super(message, cause);
	}
	public UndefinedEntryException(String message) {
		super(message);
	}
	public UndefinedEntryException(Throwable cause) {
		super(cause);
	}
}
