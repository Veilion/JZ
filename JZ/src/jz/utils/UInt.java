package jz.utils;

import jz.StaticObject;
import jz.tool.JClosure;

public class UInt extends StaticObject
{
	private UInt() {}
	
//#Sort
//{
	public static int bigger(int value1, int value2) {
		return value1 > value2 ? value1 : value2;
	}
	public static int smaller(int value1, int value2) {
		return value1 > value2 ? value2 : value1;
	}
//}
	
//#Transform
//{
	public static double toDecimal(int integer) {
		int divisor = 1;
		for(int i = 0; i < string(integer).length(); ++i) {
			divisor *= 10;
		}
		return (double)integer / divisor;
	}
//}
	
//#Math
//{
	public static int add(Number... values) {
		double value = 0;
		for(Number current : values) {
			value += current.doubleValue();
		}
		return (int) value;
	}
//}
}
