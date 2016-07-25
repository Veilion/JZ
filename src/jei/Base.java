package jei;

import jei.collections.Array;
import jei.collections.Table;
import jei.collections.Tuple.Tuple2;
import jei.collections.Tuple.Tuple3;
import jei.functional.Consumer;
import jei.functional.Producer;
import jei.functional.Sequence;
import jei.functional.Supplier;
import jei.simple.Lazy;
import jei.simple.Option;
import jei.types.Type;

public class Base 
{
	protected static <T> T nonNull(T value) {
		return $.nonNull(value);
	}
	
	protected static <T> void each(T[] array, Consumer<T> consumer) {
		for(T value : array) {
			consumer.invoke(value);
		}
	}
	protected static <T> void each(Iterable<T> iterable, Consumer<T> consumer) {
		for(T value : iterable) {
			consumer.invoke(value);
		}
	}
	protected static <T> T create(Supplier<T> producer) {
		return producer.invoke();
	}
	protected static <T> void call(Consumer<T> consumer, T argument) {
		consumer.invoke(argument);
	}
	protected static <T> T call(Supplier<T> supplier) {
		return supplier.invoke();
	}
	protected static <I, O> O call(Producer<I, O> producer, I argument) {
		return producer.invoke(argument);
	}
	protected static void call(Sequence closure) {
		closure.invoke();
	}
	protected static <T> Lazy<T> lazy(Supplier<T> supplier) {
		return $.lazy(supplier);
	}
	
	@SafeVarargs
	protected static <K, V> Table<K, V> table(Tuple2<K, V>... values) {
		return Table.of(values);
	}
	
	protected static <A, B> Tuple2<A, B> __(A a, B b) {
		return $.__(a, b);
	}
	protected static <A, B, C> Tuple3<A, B, C> __(A a, B b, C c) {
		return $.__(a, b, c);
	}
	
	@SafeVarargs
	protected static <T> Array<T> array(T... values) {
		return $.array(values);
	}
	protected static <T> Array<T> empty(int size) {
		return $.empty(size);
	}
	protected static Array<Integer> range(int start, int end) {
		return $.range(start, end);
	}
	
	protected static boolean equals(Object object1, Object object2) {
		return $.equals(object1, object2);
	}
	protected static int hash(Object object) {
		return $.hash(object);
	}
	
	protected static <T> Option<T> wrap(T value) {
		return $.option(value);
	}
	protected static <T> Option<T> some(T value) {
		return $.some(value);
	}
	protected static <T> Option<T> none() {
		return $.none();
	}
	protected static String stringify(Object object) {
		return $.stringify(object);
	}
	protected static String format(String pattern, Object... elements) {
		return $.format(pattern, elements);
	}
	protected static void print(String pattern, Object... elements) {
		$.print(pattern, elements);
	}
	protected static void print(Object output) {
		$.print(output);
	}
	
	protected static Byte wrap(byte primitive) {
		return $.wrap(primitive);
	}
	protected static Short wrap(short primitive) {
		return $.wrap(primitive);
	}
	protected static Integer wrap(int primitive) {
		return $.wrap(primitive);
	}
	protected static Long wrap(long primitive) {
		return $.wrap(primitive);
	}
	protected static Float wrap(float primitive) {
		return $.wrap(primitive);
	}
	protected static Double wrap(double primitive) {
		return $.wrap(primitive);
	}
	protected static Character wrap(char primitive) {
		return $.wrap(primitive);
	}
	protected static Boolean wrap(boolean primitive) {
		return $.wrap(primitive);
	}
	
	protected static byte unwrap(Byte wrapper) {
		return $.unwrap(wrapper);
	}
	protected static short unwrap(Short wrapper) {
		return $.unwrap(wrapper);
	}
	protected static int unwrap(Integer wrapper) {
		return $.unwrap(wrapper);
	}
	protected static long unwrap(Long wrapper) {
		return $.unwrap(wrapper);
	}
	protected static float unwrap(Float wrapper) {
		return $.unwrap(wrapper);
	}
	protected static double unwrap(Double wrapper) {
		return $.unwrap(wrapper);
	}
	protected static char unwrap(Character wrapper) {
		return $.unwrap(wrapper);
	}
	protected static boolean unwrap(Boolean wrapper) {
		return $.unwrap(wrapper);
	}
	
	protected static RuntimeException unchecked(Exception e) {
		return $.unchecked(e);
	}
	
	protected static <T> Class<? extends T> classof(T object) {
		return $.classof(object);
	}
	protected static <T> Type<? extends T> typeof(T object) {
		return $.typeof(object);
	}
	protected static <T> Type<? extends T> typeby(Class<T> clazz) {
		return $.typeby(clazz);
	}
	
	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
