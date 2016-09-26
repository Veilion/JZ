package jei.functional2;

import jei.collections2.Tuple.Tuple_4;

@FunctionalInterface
public interface Eval_4<A, B, C, D> extends Eval 
{
	boolean call(A a, B b, C c, D d);
	
	@Override
	default int arity() {
		return 4;
	}
	
	@Override
	default Eval_1<Tuple_4<A, B, C, D>> tupled() {
		return t -> this.call(t._1, t._2, t._3, t._4);
	}
}
