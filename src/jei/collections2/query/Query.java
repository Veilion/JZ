package jei.collections2.query;

import java.util.stream.Stream;

import jei.collections2.Bag;
import jei.collections2.Countable;
import jei.functional.Consumer;
import jei.functional.Consumer2;
import jei.functional.Predicate;
import jei.functional.Producer;
import jei.functional.Producer2;

public interface Query<T> extends Countable
{
	Stream<T> foo();
	
	int count(Predicate<T> predicate);

	void each(Consumer<T> consumer);
	
	boolean contains(Predicate<T> predicate);
	
	boolean matches(Predicate<T> predicate);
	
	Query<T> unique();
	
	Query<T> filter(Predicate<T> predicate);
	
	<O> Query<O> map(Producer<T, O> producer);
	
	<O> O reduce(O start, Producer2<O, T, O> producer);
	
	<O> Query<O> div2(Producer2<T, T, O> producer);
	
	<O> Query<O> div3(Producer3<T, T, T, O> producer);
	
	<O> Query<O> split(Producer<T, O[]> producer);
	
	<O extends Bag<T>> O collect(O collection);
	
	<O> O convert(O target, Consumer2<O, T> consumer);
	
	T[] toArray(Class<T> clazz);
	
	String join(Object separator);
	
}
