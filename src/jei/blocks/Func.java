package jei.blocks;

import jei.blocks.failable.FailableFunc;
import jei.collections.Tuple;

public interface Func<Arguments extends Tuple, Result> extends Block<Arguments, Result>, FailableFunc<Arguments, Result, RuntimeException>
{
	
}
