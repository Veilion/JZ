package jei.utils;

import java.util.Arrays;
import java.util.Iterator;

import jei.StaticBase;

public final class UArray extends StaticBase
{
	private UArray() {}
	
	public static <T> T[] copy(T[] array) {
		return Arrays.copyOf(array, array.length);
	}
	
	public static <T> boolean equals(T[] array1, T[] array2) {
		if(array1 == array2)  {
			return true;
		}
		if(array1 == null || array2 == null || array1.length != array1.length) {
			return false;
		}
		for(int i = 0; i < array1.length; ++i) {
			if(!equals(array1[i], array2[i])) {
				return false;
			}
		}
		return true;
	}
	
	public static <T> Iterator<T> iterator(T[] array) {
		return new Iterator<T>() {
			private int
				i = 0;
			@Override
			public boolean hasNext() {
				return this.i < array.length;
			}
			@Override
			public T next() {
				return array[i++];
			}
		};
	}
 }
