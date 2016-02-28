package jz.func;

import jz.Const;

public interface FCompare<T> 
{
//#Comparison
//{
	public int compare(T object1, T object2);
	public default boolean eq(T left, T right) {
		return this.compare(left, right) == 0;
	}
	public default boolean gt(T left, T right) {
		return this.compare(left, right) == Const.GREATER;
	}
	public default boolean lt(T left, T right) {
		return this.compare(left, right) == -1;
	}
	public default boolean ne(T left, T right) {
		return this.compare(left, right) != 0;
	}
	public default boolean ge(T left, T right) {
		int result = this.compare(left, right);
		return result == 1 || result == 0;
	}
	public default boolean le(T left, T right) {
		int result = this.compare(left, right);
		return result == -1 || result == 0;
	}
//}
//#Static: Factory
//{
	public static FCompare<String> byStrLength() {
		return (leftStr, rightStr) -> {
			int left = leftStr.length(), right = rightStr.length();
			return left > right ? Const.GREATER : left < right ? Const.LESS : leftStr.compareTo(rightStr);
		};
	}
	public static <P extends Comparable<P>> FCompare<P> byValue() {
		return (left, right) -> {
			return left.compareTo(right);
		};
	}
//}
}
