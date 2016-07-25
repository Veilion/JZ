package jei;

import jei.collections.Array;
import jei.collections.Table;
import jei.collections.Tuple.Tuple2;
import jei.collections.Tuple.Tuple3;
import jei.error.NullArgumentException;
import jei.functional.Consumer;
import jei.functional.Supplier;
import jei.simple.Lazy;
import jei.simple.Option;
import jei.types.Type;
import jei.utils.UError;
import jei.utils.UString;

public abstract class $ extends StaticBase
{
	private $() {}
	
	public static <T> void each(T[] array, Consumer<T> consumer) {
		for(T value : array) {
			consumer.invoke(value);
		}
	}
	public static <T> void each(Iterable<T> iterable, Consumer<T> consumer) {
		for(T value : iterable) {
			consumer.invoke(value);
		}
	}
	public static <T> T create(Supplier<T> producer) {
		return producer.invoke();
	}
	public static <T> Lazy<T> lazy(Supplier<T> supplier) {
		return new Lazy<>(supplier);
	}
	
	@SafeVarargs
	public static <K, V> Table<K, V> table(Tuple2<K, V>... values) {
		return Table.of(values);
	}
	
	@SafeVarargs
	public static <T> Array<T> array(T... values) {
		return Array.of(values);
	}
	public static <T> Array<T> empty(int size) {
		return Array.withSize(size);
	}
	public static Array<Integer> range(int start, int end) {
		//TODO
		return null;
	}
	
	public static <A, B> Tuple2<A, B> __(A a, B b) {
		return new Tuple2<>(a, b);
	}
	public static <A, B, C> Tuple3<A, B, C> __(A a, B b, C c) {
		return new Tuple3<>(a, b, c);
	}
	
	public static boolean equals(Object object1, Object object2) {
		return (object1 == object2) || (object1 != null && object1.equals(object2));
	}
	public static int hash(Object object) {
		return object != null ? object.hashCode() : 0;
	}
	
	
	public static <T> T nonNull(T value) {
		if(value == null) {
			throw new NullArgumentException();
		}
		return value;
	}
	public static <T> Option<T> option(T value) {
		return Option.wrap(value);
	}
	public static <T> Option<T> some(T value) {
		return Option.some(value);
	}
	public static <T> Option<T> none() {
		return Option.none();
	}
	
	public static Byte wrap(byte primitive) {
		return Byte.valueOf(primitive);
	}
	public static Short wrap(short primitive) {
		return Short.valueOf(primitive);
	}
	public static Integer wrap(int primitive) {
		return Integer.valueOf(primitive);
	}
	public static Long wrap(long primitive) {
		return Long.valueOf(primitive);
	}
	public static Float wrap(float primitive) {
		return Float.valueOf(primitive);
	}
	public static Double wrap(double primitive) {
		return Double.valueOf(primitive);
	}
	public static Character wrap(char primitive) {
		return Character.valueOf(primitive);
	}
	public static Boolean wrap(boolean primitive) {
		return primitive ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public static byte unwrap(Byte wrapper) {
		return wrapper.byteValue();
	}
	public static short unwrap(Short wrapper) {
		return wrapper.shortValue();
	}
	public static int unwrap(Integer wrapper) {
		return wrapper.intValue();
	}
	public static long unwrap(Long wrapper) {
		return wrapper.longValue();
	}
	public static float unwrap(Float wrapper) {
		return wrapper.floatValue();
	}
	public static double unwrap(Double wrapper) {
		return wrapper.doubleValue();
	}
	public static char unwrap(Character wrapper) {
		return wrapper.charValue();
	}
	public static boolean unwrap(Boolean wrapper) {
		return wrapper.booleanValue();
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
	
	public static RuntimeException unchecked(Exception e) {
		return UError.unchecked(e);
	}
	
	public static <T> Class<? extends T> classof(T object) {
		@SuppressWarnings("unchecked")
		Class<? extends T> clazz = (Class<T>) object.getClass();
		return clazz;
	}
	public static <T> Type<? extends T> typeof(T object) {
		@SuppressWarnings("unchecked")
		Class<? extends T> clazz = (Class<? extends T>) object.getClass();
		return Type.forClass(clazz);
	}
	public static <T> Type<? extends T> typeby(Class<T> clazz) {
		return Type.forClass(clazz);
	}
}
