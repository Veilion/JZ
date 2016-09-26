package jei.functional2;

import jei.collections2.Tuple.Tuple_0;

@FunctionalInterface
public interface Eval_0 extends Eval
{
	boolean call();
	
	@Override
	default int arity() {
		return 0;
	}
	
	@Override
	default Eval_1<Tuple_0> tupled() {
		return t -> this.call();
	}
}
