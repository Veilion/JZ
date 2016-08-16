package jei.collections;

import java.util.Arrays;

class DefaultArray<T> extends AbstractArray<T>
{	
	private Object[]
		array;
	
	private int
		count = 0;

	private final int
		initialSize;
	
	DefaultArray(int size) {
		if(size <= 0) {
			size = 1;
		}
		this.initialSize = size;
		this.array = new Object[size];
	}
	
	private T fastGet(int index) {
		@SuppressWarnings("unchecked")
		T value = (T) this.array[index];
		return value;
	}
	private void fastSet(int index, T value) {
		this.array[index] = value;
	}
	private void fastRemove(int index) {
		if(this.array.length < this.count + initialSize) {
			// create a new array, when the old one has to much unused space
			Object[] newArray = new Object[this.count + initialSize - 1];
			for(int i = 0, newI = 0; i < this.count; ++i)
				if(i != index)
					newArray[newI++] = this.array[i];
			--this.count;
		} else {
			--this.count;
			for(int i = index; i < this.count; ++i)	
			this.fastSet(i, this.fastGet(i + 1));
		}
	}
	
	@Override
	public T get(int index) {
		return this.fastGet(this.validate(index));
	}
	@Override
	public T set(int index, T value) {
		index = this.validate(index);
		T safe = this.fastGet(index);
		this.array[index] = value;
		return safe;
	}
	@Override
	public void add(T value) {
		if(this.count == this.array.length) {
			this.array = Arrays.copyOf(this.array, this.array.length * 2);
		}
		this.count++;
		validate(this.count - 1);
		this.array[this.count - 1] = value;
	}
	@Override
	public void add(int index, T entry) {
		index = this.validate(index);
		this.add(null);
		for(int i = index; i < this.count() - 1; ++i) {
			this.array[i + 1] = this.array[i];
		}
		this.array[index] = entry;
	}
	@Override
	public T remove(int index) {
		index = this.validate(index);
		T previous = this.fastGet(index);
		this.fastRemove(index);
		return previous;
	}
	@Override
	public int count() {
		return this.count;
	}
	@Override
	public void clear() {
		this.count = 0;
		this.array = new Object[this.initialSize];
	}
	
	@Override
	public FinalArray<T> readOnly() {
		return FinalArray.delegate(this);
	}

	@Override
	public T[] toNativeArray(Class<T> clazz) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, this.count());
		for(int i = 0; i < array.length; ++i) {
			array[i] = this.fastGet(i);
		}
		return array;
	}
}
