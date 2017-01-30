package jei.blocks;

import jei.blocks.failable.FailableEval;
import jei.collections.Tuple;

public interface Eval<Arguments extends Tuple> extends Block<Arguments, Boolean>, FailableEval<Arguments, RuntimeException>
{
	
}
