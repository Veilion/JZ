package jei.collections;

import java.util.Iterator;

import jei.error.ArgumentException;

class LazyIntRange extends AbstractRange<Integer> implements IntRange
{
	private static Iterator<Integer> createIterator(int first, int last, int jumpSize) {
		if(first > last) {
			return new Iterator<Integer>() {
				private int i = first; 
				@Override
				public boolean hasNext() {
					return this.i >= last;
				}
				@Override
				public Integer next() {
					Integer retVal = this.i;
					this.i -= jumpSize;
					return retVal;
				}
			};
		} else {
			return new Iterator<Integer>() {
				private int i = first; 
				@Override
				public boolean hasNext() {
					return this.i <= last;
				}
				@Override
				public Integer next() {
					Integer retVal = this.i;
					this.i += jumpSize;
					return retVal;
				}
			};
		}
	}
	
	private final int
		first,
		last,
		jumpSize;
	
	public LazyIntRange(int first, int last, int jumpSize) {
		super(true);
		if(jumpSize <= 0) {
			throw new ArgumentException("the jump-size of a range has to be positive");
		}
		
		this.first = first;
		this.last = last;
		this.jumpSize = jumpSize;
	}
	
	@Override
	public Integer first() {
		return this.first;
	}
	@Override
	public Integer last() {
		return this.last;
	}
	@Override
	public Integer getJumpSize() {
		return this.jumpSize;
	}
	@Override
	public int count() {
		return ((this.first > this.last ? this.first - this.last : this.last - this.first) / this.jumpSize) + 1;
	}
	@Override
	public boolean contains(Integer value) {
		if(value >= this.first && value <= this.last) {
			for(int i : this) {
				if(i == value) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public Iterator<Integer> iterator() {
		return createIterator(this.first, this.last, this.jumpSize);
	}
	@Override
	public Iterable<Integer> reversed() {
		return () -> createIterator(this.last, this.first, this.jumpSize);
	}
	
	@Override
	public Array<Integer> toArray() {
		Array<Integer> array = Array.withSize(this.count());
		array.addAll(this);
		return array;
	}
	@Override
	public LazyIntRange asReversed() {
		return new LazyIntRange(this.last, this.first, this.jumpSize);
	}
	@Override
	public LazyIntRange withJumps(Integer amount) {
		return new LazyIntRange(this.first, this.last, amount);
	}
	
	@Override
	public int hashCode() {
		return this.first ^ this.last ^ this.jumpSize;
	}
}
