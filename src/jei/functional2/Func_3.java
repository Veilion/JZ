package jei.functional2;

import jei.collections2.Tuple.Tuple_3;

@FunctionalInterface
public interface Func_3<A, B, C, R> extends Func
{
	R call(A a, B b, C c);
	
	@Override
	default int arity() {
		return 3;
	}
	
	@Override
	default Func_1<Tuple_3<A, B, C>, R> tupled() {
		return t -> this.call(t._1, t._2, t._3);
	}
}
