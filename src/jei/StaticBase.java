package jei;

import jei.error.IllegalInvocationError;

public abstract class StaticBase extends Base
{
	protected StaticBase() {
		throw new IllegalInvocationError("cannot instantiate the static class " + this.getClass().getName());
	}
}
