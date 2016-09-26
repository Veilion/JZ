package jei.collections;

import java.util.Iterator;

import jei.functional.Predicate;

class DefaultSet<T> extends AbstractSet<T>
{
	private static final Object
		DEFINED = new Object();
	
	private Table<T, Object>
		table = table();
	
	@Override
	public boolean add(T value) {
		return table.add(value, DEFINED);
	}
	@Override
	public boolean removeWhere(Predicate<T> predicate) {
		return this.table.removeWhere(entry -> predicate.call(entry.getKey()));
	}
	@Override
	public void clear() {
		this.table.clear();
	}
	@Override
	public int count() {
		return this.table.count();
	}
	@Override
	public Iterator<T> iterator() {
		final Iterator<Table.Entry<T, Object>> tableIterator = this.table.iterator();
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return tableIterator.hasNext();
			}
			@Override
			public T next() {
				return tableIterator.next().getKey();
			}
			
		};
	}

}
