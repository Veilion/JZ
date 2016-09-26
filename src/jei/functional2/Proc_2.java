package jei.functional2;

import jei.collections2.Tuple.Tuple_2;

@FunctionalInterface
public interface Proc_2<A, B> extends Proc
{
	void call(A a, B b);
	
	@Override
	default int arity() {
		return 2;
	}
	
	@Override
	default Proc_1<Tuple_2<A, B>> tupled() {
		return t -> this.call(t._1, t._2);
	}
}
