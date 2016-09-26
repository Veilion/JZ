package jei.functional2;

import jei.collections2.Tuple.Tuple_0;

@FunctionalInterface
public interface Func_0<R> extends Func, Make<R> 
{
	@Override
	R call();
	
	@Override
	default int arity() {
		return 0;
	}
	
	@Override
	default Func_1<Tuple_0, R> tupled() {
		return t -> this.call();
	}
}
