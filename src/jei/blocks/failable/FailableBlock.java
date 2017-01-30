package jei.blocks.failable;

import jei.collections.Tuple;

public interface FailableBlock<Arguments extends Tuple, Result, Failure extends Throwable>
{
	Result apply(Arguments arguments) throws Failure;
	
	int arity();
	
	boolean isFunc();
	
	boolean isProc();
	
	boolean isEval();
	
	default boolean isFailable() {
		return true;
	}
}