package jei.collections;

public interface Collection<T> extends Iterable<T>
{
	int count();
	default boolean isEmpty() {
		return this.count() == 0;
	}
}
