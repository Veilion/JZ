package jei.utils;

import jei.StaticBase;

public abstract class UError extends StaticBase
{
	private UError() {}
	
	public static RuntimeException unchecked(Throwable e) {
		UError.<RuntimeException>throwUnchecked(e);
		return null;
	}
	@SuppressWarnings("unchecked")
	private static <T extends Throwable> void throwUnchecked(Throwable e) throws T {
		throw (T) e;
	}
}
