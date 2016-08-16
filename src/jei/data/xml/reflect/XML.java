package jei.data.xml.reflect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jei.Base;
import jei.error.FileNotFoundException;
import jei.types.Type;

public class XML extends Base
{
	public static <T> T read(String file, Class<T> clazz) {
		return read(file, typeby(clazz));
	}
	public static <T> T read(String file, Type<T> type) {
		File xml = new File(file);
		if(!xml.exists()) {
			throw new FileNotFoundException("xml-file does not exist: " + file);
		}
		//TODO
		return null;
	}
	
	public static void write(String file, Object data) {
		try {
			File xml = new File(file);
			if(!xml.exists()) {
				xml.createNewFile();
			}
			StringBuilder builder = new StringBuilder();
			parse(data, builder, "");
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
				writer.write(builder.toString());
			}
		} catch(IOException e) {
			
		}
	}
	
	private static void parse(Object data, StringBuilder builder, String varName) {
		
//		Type<?> type = typeof(data);
//		Data note = type.notes().oneOf(Data.class).orThrow(new XmlWriteException("not a parseable type: " + type.getQualifiedName()));
//		String nodeName = note.value();
//		if(nodeName.isEmpty()) {
//			if(varName.isEmpty()) {
//				nodeName = type.getName();
//			} else {
//				nodeName = varName;
//			}
//		}
		
	}
	
	public XML() {
		
	}
}
