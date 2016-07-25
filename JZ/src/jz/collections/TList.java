package jz.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import jz.query.JQuery;
import jz.query.JQueryResult;
import jz.utils.UFilter;
import jz.utils.UInt;
import jz.utils.UIterable;

public class TList<T> extends AIndex<T> implements JList<T> 
{
//#Variables: Instance
//{
	private Object[]
		array = null;
//}
//#Constructor
//{
	public TList() {
		this.array = new Object[0];
	}
	@SafeVarargs
	public TList(T... values) {
		this.array = Arrays.copyOf(values, values.length);
	}
	public TList(ICollection<T> coll) {
		this.array = new Object[coll.getSize()];
		int i = 0; for(T value : coll) {
			this.array[i++] = value;
		}
	}
	public TList(Collection<T> coll) {
		this.array = new Object[coll.size()];
		int i = 0; for(T value : coll) {
			this.array[i++] = value;
		}
	}
	public TList(Iterable<T> iterable) {
		this.array = new Object[UIterable.getSize(iterable)];
		int i = 0; for(T value : iterable) {
			this.array[i++] = value;
		}
	}
//}
//#Get
//{
	@Override
	public <O> JList<O> get(JQuery<T, O> query) {
		JList<O> list = list();
		for(T current : this) {
			JQueryResult<O> result = query.check(current);
			if(result.isSuccess())
				list.add(result.getValue());
		}
		return list;
	}
	@Override
	public JList<T> getRange(int index1, int index2) {
		this.checkIndex(index1); this.checkIndex(index2);
		int high, low;
		if(index1 > index2) {
			high = index1; low = index2;
		} else {
			high = index2; low = index1;
		}
		JList<T> range = list();
		for(int i = low; i <= high; ++i) {
			range.add(this.unsafeGet(i));
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
//#Add
//{
	@Override
	public void add(T value) {
		this.incSizeBy(1);
		this.unsafeSet(this.getSize() - 1, value);
	}
	@Override
	public void add(T[] values) {
		int befSize = this.getSize();
		this.incSizeBy(values.length);
		for(int i = 0; i < values.length; ++i)
			this.unsafeSet(befSize + i, values[i]);
	}
	@Override
	public void add(ICollection<T> coll) {
		int befSize = this.getSize();
		this.incSizeBy(coll.getSize());
		int i = 0; for(T current : coll)
			this.unsafeSet(befSize + i++, current);
	}
	@Override
	public void add(Collection<T> coll) {
		int befSize = this.getSize();
		this.incSizeBy(coll.size());
		int i = 0; for(T current : coll)
			this.unsafeSet(befSize + i++, current);
	}
	@Override
	public void add(Iterable<T> iterable) {
		int befSize = this.getSize();
		this.incSizeBy(UIterable.getSize(iterable));
		int i = 0; for(T current : iterable)
			this.unsafeSet(befSize + i++, current);
	}
//}
//#Remove
//{
	@Override
	public T remove(int index) {
		T value = this.safeGet(index);
		Object[] newArray = new Object[this.getSize() - 1];
		for(int i = 0, ni = 0; i < this.getSize(); ++i)
			if(i != index)
				newArray[ni++] = this.array[i];
		return value;
	}
	@Override
	public boolean remove(T value) {
		int amount = 0;
		if(null == value) {
			for(T current : this) if(null == current) ++amount;
			Object[] newArray = new Object[this.getSize() - amount];
			for(int i = 0, ni = 0; i < this.getSize(); ++i)
				if(null != this.array[i])
					newArray[ni++] = this.array[i];
			this.array = newArray;
		} else {
			for(T current : this) if(UFilter.equalNN(current, value)) ++amount;
			Object[] newArray = new Object[this.getSize() - amount];
			for(int i = 0, ni = 0; i < this.getSize(); ++i) {
				if(null == this.array[i] || !value.equals(this.array[i])) {
					newArray[ni++] = this.array[i];
				}
			}
			this.array = newArray;
		}
		return amount > 0;
	}
	@Override
	public boolean remove(JQuery<T, ?> query) {
		JList<T> found = list();
		for(T current : this) {
			if(query.check(current).isSuccess()) {
				found.add(current);
			}
		}
		Object[] newArray = new Object[this.getSize() - found.getSize()];
		for(int i = 0, ni = 0; i < this.getSize(); ++i) {
			if(found.contains(this.unsafeGet(i))) {
				newArray[ni++] = this.array[i];
			}
		}
		this.array = newArray;
		return !found.isEmpty();
	}
//}
//#Size
//{
	@Override
	public int getSize() {
		return this.array.length;
	}
	@Override
	public void setSize(int size) {
		Object[] newArray = new Object[size];
		int ll = UInt.smaller(size, this.array.length);
		for(int i = 0; i < ll; ++i)
			newArray[i] = this.array[i];
		this.array = newArray;
	}
	@Override
	public void clear() {
		for(int i = 0; i < this.getSize(); ++i)
			this.array[i] = 0;
	}
	private void incSizeBy(int amount) {
		Object[] newArray = new Object[this.getSize() + amount];
		for(int i = 0; i < this.getSize(); ++i)
			newArray[i] = this.array[i];
		this.array = newArray;
	}
//}
//#Transform
//{
	@Override
	public JArray<T> toArray() {
		JArray<T> array = array(this.getSize());
		for(int i = 0; i < this.getSize(); ++i)
			array.set(i, this.unsafeGet(i));
		return array;
	}
	@Override
	public List<T> toNativeList() {
		List<T> nlist = new ArrayList<>(this.getSize());
		for(T value : this)
			nlist.add(value);
		return nlist;
	}
//}
//#IFace: Copy
//{
	@Override
	public TList<T> copy() {
		return new TList<>(this);
	}
//}
}
