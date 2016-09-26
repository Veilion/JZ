package jei.collections2;

import jei.Base;

public class Tuple extends Base
{
	public static Tuple_0 empty() {
		return Tuple_0.INSTANCE;
	}
	public static <A> Tuple_1<A> of(A _1) {
		return new Tuple_1<>(_1);
	}
	public static <A, B> Tuple_2<A, B> of(A _1, B _2) {
		return new Tuple_2<>(_1, _2);
	}
	public static <A, B, C> Tuple_3<A, B, C> of(A _1, B _2, C _3) {
		return new Tuple_3<>(_1, _2, _3);
	}
	public static <A, B, C, D> Tuple_4<A, B, C, D> of(A _1, B _2, C _3, D _4) {
		return new Tuple_4<>(_1, _2, _3, _4);
	}
	public static <A, B, C, D, E> Tuple_5<A, B, C, D, E> of(A _1, B _2, C _3, D _4, E _5) {
		return new Tuple_5<>(_1, _2, _3, _4, _5);
	}
	
	protected Tuple() {}
	
	public final static class Tuple_0 extends Tuple
	{
		private static final Tuple_0
			INSTANCE = new Tuple_0();
		
		private Tuple_0() {}
		
		@Override
		public int hashCode() {
			return 0;
		}
		
		@Override
		public boolean equals(Object object) {
			return object == this;
		}
		
		@Override
		public String toString() {
			return "";
		}
	}
	
	public final static class Tuple_1<A> extends Tuple
	{
		public final A _1;
		
		private Tuple_1(A _1) {
			this._1 = _1;
		}
		
		@Override
		public int hashCode() {
			return hashAll(this._1);
		}
	}
	
	public final static class Tuple_2<A, B> extends Tuple
	{
		public final A _1;
		public final B _2;
		
		private Tuple_2(A _1, B _2) {
			this._1 = _1;
			this._2 = _2;
		}
		
		@Override
		public int hashCode() {
			return hashAll(this._1, this._2);
		}
			
		@Override
		public boolean equals(Object object) {
			if (object instanceof Tuple_2) {
				Tuple_2<?, ?> tuple = (Tuple_2<?, ?>) object;
				return equals(tuple._1, this._1) 
					&& equals(tuple._2, this._2)
				;
			}
			return false;
		}
		@Override
		public String toString() {
			return format("({0}, {1})", this._1, this._2);
		}
	}
	
	public final static class Tuple_3<A, B, C> extends Tuple
	{
		public final A _1;
		public final B _2;
		public final C _3;
		
		private Tuple_3(A _1, B _2, C _3) {
			this._1 = _1;
			this._2 = _2;
			this._3 = _3;
		}
		
		@Override
		public int hashCode() {
			return hashAll(this._1, this._2, this._3);
		}
		@Override
		public boolean equals(Object object) {
			if (object instanceof Tuple_3) {
				Tuple_3<?, ?, ?> tuple = (Tuple_3<?, ?, ?>) object;
				return equals(tuple._1, this._1) 
					&& equals(tuple._2, this._2)
					&& equals(tuple._3, this._3)
				;
			}
			return false;
		}
		@Override
		public String toString() {
			return format("({0}, {1}, {2})", this._1, this._2, this._3);
		}
	}
	
	public final static class Tuple_4<A, B, C, D> extends Tuple
	{
		public A _1;
		public B _2;
		public C _3;
		public D _4;
		
		private Tuple_4(A _1, B _2, C _3, D _4) {
			this._1 = _1;
			this._2 = _2;
			this._3 = _3;
			this._4 = _4;
		}
		
		@Override
		public int hashCode() {
			return hashAll(this._1, this._2, this._3, this._4);
		}
		@Override
		public boolean equals(Object object) {
			if (object instanceof Tuple_4) {
				Tuple_4<?, ?, ?, ?> tuple = (Tuple_4<?, ?, ?, ?>) object;
				return equals(tuple._1, this._1) 
					&& equals(tuple._2, this._2)
					&& equals(tuple._3, this._3)
					&& equals(tuple._4, this._4)
				;
			}
			return false;
		}
		@Override
		public String toString() {
			return format("({0}, {1}, {2}, {3})", this._1, this._2, this._3, this._4);
		}
	}
	
	public final static class Tuple_5<A, B, C, D, E> extends Tuple
	{
		public final A _1;
		public final B _2;
		public final C _3;
		public final D _4;
		public final E _5;
		
		private Tuple_5(A _1, B _2, C _3, D _4, E _5) {
			this._1 = _1;
			this._2 = _2;
			this._3 = _3;
			this._4 = _4;
			this._5 = _5;
		}
		
		@Override
		public int hashCode() {
			return hashAll(this._1, this._2, this._3, this._4, this._5);
		}
		@Override
		public boolean equals(Object object) {
			if (object instanceof Tuple_5) {
				Tuple_5<?, ?, ?, ?, ?> tuple = (Tuple_5<?, ?, ?, ?, ?>) object;
				return equals(tuple._1, this._1) 
					&& equals(tuple._2, this._2)
					&& equals(tuple._3, this._3)
					&& equals(tuple._4, this._4)
					&& equals(tuple._5, this._5)
				;
			}
			return false;
		}
		@Override
		public String toString() {
			return format("({0}, {1}, {2}, {3}, {4})", this._1, this._2, this._3, this._4, this._5);
		}
	}
}
