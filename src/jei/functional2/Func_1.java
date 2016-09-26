package jei.functional2;

import jei.collections2.Tuple.Tuple_1;

@FunctionalInterface
public interface Func_1<A, R> extends Func
{
	R call(A a);
	
	@Override
	default int arity() {
		return 1;
	}
	
	@Override
	default Func_1<Tuple_1<A>, R> tupled() {
		return t -> this.call(t._1);
	}
}
