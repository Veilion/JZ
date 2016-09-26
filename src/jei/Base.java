package jei;

import jei.functional.Supplier;
import jei.simple.Computed;
import jei.simple.Lazy;
import jei.simple.Option;
import jei.types.Type;

public class Base 
{
	protected Base() {}
	
	public static <T> Computed<T> compute(Supplier<T> supplier) {
		return Jei.compute(supplier);
	}
	public static <T> Lazy<T> lazy(Supplier<T> supplier) {
		return Jei.lazy(supplier);
	}
	
	public static <T> T create(Supplier<T> supplier) {
		return Jei.create(supplier);
	}
	
	public static boolean equals(Object object1, Object object2) {
		return Jei.equals(object1, object2);
	}
	
	public static int hash(Object object) {
		return Jei.hash(object);
	}
	
	public static int hashAll(Object... objects) {
		return Jei.hashAll(objects);
	}
	
	public static <T> T nonNull(T value) {
		return Jei.nonNull(value);
	}
	
	public static <T> Option<T> maybe(T value) {
		return Jei.maybe(value);
	}
	
	public static <T> Option<T> some(T value) {
		return Jei.some(value);
	}
	
	public static <T> Option<T> none() {
		return Jei.none();
	}
	
	public static String stringify(Object object) {
		return Jei.stringify(object);
	}
	
	public static String format(String pattern, Object... elements) {
		return Jei.format(pattern, elements);
	}
	
	public static void print(String pattern, Object... elements) {
		Jei.print(pattern, elements);
	}
	
	public static void print(Object output) {
		Jei.print(output);
	}
	
	public static RuntimeException unchecked(Throwable e) {
		return Jei.unchecked(e);
	}
	
	public static <T> Type<T> typeBy(Class<T> clazz) {
		return Jei.typeBy(clazz);
	}
	public static <T> Type<T> typeOf(T object) {
		return Jei.typeOf(object);
	}
	public static <T> Class<T> classOf(T object) {
		return Jei.classOf(object);
	}
	
	public static Byte box(byte primitive) {
		return Jei.box(primitive);
	}
	public static Short box(short primitive) {
		return Jei.box(primitive);
	}
	public static Integer box(int primitive) {
		return Jei.box(primitive);
	}
	public static Long box(long primitive) {
		return Jei.box(primitive);
	}
	public static Float box(float primitive) {
		return Jei.box(primitive);
	}
	public static Double box(double primitive) {
		return Jei.box(primitive);
	}
	public static Character box(char primitive) {
		return Jei.box(primitive);
	}
	public static Boolean box(boolean primitive) {
		return Jei.box(primitive);
	}
	
	public static byte unbox(Byte wrapper) {
		return Jei.unbox(wrapper);
	}
	public static short unbox(Short wrapper) {
		return Jei.unbox(wrapper);
	}
	public static int unbox(Integer wrapper) {
		return Jei.unbox(wrapper);
	}
	public static long unbox(Long wrapper) {
		return Jei.unbox(wrapper);
	}
	public static float unbox(Float wrapper) {
		return Jei.unbox(wrapper);
	}
	public static double unbox(Double wrapper) {
		return Jei.unbox(wrapper);
	}
	public static char unbox(Character wrapper) {
		return Jei.unbox(wrapper);
	}
	public static boolean unbox(Boolean wrapper) {
		return Jei.unbox(wrapper);
	}
	
	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
