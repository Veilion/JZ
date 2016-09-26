package jei.functional2;

import jei.collections2.Tuple.Tuple_3;

@FunctionalInterface
public interface Proc_3<A, B, C> extends Proc
{
	void call(A a, B b, C c);
	
	@Override
	default int arity() {
		return 3;
	}
	
	@Override
	default Proc_1<Tuple_3<A, B, C>> tupled() {
		return t -> this.call(t._1, t._2, t._3);
	}
}
