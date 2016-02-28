package jz.collections;

import java.util.Iterator;

import jz.query.IQueryable;
import jz.query.JQuery;

public interface JIndex<T> extends ICollection<T>, IQueryable<T>
{
//#Get
//{
	public T get(int index);
	public <O> JIndex<O> get(JQuery<T, O> query);
	public JIndex<T> getRange(int index1, int index2);
//}
//#Set
//{
	public T set(int index, T value);
	public int set(JQuery<T, ? extends T> query);
//}
//#Contains
//{
	public boolean contains(T value);
	public boolean contains(JQuery<T, ?> query);
//}
//#Amount
//{
	public int amountOf(T value);
	public int amountOf(JQuery<T, ?> query);
//}
//#Index
//{
	public int firstIndex(T value);
	public int firstIndex(JQuery<T, ?> query);
	public int lastIndex(T value);
	public int lastIndex(JQuery<T, ?> query);
//}
//#IFace
//{
	@Override
	public JIndex<T> copy();
	@Override
	public default Iterator<T> iterator() {
		JIndex<T> that = this;
		return new Iterator<T>() {
			int next = 0;
			@Override
			public boolean hasNext() {
				return that.getSize() > next;
			}
			@Override
			public T next() {
				return that.get(this.next++);
			}
		};
	}
//}
}
