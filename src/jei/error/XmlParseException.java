package jei.error;

public class XmlParseException extends ParseException
{
	private static final long serialVersionUID = 1L;
	
	public XmlParseException() {
		super();
	}
	public XmlParseException(String message, Throwable cause) {
		super(message, cause);
	}
	public XmlParseException(String message) {
		super(message);
	}
	public XmlParseException(Throwable cause) {
		super(cause);
	}
}
