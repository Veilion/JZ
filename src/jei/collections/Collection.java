package jei.collections;

public interface Collection<T> extends Iterable<T>, Countable
{
	default boolean isEmpty() {
		return this.count() == 0;
	}
}
