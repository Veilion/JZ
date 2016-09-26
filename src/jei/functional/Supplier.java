package jei.functional;

import jei.StandardLibraryContender;

@FunctionalInterface
public interface Supplier<R> extends Trapable<Sequence, R>, StandardLibraryContender<java.util.function.Supplier<R>>
{
	R call();
	
	@Override
	default java.util.function.Supplier<R> java() {
		return this::call;
	}

	@Override
	default Sequence trap(Consumer<? super R> consumer) {
		return () -> consumer.call(this.call());
	}
}
