package jz.collections;

import jz.JObject;

public class JPair<L, R> extends JObject implements IPair<L, R>
{
//#Variables: Instance
//{
	private L
		left;
	private R
		right;
//}
//#Constructor
//{
	public JPair() {
		this(null, null);
	}
	public JPair(L left, R right) {
		this.left = left;
		this.right = right;
	}
//}
//#Left
//{
	public L getLeft() {
		return this.left;
	}
	public L setLeft(L value) {
		L before = this.getLeft();
		this.left = value;
		return before;
	}
//}
//#Right
//{
	public R getRight() {
		return this.right;
	}
	public R setRight(R value) {
		R before = this.getRight();
		this.right = value;
		return before;
	}
//}
//#Copy
//{
	@Override
	public JPair<L, R> copy() {
		return new JPair<>(this.getLeft(), this.getRight());
	}
//}
}
