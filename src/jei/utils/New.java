package jei.utils;

import jei.StaticBase;
import jei.functional.Supplier;

public class New extends StaticBase 
{
	private New() {}
	
	public static Object toString(Supplier<Object> output) {
		return new Object() {
			@Override
			public String toString() {
				Object outObject = output.invoke();
				return outObject == null ? "null" : outObject.toString();
			}
		};
	}
}
