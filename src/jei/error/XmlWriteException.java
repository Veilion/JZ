package jei.error;

public class XmlWriteException extends XmlParseException
{
	private static final long serialVersionUID = 1L;
	
	public XmlWriteException() {
		super();
	}
	public XmlWriteException(String message, Throwable cause) {
		super(message, cause);
	}
	public XmlWriteException(String message) {
		super(message);
	}
	public XmlWriteException(Throwable cause) {
		super(cause);
	}
}
