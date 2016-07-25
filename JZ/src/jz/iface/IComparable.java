package jz.iface;

import jz.Const;

public interface IComparable<T> extends Comparable<T>
{
//#Compare
//{
	public int compareTo(T value);
	public default boolean eq(T value) {
		 return this.compareTo(value) == Const.EQUAL;
	}
	public default boolean ne(T value) {
		return this.compareTo(value) != Const.EQUAL;
	}
	public default boolean lt(T value) {
		return this.compareTo(value) == Const.LESS;
	}
	public default boolean gt(T value) {
		return this.compareTo(value) == Const.GREATER;
	}
	public default boolean le(T value) {
		int result = this.compareTo(value);
		return Const.LESS == result || Const.EQUAL == result;
	}
	public default boolean ge(T value) {
		int result = this.compareTo(value);
		return Const.GREATER == result || Const.EQUAL == result;
	}
//}
}
