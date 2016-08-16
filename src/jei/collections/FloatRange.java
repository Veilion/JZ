package jei.collections;

public interface FloatRange extends Range<Float>
{
	static FloatRange lazy(int first, int last) {
		return new LazyFloatRange(first, last, 1);
	}
	static FloatRange lazy(int first, int last, int jumpSize) {
		return new LazyFloatRange(first, last, jumpSize);
	}
//	static FloatRange eager(int first, int last) {
//		return new EagerIntRange(first, last, 1);
//	}
//	static FloatRange eager(int first, int last, int jumpSize) {
//		return new EagerIntRange(first, last, jumpSize);
//	}
	
	@Override FloatRange withJumps(Float amount);
	@Override FloatRange asReversed();
}
