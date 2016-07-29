package jei.collections;

import jei.functional.Predicate;
import jei.simple.Option;

public interface Array<T> extends Stream<T>
{
	public static <T> Array<T> withSize(int size) {
		return new DefaultArray<>(size);
	}
	@SafeVarargs
	public static <T> Array<T> of(T... values) {
		Array<T> array = new DefaultArray<>(values.length);
		array.addAll(values);
		return array;
	} 
	public static <T> Array<T> empty() {
		return new DefaultArray<>(10);
	}
	public static <T> Array<T> from(Iterable<T> iterable) {
		Array<T> array = new DefaultArray<>(0);
		array.addAll(iterable);
		return array;
	}
	
	T get(int index);
	Array<T> getAll(int... indices);
	Array<T> getRange(int start, int end);
	
	void add(T entry);
	void addAll(T[] entry);
	void addAll(Iterable<T> entries);
	
	T set(int index, T entry);
	boolean set(T occurence, T replacement);
	boolean setWhere(T entry, Predicate<T> predicate);
	boolean setFirst(T occurence, T replacement);
	boolean setLast(T occurence, T replacement);
	
	T remove(int index);
	boolean remove(T entry);
	boolean removeWhere(Predicate<T> predicate);
	
	int amountOf(T entry);
	boolean has(T entry);

	Option<Integer> firstIndexOf(T entry);
	Option<Integer> firstIndexWhere(Predicate<T> predicate);
	Option<Integer> lastIndexOf(T entry);
	Option<Integer> lastIndexWhere(Predicate<T> predicate);
	
	Option<T> firstWhere(Predicate<T> predicate);
	Option<T> lastWhere(Predicate<T> predicate);
	
	T first();
	T last();
	
	void clear();
}
