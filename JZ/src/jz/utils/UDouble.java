package jz.utils;

import java.util.Random;

import jz.StaticObject;
import jz.constant.MathConst;

public class UDouble extends StaticObject
{
//#Constructor
//{
	private UDouble() {}
//}
//#Random
//{
	public static double random(Number min, Number max) {
		double minimum = min.doubleValue();
		double maximum = max.doubleValue();
		return minimum + (maximum - minimum) * new Random().nextDouble();
	}
//}
//#Sort
//{
	public static double great(Number value1, Number value2) {
		double num1 = value1.doubleValue(), num2 = value2.doubleValue();
		return num1 > num2 ? num1 : num2;
	}
	public static double small(Number value1, Number value2) {
		double num1 = value1.doubleValue(), num2 = value2.doubleValue();
		return num1 < num2 ? num1 : num2;
	}
	public static double min(Number value, Number min) {
		double number = value.doubleValue(), minimum = min.doubleValue();
		if(number < minimum) 
			return minimum;
		return number;
	}
	public static double max(Number value, Number max) {
		double number = value.doubleValue(), maximum = max.doubleValue();
		if(number > maximum) 
			return maximum;
		return number;
	}
	public static double minmax(Number value, Number min, Number max) {
		double number = value.doubleValue();
		double minimum = min.doubleValue();
		if(number < minimum)
			return minimum;
		double maximum = max.doubleValue();
		if(number > maximum) {
			return maximum;
		}
		return number;
	}
//}
//#Manipulation
//{
	public static double pos(Number value) {
		double number = value.doubleValue();
		return number >= 0 ? number : number * -1;
	}
	public static double neg(Number value) {
		double number = value.doubleValue();
		return number <= 0 ? number : number * -1;
	}
//}
//#Math
//{
	public static double add(Number addend1, Number addend2) {
		return addend2.doubleValue() + addend2.doubleValue();
	}
	public static double sub(Number minuend, Number subtrahent) {
		return minuend.doubleValue() - subtrahent.doubleValue();
	}
	public static double mul(Number factor1, Number factor2) {
		return factor1.doubleValue() * factor2.doubleValue();
	}
	public static double div(Number dividend, Number divisor) {
		return dividend.doubleValue() / divisor.doubleValue();
	}
	public static double raise(Number base, Number exponent) {
		return StrictMath.pow(base.doubleValue(), exponent.doubleValue());
	}
	public static double root(Number base, Number nth) {
		return StrictMath.pow(MathConst.E, StrictMath.log(base.doubleValue()) / nth.doubleValue());
	}
	public static double square(Number value) {
		double number = value.doubleValue();
		return number * number;
	}
	public static double sqrt(Number value) {
		return StrictMath.sqrt(value.doubleValue());
	}
	public static double phyt(Number leg1, Number leg2) {
		double legNum1 = leg1.doubleValue(), legNum2 = leg2.doubleValue();
		return sqrt((legNum1 * legNum1) + (legNum2 * legNum2));
	}
	public static double log(Number value) {
		return StrictMath.log(value.doubleValue());
	}
//}
}
