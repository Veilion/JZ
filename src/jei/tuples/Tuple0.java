package jei.tuples;

import jei.blocks.arity.Arity0;

public class Tuple0 extends Tuple implements Arity0
{
	static final Tuple0 
		INSTANCE = new Tuple0();
	
	private Tuple0() { }
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public boolean equals(Object other) {
		return other == this;
	}
	
	@Override
	public String toString() {
		return "()";
	}
}
