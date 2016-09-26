package jei.utils;

import jei.StaticBase;
import jei.functional.Predicate;
import jei.functional.Supplier;

public class New extends StaticBase 
{
	private New() {}
	
	public static Object toString(Supplier<Object> output) {
		return new Object() {
			@Override
			public String toString() {
				Object outObject = output.call();
				return outObject == null ? "null" : outObject.toString();
			}
		};
	}
	public static Object equals(Predicate<Object> output) {
		return new Object() {
			@Override
			public boolean equals(Object obj) {
				return output.equals(obj);
			}
		};
	}
	public static Object hashCode(Supplier<Integer> output) {
		return new Object() {
			@Override
			public int hashCode() {
				return output.call();
			}
		};
	}
}
