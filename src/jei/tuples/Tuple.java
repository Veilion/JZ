package jei.tuples;

import jei.blocks.arity.Arity;

public abstract class Tuple implements Arity
{
	public static Tuple0 empty() {
		return Tuple0.INSTANCE;
	}
	
	public abstract int hashCode();
	
	public abstract boolean equals(Object other);
	
	public abstract String toString();
}
