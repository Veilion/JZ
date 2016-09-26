package jei;

import jei.error.NullArgumentException;
import jei.functional.Supplier;
import jei.simple.Computed;
import jei.simple.Lazy;
import jei.simple.Option;
import jei.types.Type;
import jei.utils.UError;
import jei.utils.UString;

public final class Jei
{
	private Jei() {}
	
	public static <T> Computed<T> compute(Supplier<T> supplier) {
		return Computed.of(supplier);
	}
	public static <T> Lazy<T> lazy(Supplier<T> supplier) {
		return Lazy.of(supplier);
	}
	
	public static <T> T create(Supplier<T> supplier) {
		return supplier.call();
	}
	
	public static boolean equals(Object object1, Object object2) {
		return (object1 == object2) || (object1 != null && object1.equals(object2));
	}
	
	public static int hash(Object object) {
		return object != null ? object.hashCode() : 0;
	}
	
	public static int hashAll(Object... objects) {
		int hash = 0;
		for (Object object : objects) {
			hash = hash ^ hash(object);
		}
		return hash;
	}
	
	public static <T> T nonNull(T value) {
		if(value == null) {
			throw new NullArgumentException();
		}
		return value;
	}
	
	public static <T> Option<T> maybe(T value) {
		return Option.wrap(value);
	}
	
	public static <T> Option<T> some(T value) {
		return Option.some(value);
	}
	
	public static <T> Option<T> none() {
		return Option.none();
	}
	
	public static String stringify(Object object) {
		return UString.stringify(object);
	}
	
	public static String format(String pattern, Object... elements) {
		return UString.format(pattern, elements);
	}
	
	public static void print(String pattern, Object... elements) {
		Console.print(pattern, elements);
	}
	
	public static void print(Object output) {
		Console.print(output);
	}
	
	public static RuntimeException unchecked(Throwable e) {
		return UError.unchecked(e);
	}
	
	public static <T> Type<T> typeBy(Class<T> clazz) {
		return Type.forClass(clazz);
	}
	public static <T> Type<T> typeOf(T object) {
		return typeBy(classOf(object));
	}
	public static <T> Class<T> classOf(T object) {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) object.getClass();
		return clazz;
	}
	
	public static Byte box(byte primitive) {
		return Byte.valueOf(primitive);
	}
	public static Short box(short primitive) {
		return Short.valueOf(primitive);
	}
	public static Integer box(int primitive) {
		return Integer.valueOf(primitive);
	}
	public static Long box(long primitive) {
		return Long.valueOf(primitive);
	}
	public static Float box(float primitive) {
		return Float.valueOf(primitive);
	}
	public static Double box(double primitive) {
		return Double.valueOf(primitive);
	}
	public static Character box(char primitive) {
		return Character.valueOf(primitive);
	}
	public static Boolean box(boolean primitive) {
		return primitive ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public static byte unbox(Byte wrapper) {
		return wrapper.byteValue();
	}
	public static short unbox(Short wrapper) {
		return wrapper.shortValue();
	}
	public static int unbox(Integer wrapper) {
		return wrapper.intValue();
	}
	public static long unbox(Long wrapper) {
		return wrapper.longValue();
	}
	public static float unbox(Float wrapper) {
		return wrapper.floatValue();
	}
	public static double unbox(Double wrapper) {
		return wrapper.doubleValue();
	}
	public static char unbox(Character wrapper) {
		return wrapper.charValue();
	}
	public static boolean unbox(Boolean wrapper) {
		return wrapper.booleanValue();
	}
}
