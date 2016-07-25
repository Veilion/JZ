package jz.collections;

import jz.iface.ICopyable;

public interface IPair<L, R> extends ICopyable<IPair<L, R>>
{
//#Left
//{
	public L getLeft();
	public L setLeft(L value);
//}
//#Right
//{
	public R getRight();
	public R setRight(R value);
//}
}
