package jei.functional2;

import jei.collections2.Tuple.Tuple_2;

@FunctionalInterface
public interface Func_2<A, B, R> extends Func
{
	R call(A a, B b);
	
	@Override
	default int arity() {
		return 2;
	}
	
	@Override
	default Func_1<Tuple_2<A, B>, R> tupled() {
		return t -> this.call(t._1, t._2);
	}
}
