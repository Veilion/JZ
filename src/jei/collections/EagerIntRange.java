package jei.collections;

import java.util.Iterator;

import jei.error.ArgumentException;

class EagerIntRange extends AbstractRange<Integer> implements IntRange
{
	private final int[]
		range;
		
	private final int
		last,
		jumpSize;
	
	public EagerIntRange(int first, int last, int jumpSize) {
		super(false);
		if(jumpSize <= 0) {
			throw new ArgumentException("the jump-size of a range has to be positive");
		}

		if(first < last) {
			this.range = new int[(last - first) / jumpSize + 1];
			for(int i = first, arrI = 0; i < last; i += jumpSize, ++arrI) {
				this.range[arrI] = i; 
			}
		} else {
			this.range = new int[(first - last) / jumpSize + 1];
			for(int i = first, arrI = 0; i > last; i -= jumpSize, ++arrI) {
				this.range[arrI] = i; 
			}
		}
		this.last = last;
		this.jumpSize = jumpSize;
	}
	
	@Override
	public Integer first() {
		return this.range[0];
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
	public boolean contains(Integer value) {
		if(value >= this.first() && value <= this.last) {
			for(int i = 0; i < this.range.length; ++i) {
				if(this.range[i] == value) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int count() {
		return this.range.length;
	}

	@Override
	public Iterable<Integer> reversed() {
		final EagerIntRange that = this;
		return () -> new Iterator<Integer>() {
			private int
				i = that.range.length - 1;
			@Override
			public boolean hasNext() {
				return this.i >= 0;
			}
			@Override
			public Integer next() {
				return that.range[this.i--];
			}
		};
	}

	@Override
	public Array<Integer> toArray() {
		return Array.from(this);
	}

	@Override
	public Iterator<Integer> iterator() {
		final EagerIntRange that = this;
		return new Iterator<Integer>() {
			private int
				i = 0;
			@Override
			public boolean hasNext() {
				return this.i < that.range.length;
			}
			@Override
			public Integer next() {
				return that.range[this.i++];
			}
		};
	}

	@Override
	public EagerIntRange withJumps(Integer amount) {
		return new EagerIntRange(this.first(), this.last, amount);
	}

	@Override
	public EagerIntRange asReversed() {
		return new EagerIntRange(this.last, this.first(), this.jumpSize);
	}

	@Override
	public int hashCode() {
		return this.first() ^ this.last ^ this.jumpSize;
	}

}
