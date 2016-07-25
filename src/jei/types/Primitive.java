package jei.types;

import static jei.$.format;

import jei.error.ArgumentException;
import jei.error.UnreachableError;

public enum Primitive
{
	BYTE(byte.class, Byte.class),
	SHORT(short.class, Short.class),
	INT(int.class, Integer.class),
	LONG(long.class, Long.class), 
	FLOAT(float.class, Float.class),
	DOUBLE(double.class, Double.class),
	CHAR(char.class, Character.class),
	BOOLEAN(boolean.class, Boolean.class),
	;
	
	private final Class<?>
		type,
		boxedType;
	
	private Primitive(Class<?> type, Class<?> boxedType) {
		this.type = type;
		this.boxedType = boxedType;
	}
	public Class<?> getType() {
		return this.type;
	}
	public Class<?> getBoxedType() {
		return this.boxedType;
	}
	
	public static Primitive ofType(Class<?> clazz) {
		if(!clazz.isPrimitive()) {
			throw new ArgumentException(format("class {0} is not primitive", clazz));
		}
		for(Primitive primitive : values()) {
			if(primitive.getType() == clazz) {
				return primitive;
			}
		}
		throw new UnreachableError(format("primitive type {0} not checked", clazz));
	}
}
