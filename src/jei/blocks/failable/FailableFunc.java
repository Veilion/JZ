package jei.blocks.failable;

import jei.collections.Tuple;

public interface FailableFunc<Arguments extends Tuple, Result, Failure extends Throwable> extends FailableBlock<Arguments, Result, Failure> 
{
	@Override
	default boolean isFunc() {
		return true;
	}
	
	@Override
	default boolean isProc() {
		return false;
	}
	
	@Override
	default boolean isEval() {
		return false;
	}
}
