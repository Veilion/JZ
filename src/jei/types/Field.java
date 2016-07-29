package jei.types;

import jei.Base;
import jei.error.NullArgumentException;

public class Field extends Base
{
	private final java.lang.reflect.Field
		field;
	
	public Field(java.lang.reflect.Field field) {
		this.field = field;
	}
	
	public Object get(Object object) {
		if(null == object) {
			throw new NullArgumentException();
		}
		try {
			return this.field.get(object);
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
