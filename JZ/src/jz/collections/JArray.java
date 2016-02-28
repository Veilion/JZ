package jz.collections;

import jz.query.JQuery;

public interface JArray<T> extends JIndex<T>
{
//#Get
//{
	@Override
	public <O> JArray<O> get(JQuery<T, O> query);
	@Override
	public JArray<T> getRange(int index1, int index2);
//}
//#Transform
//{
	public JList<T> toList();
	public T[] toNativeArray(Class<T> clazz);
//}
//#IFace: Copy
//{
	@Override
	public JArray<T> copy();
//}
}
