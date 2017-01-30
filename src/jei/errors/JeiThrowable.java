package jei.errors;

import java.io.PrintWriter;
import java.io.StringWriter;

public interface JeiThrowable 
{
	void printStackTrace(PrintWriter writer);
	
	default String getStackTraceString() {
		StringWriter sWriter = new StringWriter();
		PrintWriter pWriter = new PrintWriter(sWriter);
		this.printStackTrace(pWriter);
		return sWriter.toString();
	}
}
