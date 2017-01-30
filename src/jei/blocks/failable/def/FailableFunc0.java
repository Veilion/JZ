package jei.blocks.failable.def;

import jei.blocks.arity.Arity0;
import jei.blocks.failable.FailableFunc;
import jei.collections.Tuple.Tuple0;

public interface FailableFunc0<Result, Failure extends Throwable> extends FailableFunc<Tuple0, Result, Failure>, Arity0
{
	Result call() throws Failure;
	
	@Override
	default Result apply(Tuple0 arguments) throws Failure {
		return this.call();
	}
	
	@Override
	default int arity() {
		return 0;
	}
}
