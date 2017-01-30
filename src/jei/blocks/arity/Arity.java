package jei.blocks.arity;

import jei.errors.coding.MissingImplementationError;

public interface Arity {
	default int artiy() {
		throw new MissingImplementationError();
	}
}
