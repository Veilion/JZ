package jei.collections;

import jei.functional.Consumer;
import jei.functional.Predicate;
import jei.functional.Producer;
import jei.functional.Producer2;
import jei.json.Jsonifyable;

public interface Stream<T> extends Collection<T>, Jsonifyable
{	
	Array<T> getWhere(Predicate<T> predicate);
	
	boolean hasWhere(Predicate<T> predicate);
	
	int amountWhere(Predicate<T> predicate);

	void each(Consumer<T> consumer);
	Array<T> filter(Predicate<T> predicate);
	<R> Array<R> map(Producer<T, R> producer);
	<R> R reduce(final R start, Producer2<R, T, R> producer);
}
