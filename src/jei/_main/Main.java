package jei._main;

import jei.Base;
import jei.collections.Array;
import jei.utils.UString;
import jei.utils.UTest;

public class Main extends Base
{
	public static void main(String[] args) {
		
		
		Array<String> array = array();
		for(int i = 0; i < 1000; ++i) {
			array.add("Entry " + i);
		}
		Object r1 = UTest.withNanos(1000, () -> {
			Array<Integer> result = array
				.map(e -> Integer.valueOf(UString.removeStart(e, "Entry ".length())))
				.filter(e -> e > 10 && e < 50)
				.getAll(1, 2, 3, 4);
			;
		});
		Object r2 = UTest.withNanos(1000, () -> {
			Array<Integer> result = array();
			for(String e : array) {
				int value = Integer.valueOf(UString.removeStart(e, "Entry ".length()));
				if(value > 10 && value < 50) {
					result.add(value);
				}
			}
		});
		
		print("Result 1: " + r1);
		print("Result 2: " + r2);
	}
}
