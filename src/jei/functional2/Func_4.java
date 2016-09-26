package jei.functional2;

import jei.collections2.Tuple.Tuple_4;

@FunctionalInterface
public interface Func_4<A, B, C, D, R> extends Func 
{
	R call(A a, B b, C c, D d);
	
	@Override
	default int arity() {
		return 4;
	}
	 
	@Override
	default Func_1<Tuple_4<A, B, C, D>, R> tupled() {
		return t -> this.call(t._1, t._2, t._3, t._4);
	}
}
