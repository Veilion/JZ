package jei.functional;

import java.util.function.BiFunction;

import jei.StandardLibraryContender;

@FunctionalInterface
public interface Producer2<A, B, R> extends Trapable<Consumer2<A, B>, R>, StandardLibraryContender<BiFunction<A, B, R>>
{
	R call(A a, B b);
	
	@Override
	default BiFunction<A, B, R> java() {
		return this::call;
	}
	
	@Override
	default Consumer2<A, B> trap(Consumer<? super R> consumer) {
		return (a, b) -> consumer.call(this.call(a, b));
	}
	
	default Producer<B, R> linkA(Supplier<A> supplier) {
		return b -> this.call(supplier.call(), b);
	}
	default Producer<A, R> linkB(Supplier<B> supplier) {
		return a -> this.call(a, supplier.call());
	}
	default Supplier<R> link(Supplier<A> supplierA, Supplier<B> supplierB) {
		return () -> this.call(supplierA.call(), supplierB.call());
	}
	
	default Producer<B, R> bindA(A a) {
		return b -> this.call(a, b);
	}
	default Producer<A, R> bindB(B b) {
		return a -> this.call(a, b);
	}
	default Supplier<R> bind(A a, B b) {
		return () -> this.call(a, b);
	}
}
