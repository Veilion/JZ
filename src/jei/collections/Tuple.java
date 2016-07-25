package jei.collections;

import jei.Base;

public abstract class Tuple extends Base
{
	protected Tuple() {}
	
	public static class Tuple2<A, B> extends Tuple 
	{
		public A _1;
		public B _2;
		public Tuple2(A _1, B _2) {
			this._1 = _1;
			this._2 = _2;
		}
		@Override
		public int hashCode() {
			return hash(this._1) ^ hash(this._2);
		}
		@Override
		public boolean equals(Object object) {
			if(this == object) {
				return true;
			}
			if(object instanceof Tuple2) {
				Tuple2<?, ?> t2 = (Tuple2<?, ?>) object;
				return equals(this._1, t2._1) && equals(this._2, t2._2);
			}
			return false;
		}
		@Override
		public String toString() {
			return format("({0}, {1})", this._1, this._2);
		}
	}
	public static class Tuple3<A, B, C> extends Tuple 
	{
		public A _1;
		public B _2;
		public C _3;
		public Tuple3(A _1, B _2, C _3) {
			this._1 = _1;
			this._2 = _2;
			this._3 = _3;
		}
		@Override
		public int hashCode() {
			return hash(this._1) ^ hash(this._2) ^ hash(this._3);
		}
		@Override
		public boolean equals(Object object) {
			if(this == object) {
				return true;
			}
			if(object instanceof Tuple3) {
				Tuple3<?, ?, ?> t3 = (Tuple3<?, ?, ?>) object;
				return equals(this._1, t3._1) && equals(this._2, t3._2) && equals(this._3, t3._3);
			}
			return false;
		}
		@Override
		public String toString() {
			return format("({0}, {1})", this._1, this._2);
		}
	}
}
