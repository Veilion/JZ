package jz.utils;

import jz.StaticObject;

public class UIterable extends StaticObject
{
//#Constructor
//{
	private UIterable() {}
//}
//#Size
//{
	public static <T> int getSize(Iterable<T> iterable) {
		int size = 0; for(@SuppressWarnings("unused") T value : iterable) {
			++size;
		} return size;
	}
//}
}
