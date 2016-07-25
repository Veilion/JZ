package jei.utils;

import jei.StaticBase;

public abstract class UError extends StaticBase
{
	private UError() {}
	
	public static RuntimeException unchecked(Exception e) {
		UError.<RuntimeException>throwUnchecked(e);
		return null;
	}
	@SuppressWarnings("unchecked")
	private static <T extends Exception> void throwUnchecked(Exception e) throws T {
		throw (T) e;
	}
}
