package jz.collections;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

import jz.query.JQuery;
import jz.query.JQueryResult;
import jz.utils.UIterable;

public class TArray<T> extends AIndex<T> implements JArray<T> 
{
//#Variables: Instance
//{
	private Object[]
		array = null;
//}
//#Constructors
//{
	public TArray() {
		this.array = new Object[0];
	}
	public TArray(int size) {
		this.array = new Object[size];
	}
	@SafeVarargs
	public TArray(T... values) {
		this.array = Arrays.copyOf(values, values.length);
	}
	@SafeVarargs
	public TArray(int size, T... values) {
		this.array = new Object[size];
		for(int i = 0; i < size; ++i) {
			if(values.length <= i)
				break;
			else
				this.array[i] = values[i];
		}
	}
	public TArray(ICollection<T> coll) {
		this.array = new Object[coll.getSize()];
		int i = 0; for(T value : coll) {
			this.array[i++] = value;
		}
	}
	public TArray(Collection<T> coll) {
		this.array = new Object[coll.size()];
		int i = 0; for(T value : coll) {
			this.array[i++] = value;
		}
	}
	public TArray(Iterable<T> iterable) {
		this.array = new Object[UIterable.getSize(iterable)];
		int i = 0; for(T value : iterable) {
			this.array[i++] = value;
		}
	}
//}
//#Get
//{
	@Override
	public <O> JArray<O> get(JQuery<T, O> query) {
		JList<O> list = list();
		for(T current : this) {
			JQueryResult<O> result = query.check(current);
			if(result.isSuccess())
				list.add(result.getValue());
		}
		return list.toArray();
	}
	@Override
	public JArray<T> getRange(int index1, int index2) {
		this.checkIndex(index1); this.checkIndex(index2);
		int high, low;
		if(index1 > index2) {
			high = index1; low = index2;
		} else {
			high = index2; low = index1;
		}
		JArray<T> range = array(high - low);
		for(int i = low; i <= high; ++i) {
			range.set(i - low, this.unsafeGet(i));
		}
		return range;
	}
	@Override @SuppressWarnings("unchecked")
	protected T unsafeGet(int index) {
		return (T) this.array[index];
	}
//}
//#Set
//{
	@Override
	protected void unsafeSet(int index, T value) {
		this.array[index] = value;
	}
//}
//#Size
//{
	@Override
	public int getSize() {
		return this.array.length;
	}
	@Override
	public void clear() {
		for(int i = 0; i < this.getSize(); ++i)
			this.array[i] = 0;
	}
//}
//#Transform
//{
	@Override
	public JList<T> toList() {
		JList<T> list = list();
		list.add(this);
		return list;
	}
	@Override
	public T[] toNativeArray(Class<T> clazz) {
		@SuppressWarnings("unchecked") T[] narray = (T[]) Array.newInstance(clazz, this.getSize());
		for(int i = 0; i < this.getSize(); ++i)
			narray[i] = this.unsafeGet(i);
		return narray;
	}
//}
//#IFace: Copy
//{
	@Override
	public TArray<T> copy() {
		return new TArray<>(this);
	}
//}
}
