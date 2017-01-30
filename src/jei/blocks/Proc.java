package jei.blocks;

import jei.blocks.failable.FailableProc;
import jei.collections.Tuple;

public interface Proc<Arguments extends Tuple> extends Block<Arguments, Void>, FailableProc<Arguments, RuntimeException>
{
	
}
