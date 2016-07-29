package jei.collections;

public interface Range<T extends Number> extends Iterable<T>
{
	T first();
	T last();
	T getJumpSize();
	boolean contains(T value);
	int count();
	
	Range<T> withJumps(T amount);
	Range<T> asReversed();
	
	Iterable<T> reversed();
	
	Array<T> toArray();
}
