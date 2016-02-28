package jz.collections;

import jz.Const;
import jz.JObject;
import jz.error.NotYetImplementedException;
import jz.error.UnreachableException;
import jz.func.FCompare;
import jz.query.JQuery;
import jz.query.JQueryResult;

public abstract class AIndex<T> extends JObject implements JIndex<T>
{
//#Get
//{
	@Override
	public T get(int index) {
		return this.safeGet(index);
	}
	protected T safeGet(int index) {
		try {
			return this.unsafeGet(this.secIndex(index));
		} catch(ArrayIndexOutOfBoundsException e) {
			this.checkIndex(index);
			throw new UnreachableException("Index not checked: " + index, e);
		}
	}
	protected abstract T unsafeGet(int index);
//}
//#Set
//{
	@Override
	public T set(int index, T value) {
		T current = this.safeGet(index);
		this.unsafeSet(index, value);
		return current;
	}
	@Override
	public int set(JQuery<T, ? extends T> query) {
		int amount = 0;
		for(int i = 0; i < this.getSize(); ++i) {
			JQueryResult<? extends T> result = query.check(this.unsafeGet(i));
			if(result.isSuccess())
				this.unsafeSet(i, result.getValue());
		}
		return amount;
	}
	protected void safeSet(int index, T value) {
		try {
			this.unsafeSet(this.secIndex(index), value);
		} catch(ArrayIndexOutOfBoundsException e) {
			this.checkIndex(index);
			throw new UnreachableException("Index not checked: " + index, e);
		}
	}
	protected abstract void unsafeSet(int index, T value);
//}
//#Contains
//{
	@Override
	public boolean contains(T value) {
		if(null == value) {
			for(T current : this) 
				if(null == current)
					return true;
		} else {
			for(T current : this) 
				if(null != current && value.equals(current))
					return true;
		}
		return false;
	}
	@Override
	public boolean contains(JQuery<T, ?> query) {
		for(T current : this)
			if(query.check(current).isSuccess())
				return true;
		return false;
	}
//}
//#Amount
//{
	@Override
	public int amountOf(T value) {
		int amount = 0;
		if(null == value) {
			for(T current : this) 
				if(null == current)
					++amount;
		} else {
			for(T current : this) 
				if(null != current && value.equals(current))
					++amount;
		}
		return amount;
	}
	@Override
	public int amountOf(JQuery<T, ?> query) {
		int amount = 0;
		for(T current : this)
			if(query.check(current).isSuccess())
				++amount;
		return amount;
	}
//}
//#Index
//{
	public int firstIndex(T value) {
		if(null == value) {
			for(int i = 0; i < this.getSize(); ++i) 
				if(null == this.unsafeGet(i))
					return i;
		} else {
			for(int i = 0; i < this.getSize(); ++i) 
				if(null != this.unsafeGet(i) && value.equals(this.unsafeGet(i)))
					return i;
		}
		return Const.NO_INDEX;
	}
	@Override
	public int firstIndex(JQuery<T, ?> query) {
		for(int i = 0; i < this.getSize(); ++i)
			if(query.check(this.unsafeGet(i)).isSuccess())
				return i;
		return Const.NO_INDEX;
	}
	public int lastIndex(T value) {
		if(null == value) {
			for(int i = this.getSize() - 1; i >= 0; --i) 
				if(null == this.unsafeGet(i))
					return i;
		} else {
			for(int i = this.getSize() - 1; i >= 0; --i) 
				if(null != this.unsafeGet(i) && value.equals(this.unsafeGet(i)))
					return i;
		}
		return Const.NO_INDEX;
	}
	@Override
	public int lastIndex(JQuery<T, ?> query) {
		for(int i = this.getSize() - 1; i >= 0; ++i) 
			if(query.check(this.unsafeGet(i)).isSuccess())
				return i;
		return Const.NO_INDEX;
	}
	protected int secIndex(int index) {
		if(index <= -this.getSize() - 1) {
			index += this.getSize();
		}
		return index;
	}
//}
//#Sort
//{
	public void sortAsc(FCompare<? super T> cmp) {
		throw new NotYetImplementedException();
	}
	public void sortDesc(FCompare<? super T> cmp) {
		throw new NotYetImplementedException();
	}
//}
//#Check
//{
	protected void checkIndex(int index) throws RuntimeException {
		if(this.isEmpty())
			throw new IndexOutOfBoundsException("cannot access an empty array");
		if(index < 0)
			throw new IndexOutOfBoundsException(string("index out of bounds: (min=0, index={0})", index));
		if(index > this.getSize())
			throw new IndexOutOfBoundsException(string("index out of bounds: (max={0}, index={1})", this.getSize() - 1, index));
	}
//}
//#IFace: Copy
//{
	public abstract AIndex<T> copy();
//}

}
