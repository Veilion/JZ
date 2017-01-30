package jei.blocks.failable.def;

import jei.blocks.arity.Arity1;
import jei.blocks.failable.FailableFunc;
import jei.collections.Tuple.Tuple1;

public interface FailableFunc1<Param1, Result, Failure extends Throwable> extends FailableFunc<Tuple1<Param1>, Result, Failure>, Arity1
{
	Result call() throws Failure;
	
	@Override
	default Result apply(Tuple1<Param1> arguments) throws Failure {
		return this.call();
	}
	
	@Override
	default int arity() {
		return 0;
	}
}
