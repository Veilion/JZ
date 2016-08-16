package jei.collections;

import jei.data.json.Jsonifyable;
import jei.functional.Consumer;
import jei.functional.Predicate;
import jei.functional.Producer;
import jei.functional.Producer2;

public interface CustomStream<T, R> extends Collection<T>, Jsonifyable
{
	boolean includes(Predicate<T> predicate);
	boolean matches(Predicate<T> predicate);
	
	int amountWhere(Predicate<T> predicate);

	void each(Consumer<T> consumer);
	R filter(Predicate<T> predicate);
	<U> Array<U> map(Producer<T, U> producer);
	<U> U reduce(final U start, Producer2<U, T, U> producer);
}
