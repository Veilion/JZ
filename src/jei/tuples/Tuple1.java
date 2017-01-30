package jei.tuples;

import java.util.Objects;

import jei.blocks.arity.Arity1;

public class Tuple1<T1> extends Tuple implements Arity1
{
	private final T1
		_1;
	
	Tuple1(T1 _1) {
		this._1 = _1;
	}
	
	public T1 _1() {
		return this._1;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this._1);
	}
}
