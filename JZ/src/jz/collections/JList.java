package jz.collections;

import java.util.Collection;
import java.util.List;

import jz.query.JQuery;

public interface JList<T> extends JIndex<T>
{
//#Get
//{
	@Override
	public <O> JList<O> get(JQuery<T, O> query);
	@Override
	public JList<T> getRange(int index1, int index2);
//}
//#Add
//{
	public void add(T value);
	public void add(T[] values);
	public void add(ICollection<T> coll);
	public void add(Collection<T> coll);
	public void add(Iterable<T> iterable);
//}
//#Remove
//{
	public T remove(int index);
	public boolean remove(T value);
	public boolean remove(JQuery<T, ?> query);
//}
//#Transform
//{
	public JArray<T> toArray();
	public List<T> toNativeList();
//}
//#Size
//{
	public void setSize(int size);
//}
//#IFace: Copy
//{
	@Override
	public JList<T> copy();
//}
}
