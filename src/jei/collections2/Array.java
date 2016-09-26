package jei.collections2;

import jei.collections2.query.Query;
import jei.collections2.query.Queryable;
import jei.functional.Predicate;
import jei.simple.Option;

public interface Array<T> extends Collection<T>, Queryable<T>, Query<T>
{
	T get(int index);
	Array<T> getAll(int... indexes);
	Array<T> getRange(int indexA, int indexB);
	
	int amountOf(T entry);
	boolean has(T entry);
	Option<Integer> firstIndexOf(T entry);
	Option<Integer> firstIndexIf(Predicate<T> predicate);
	Option<Integer> lastIndexOf(T entry);
	Option<Integer> lastIndexIf(Predicate<T> predicate);
	
	
	
	
}
