package jei.utils;

import java.util.Arrays;

import jei.StaticBase;

public final class UArray extends StaticBase
{
	private UArray() {}
	
	public static <T> T[] copy(T[] array) {
		return Arrays.copyOf(array, array.length);
	}
 }
