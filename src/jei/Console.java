package jei;

import jei.annotation.NonNull;

public abstract class Console extends StaticBase
{
	private Console() {}
	
	public static void print(Object output) {
		System.out.println(stringify(output));
	}
	public static void print(String pattern, @NonNull Object... elements) {
		print(format(pattern, elements));
	}
}
