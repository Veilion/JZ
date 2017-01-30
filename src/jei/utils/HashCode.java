package jei.utils;

import jei.StaticClass;

public final class HashCode extends StaticClass
{
	private HashCode() { }
	
	public static int of(Object object) {
		return object == null ? 0 : object.hashCode();
	}
	
	public static int ofAll(Object... objects) {
		if (objects == null) {
			return 0;
		} else {
			int result = 1;
			for (Object object : objects) {
				result = 31 * result + HashCode.of(object);
			}
			return result;
		}
	}
}
