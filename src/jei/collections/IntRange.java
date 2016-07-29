package jei.collections;

public interface IntRange extends Range<Integer>
{
	static IntRange lazy(int first, int last) {
		return new LazyIntRange(first, last, 1);
	}
	static IntRange lazy(int first, int last, int jumpSize) {
		return new LazyIntRange(first, last, jumpSize);
	}
	static IntRange eager(int first, int last) {
		return new EagerIntRange(first, last, 1);
	}
	static IntRange eager(int first, int last, int jumpSize) {
		return new EagerIntRange(first, last, jumpSize);
	}
	
	@Override IntRange withJumps(Integer amount);
	@Override IntRange asReversed();
}
