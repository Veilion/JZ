package jei.functional2;

import jei.collections2.Tuple.Tuple_1;

@FunctionalInterface
public interface Proc_1<A> extends Proc 
{
	void call(A a);
	
	@Override
	default int arity() {
		return 1;
	}
	
	@Override
	default Proc_1<Tuple_1<A>> tupled() {
		return t -> this.call(t._1);
	}
}
