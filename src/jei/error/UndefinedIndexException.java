package jei.error;

import jei.$;

public class UndefinedIndexException extends JeiException
{
	private static final long serialVersionUID = 1L;
	
	public UndefinedIndexException() {
		super();
	}
	public UndefinedIndexException(int index, int min, int max) {
		super($.format("index: {0}, bounds: {1} to {2}", index, min, max));
	}
	public UndefinedIndexException(String message, Throwable cause) {
		super(message, cause);
	}
	public UndefinedIndexException(String message) {
		super(message);
	}
	public UndefinedIndexException(Throwable cause) {
		super(cause);
	}
}
