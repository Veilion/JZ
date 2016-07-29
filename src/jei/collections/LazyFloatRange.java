package jei.collections;

import java.util.Iterator;

class LazyFloatRange extends AbstractRange<Float> implements FloatRange
{
	private static Iterator<Float> createIterator(float first, float last, float jumpSize) {
		if(first > last) {
			return new Iterator<Float>() {
				private float i = first; 
				@Override
				public boolean hasNext() {
					return this.i >= last;
				}
				@Override
				public Float next() {
					Float retVal = this.i;
					this.i -= jumpSize;
					return retVal;
				}
			};
		} else {
			return new Iterator<Float>() {
				private float i = first; 
				@Override
				public boolean hasNext() {
					return this.i <= last;
				}
				@Override
				public Float next() {
					Float retVal = this.i;
					this.i += jumpSize;
					return retVal;
				}
			};
		}
	}
	
	private final float
		first,
		last,
		jumpSize;
	
	private int
		hashCode = 0;
	
	public LazyFloatRange(float first, float last, float jumpSize) {
		super(true);
		this.first = first;
		this.last = last;
		this.jumpSize = jumpSize;
	}

	@Override
	public Float first() {
		return this.first;
	}

	@Override
	public Float last() {
		return this.last;
	}
	@Override
	public Float getJumpSize() {
		return this.jumpSize;
	}
	@Override
	public int count() {
		return (int)((this.first > this.last ? this.first - this.last : this.last - this.first) / this.jumpSize) + 1;
	}
	@Override
	public boolean contains(Float value) {
		if(value >= this.first && value <= this.last) {
			for(float i : this) {
				if(i == value) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public Iterator<Float> iterator() {
		return createIterator(this.first, this.last, this.jumpSize);
	}
	@Override
	public Iterable<Float> reversed() {
		return () -> createIterator(this.last, this.first, this.jumpSize);
	}
	
	@Override
	public Array<Float> toArray() {
		Array<Float> array = Array.withSize(this.count());
		array.addAll(this);
		return array;
	}
	@Override
	public LazyFloatRange asReversed() {
		return new LazyFloatRange(this.last, this.first, this.jumpSize);
	}
	@Override
	public LazyFloatRange withJumps(Float amount) {
		return new LazyFloatRange(this.first, this.last, amount);
	}
	
	@Override
	public int hashCode() {
		if(this.hashCode == 0) {
			this.hashCode = Float.floatToIntBits(this.first) ^ Float.floatToIntBits(this.last) ^ Float.floatToIntBits(this.jumpSize);
		}
		return this.hashCode;
	}
}
