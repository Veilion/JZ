package jei.collections;

import java.util.Iterator;

import jei.data.json.JSON;
import jei.error.UndefinedEntryException;
import jei.functional.Predicate;
import jei.simple.Option;

public abstract class AbstractArray<T> extends AbstractStream<T> implements Array<T>
{
	protected int validate(int index) {
		int count = count();
		int useIndex = index;
		if(index < 0) {
			useIndex = this.count() + index;
		}  
		if(useIndex < 0 || useIndex > count - 1) {
			throw new UndefinedEntryException(useIndex, count * -1, count - 1);
		}
		return useIndex;
	}
	
	@Override
	public Iterator<T> iterator() {
		AbstractArray<T> that = this;
		return new Iterator<T>() {
			private int
				index = 0;
			@Override
			public boolean hasNext() {
				return this.index < that.count();
			}
			@Override
			public T next() {
				return that.get(this.index++);
			}
		};
	}
	@Override
	public Array<T> getAll(int... indices) {
		Array<T> results = Array.withSize(indices.length);
		for(int i = 0; i < indices.length; ++i) {
			results.add(this.get(this.validate(indices[i])));
		}
		return results;
	}
	@Override
	public Array<T> getRange(int start, int end) {
		start = validate(start);
		end = validate(end);
		int max = start > end ? start : end;
		int min = end == max ? start : end;
		Array<T> values = Array.withSize(max - min);
		for(int i = min; i <= max; ++i) {
			values.add(this.get(i));
		}
		return values;
	}
	@Override
	public void addAll(T[] values) {
		for(int i = 0; i < values.length; ++i) {
			this.add(values[i]);
		}
	}
	@Override
	public void addAll(Iterable<T> values) {
		for(T value : values) {
			this.add(value);
		}
	}
	
	@Override
	public boolean set(T current, T replacement) {
		boolean found = true;
		for(int i = 0; i < this.count(); ++i) {
			if(equals(this.get(i), current)) {
				this.set(i, replacement);
			}
		}
		return found;
	}
	@Override
	public boolean setWhere(T value, Predicate<T> predicate) {
		boolean found = false;
		for(int i = 0; i < this.count(); ++i) {
			if(predicate.call(this.get(i))) {
				this.set(i, value);
				found = true;
			}
		}
		return found;
	}
	@Override
	public boolean setFirst(T occurence, T replacement) {
		boolean found = false;
		for(int i = 0; i < this.count(); ++i) {
			if(equals(this.get(i), occurence)) {
				this.set(i, replacement);
				found = true;
			}
		}
		return found;
	}
	@Override
	public boolean setLast(T occurence, T replacement) {
		boolean found = false;
		for(int i = this.count() - 1; i >= 0; --i) {
			if(equals(this.get(i), occurence)) {
				this.set(i, replacement);
				found = true;
			}
		}
		return found;
	}
	
	@Override
	public boolean remove(T value) {
		boolean found = false;
		for(int i = this.count() - 1; i >= 0; --i) {
			if(equals(this.get(i), value)) {
				this.remove(i);
				found = true;
			}
		}
		return found;
	}
	@Override
	public boolean removeWhere(Predicate<T> predicate) {
		boolean found = false;
		for(int i = this.count() - 1; i >= 0; --i) {
			if(predicate.call(this.get(i))) {
				this.remove(i);
			}
		}
		return found;
	}
	@Override
	public Option<Integer> firstIndexOf(T value) {
		for(int i = 0; i < this.count(); ++i) {
			if(equals(this.get(i), value)) {
				return some(i);
			}
		}
		return none();
	}
	@Override
	public Option<Integer> firstIndexWhere(Predicate<T> predicate) {
		for(int i = 0; i < this.count(); ++i) {
			if(predicate.call(this.get(i))) {
				return some(i);
			}
		}
		return none();
	}
	@Override
	public Option<Integer> lastIndexOf(T value) {
		for(int i = this.count() - 1; i >= 0; --i) {
			if(equals(this.get(i), value)) {
				return some(i);
			}
		}
		return none();
	}
	@Override
	public Option<Integer> lastIndexWhere(Predicate<T> predicate) {
		for(int i = this.count() - 1; i >= 0; --i) {
			if(predicate.call(this.get(i))) {
				return some(i);
			}
		}
		return none();
	}

	@Override
	public Option<T> firstWhere(Predicate<T> predicate) {
		for(int i = 0; i < this.count(); ++i) {
			T value = this.get(i);
			if(predicate.call(value)) {
				return some(value);
			}
		}
		return none();
	}
	@Override
	public Option<T> lastWhere(Predicate<T> predicate) {
		for(int i = this.count() - 1; i >= 0; --i) {
			T value = this.get(i);
			if(predicate.call(value)) {
				return some(value);
			}
		}
		return none();
	}
	
	@Override
	public int amountOf(T entry) {
		int amount = 0;
		for(T value : this) {
			if(equals(value, entry)) {
				++amount;
			}
		}
		return amount;
	}
	@Override
	public boolean has(T entry) {
		for(T value : this) {
			if(equals(value, entry)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public T first() {
		if(this.isEmpty()) {
			throw new UndefinedEntryException("cannot get the first element of an empty array");
		}
		return this.get(0);
	}
	@Override
	public T last() {
		if(this.isEmpty()) {
			throw new UndefinedEntryException("cannot get the last element of an empty array");
		}
		return this.get(-1);
	}
	
	@Override
	public int hashCode() {
		int hash = 1;
        for (T element : this) {
            hash = 31 * hash + hash(element);	
        }
        return hash;
	}
	@Override
	public boolean equals(Object object) {
		if(object instanceof FinalArray) {
			FinalArray<?> array = (FinalArray<?>) object;
			if(array.count() == this.count()) {
				for(int i = 0; i < array.count(); ++i) {
					if(!equals(array.get(i), this.get(i))) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		int count = this.count();
		StringBuilder builder = new StringBuilder(format("Array({0}) {", count));
		int numLength = (count + "").length();
		for(int i = 0; i < count; ++i) {
			builder.append(String.format("\n    %" + numLength + "s: ", i)).append(stringify(this.get(i)));
		}
		return builder.append(count > 0 ? "\n}" : "}").toString();
	}
	@Override
	public String jsonify() {
		StringBuilder builder = new StringBuilder("[");
		int count = this.count();
		for(int i = 0; i < count; ++i) {
			if(i != 0) {
				builder.append(", ");
			}
			builder.append(JSON.parse(this.get(i)));
		}
		return builder.append(']').toString();
	}
}
