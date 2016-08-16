package jei.error;

public class FileNotFoundException extends JeiException
{
	private static final long serialVersionUID = 1L;
	
	public FileNotFoundException() {
		super();
	}
	public FileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	public FileNotFoundException(String message) {
		super(message);
	}
	public FileNotFoundException(Throwable cause) {
		super(cause);
	}
}
