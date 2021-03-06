package jei.functional2;

import jei.collections2.Tuple.Tuple_5;

@FunctionalInterface
public interface Proc_5<A, B, C, D, E> extends Proc
{
	void call(A a, B b, C c, D d, E e);
	
	@Override
	default int arity() {
		return 5;
	}
	
	@Override
	default Proc_1<Tuple_5<A, B, C, D, E>> tupled() {
		return t -> this.call(t._1, t._2, t._3, t._4, t._5);
	}
}
