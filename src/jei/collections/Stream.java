package jei.collections;

import java.util.Iterator;

public interface Stream<T> extends CustomStream<T, Array<T>>
{	
	public static <T> Stream<T> of(Iterable<T> iterable) {
		if(iterable instanceof Countable) {
			final Countable that = (Countable) iterable;
			return new AbstractStream<T>() {
				@Override
				public Iterator<T> iterator() {
					return iterable.iterator();
				}
				@Override
				public int count() {
					return that.count();
				}
			};
		} else {
			return new AbstractStream<T>() {
				@Override
				public Iterator<T> iterator() {
					return iterable.iterator();
				}
				@Override
				public int count() {
					int size = 0;
					for(@SuppressWarnings("unused") T value : this) {
						++size;
					}
					return size;
				}
				
			};
		}
		
	}
	@SafeVarargs
	public static <T> Stream<T> of(T... values) {
		return of(java.util.stream.Stream.of(values)::iterator);
	}
}
