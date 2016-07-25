package jei.collections;

import java.util.HashSet;

import jei.functional.Predicate;

public interface Set<T> extends Stream<T>
{	
	@SafeVarargs
	public static <T> Set<T> of(T... values) {
		Set<T> set = new DefaultSet<>();
		set.addAll(values);
		new HashSet<>();
		return set;
	}
	
	boolean add(T value);
	void addAll(T[] values);
	void addAll(Iterable<T> values);
	
	boolean remove(T value);
	boolean removeWhere(Predicate<T> predicate);
	
	boolean has(T value);
	void clear();
	
	Array<T> toArray();
}
