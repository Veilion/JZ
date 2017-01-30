package jei.blocks.failable;

import jei.collections.Tuple;

public interface FailableEval<Arguments extends Tuple, Failure extends Throwable> extends FailableBlock<Arguments, Boolean, Failure>
{
	@Override
	default boolean isFunc() {
		return false;
	}
	
	@Override
	default boolean isProc() {
		return false;
	}
	
	@Override
	default boolean isEval() {
		return true;
	}
}
