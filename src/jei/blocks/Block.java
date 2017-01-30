package jei.blocks;

import jei.blocks.failable.FailableBlock;
import jei.collections.Tuple;

public interface Block<Arguments extends Tuple, Result> extends FailableBlock<Arguments, Result, RuntimeException>
{
	@Override
	Result apply(Arguments arguments);
	
	@Override
	default boolean isFailable() {
		return false;
	}
}
