package jei.utils;

import jei.StaticBase;
import jei.functional.Producer;
import jei.types.Primitive;

public abstract class UString extends StaticBase
{
	private UString() {}
	
	public static String stringify(Object object) {
		if(object == null) {
			return "null";
		}
		if(object.getClass().isArray()) {
			Class<?> compType = object.getClass().getComponentType();
			if(compType.isPrimitive()) {
				int size = 0;
				Producer<Integer, ?> get = null;
				switch(Primitive.ofType(compType)) {
					case BOOLEAN: {
						boolean[] array = (boolean[]) object;
						size = array.length;
						get = (index) -> array[index];
						break;
					}
					case BYTE: {
						char[] array = (char[]) object;
						size = array.length;
						get = (index) -> array[index];
						break;
					}
					case CHAR: {
						char[] array = (char[]) object;
						size = array.length;
						get = (index) -> array[index];
						break;
					}
					case DOUBLE: {
						double[] array = (double[]) object;
						size = array.length;
						get = (index) -> array[index];
						break;
					}
					case FLOAT: {
						float[] array = (float[]) object;
						size = array.length;
						get = (index) -> array[index];
						break;
					}
					case INT: {
						int[] array = (int[]) object;
						size = array.length;
						get = (index) -> array[index];
						break;
					}
					case LONG: {
						long[] array = (long[]) object;
						size = array.length;
						get = (index) -> array[index];
						break;
					}
					case SHORT: {
						short[] array = (short[]) object;
						size = array.length;
						get = (index) -> array[index];
						break;
					}
				}
				StringBuilder result = new StringBuilder(compType.getSimpleName()).append('[').append(size).append("] {");
				int numLength = (size + "").length();
				for(int i = 0; i < size; ++i) {
					if(i != 0) {
						result.append(',');
					}
					result.append(String.format("\n    %" + numLength + "s: ", i)).append(stringify(get.invoke(i)).replace("\n", "\n    "));
				}
				return result.append(size > 0 ? "\n}" :"}").toString();
			} else if(compType.isArray()) {
				Object[] array = (Object[]) object;
				compType = compType.getComponentType();
				StringBuilder result = new StringBuilder(compType.getSimpleName()).append('[').append(array.length).append("] {");
				int numLength = (array.length + "").length();
				for(int i = 0; i < array.length; ++i) {
					if(i != 0) {
						result.append(',');
					}
					result.append(String.format("\n    %" + numLength + "s: ", i)).append(stringify(array[i]).replace("\n", "\n    "));
				}
				return result.append(array.length > 0 ? "\n}" : "}").toString();
			} else {
				Object[] array = (Object[]) object;
				StringBuilder result = new StringBuilder(compType.getSimpleName()).append('[').append(array.length).append("] {");
				int numLength = (array.length + "").length();
				for(int i = 0; i < array.length; ++i) {
					if(i != 0) {
						result.append(',');
					}
					result.append(String.format("\n    %" + numLength + "s: ", i)).append(stringify(array[i]).replace("\n", "\n    "));
				}
				return result.append(array.length > 0 ? "\n}" : "}").toString();
			}
		}
		return object.toString();
	}
	public static String format(String pattern, Object... elements) {
		StringBuilder builder = new StringBuilder(), current = new StringBuilder();
		boolean inBrace = false;
		for(char letter : pattern.toCharArray()) {
			if(inBrace) {
				if(letter == '}') {
					if(current.length() == 0) {
						builder.append("{}");
					} else {
						int index = Integer.valueOf(current.toString()); // cannot fail
						if(index < elements.length) {
							builder.append(elements[index] == null ? "null" : elements[index].toString());
						} else {
							builder.append('{').append(index).append('}');
						}
					}
					current.setLength(0);
					inBrace = false;
				} else if(letter == '0' || letter == '1' || letter == '2' || letter == '3' ||
						  letter == '4' || letter == '5' || letter == '6' || letter == '7' ||
						  letter == '8' || letter == '9') {
					current.append(letter);
				} else {
					builder.append('{').append(current.toString()).append(letter);
					current.setLength(0);
					inBrace = false;
				}
			} else {
				if(letter == '{') {
					inBrace = true;
				} else {
					builder.append(letter);
				}	
			}
		}
		if(inBrace) {
			builder.append('{');
			if(current.length() != 0) {
				builder.append(current);
			}
		}
		return builder.toString();
	}
	
	public static String removeEnd(String string, int amount) {
		if(amount > string.length() || amount <= 0) {
			return "";
		}
		return string.substring(0, string.length() - amount);
	}
	public static String removeStart(String string, int amount) {
		if(amount > string.length() || amount <= 0) {
			return "";
		}
		return string.substring(amount, string.length());
	}
	
	public static String reverse(String string) {
		return new StringBuilder(string).reverse().toString();
	}
	public static String ucFirst(String string) {
		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}
	public static String lcFirst(String string) {
		return Character.toLowerCase(string.charAt(0)) + string.substring(1);
	}
}
