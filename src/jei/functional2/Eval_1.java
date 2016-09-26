package jei.functional2;

import jei.collections2.Tuple.Tuple_1;

@FunctionalInterface
public interface Eval_1<A> extends Eval
{
	boolean call(A a);
	
	@Override
	default int arity() {
		return 1;
	}
	
	@Override
	default Eval_1<Tuple_1<A>> tupled() {
		return t -> this.call(t._1);
	}
}
