package jei.utils;

import jei.StaticBase;
import jei.functional.Sequence;

public abstract class UTest extends StaticBase 
{
	private UTest() {};
	
	public static long withNanos(int tries, Sequence sequence) {
		
		long start = System.nanoTime();
		for(int i = 0; i < tries; ++i) {
			sequence.call();
		}
		return (System.nanoTime() - start) / tries;
	}
}
