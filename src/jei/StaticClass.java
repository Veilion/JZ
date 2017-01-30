package jei;

import jei.errors.coding.ForbiddenInvocationError;

public class StaticClass 
{
	protected StaticClass() {
		throw new ForbiddenInvocationError("attempted to create an instance of a static class");
	}
}
