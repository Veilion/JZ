package jei.functional2;

import jei.collections2.Tuple;

public interface Proc extends Block
{
	@Override
	Proc_1<? extends Tuple> tupled();
}
