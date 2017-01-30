package jei.blocks.failable;

import jei.collections.Tuple;

public interface FailableProc<Arguments extends Tuple, Failure extends Throwable> extends FailableBlock<Arguments, Void, Failure>
{
	@Override
	default boolean isFunc() {
		return false;
	}
	
	@Override
	default boolean isProc() {
		return true;
	}
	
	@Override
	default boolean isEval() {
		return false;
	}
}
