package jei.functional2;

import jei.collections2.Tuple.Tuple_5;

@FunctionalInterface
public interface Func_5<A, B, C, D, E, R> extends Func 
{
	R call(A a, B b, C c, D d, E e);
	
	@Override
	default int arity() {
		return 5;
	}
	
	@Override
	default Func_1<Tuple_5<A, B, C, D, E>, R> tupled() {
		return t -> this.call(t._1, t._2, t._3, t._4, t._5);	
	}
}
