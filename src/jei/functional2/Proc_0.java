package jei.functional2;

import jei.collections2.Tuple.Tuple_0;

@FunctionalInterface
public interface Proc_0 extends Proc
{
	void call();
	
	@Override
	default int arity() {
		return 0;
	}
	
	@Override
	default Proc_1<Tuple_0> tupled() {
		return t -> this.call();
	}
}
