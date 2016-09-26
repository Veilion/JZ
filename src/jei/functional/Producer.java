package jei.functional;

import java.util.function.Function;

import jei.StandardLibraryContender;

@FunctionalInterface
public interface Producer<A, R> extends Trapable<Consumer<A>, R>, StandardLibraryContender<Function<A, R>>
{
	public static <T> Producer<T, T> pass() {
		return it -> it;
	}
	
	R call(A it);
	
	@Override
	default Function<A, R> java() {
		return this::call;
	}
	
	default <O> Producer<A, O> into(Producer<R, O> producer) {
		return it -> producer.call(this.call(it));
	}
	default <I> Producer<I, R> from(Producer<I, A> producer) {
		return it -> this.call(producer.call(it));
	}
	
	@Override
	default Consumer<A> trap(Consumer<? super R> consumer) {
		return it -> consumer.call(this.call(it));
	}
	
	default Supplier<R> link(Supplier<A> supplier) {
		return () -> this.call(supplier.call());
	}
	
	default Supplier<R> bind(A it) {
		return () -> this.call(it);
	}
}
