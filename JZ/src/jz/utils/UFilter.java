package jz.utils;

import jz.StaticObject;

public class UFilter extends StaticObject
{
//#Constructor
//{
	private UFilter() {}
//}
//#Compare
//{
	public static <T> boolean equalNN(T left, T right) {
		return null != left && notNull(right).equals(left);
	}
//}
}
