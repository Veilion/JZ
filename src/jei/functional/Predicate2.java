package jei.functional;

import java.util.function.BiPredicate;

import jei.StandardLibraryContender;

@FunctionalInterface
public interface Predicate2<A, B> extends Trapable<Consumer2<A, B>, Boolean>, StandardLibraryContender<BiPredicate<A, B>>
{
	public static final Predicate2<Object, Object>
		TRUE = (a, b) -> true,
		FALSE = (a, b) -> false
	;
	
	boolean call(A a, B b);
	
	@Override
	default BiPredicate<A, B> java() {
		return this::call;
	}
	
	default Predicate2<A, B> and(Predicate2<? super A, ? super B> predicate) {
		return (a, b) -> this.call(a, b) && predicate.call(a, b);
	}
	default Predicate2<A, B> or(Predicate2<? super A, ? super B> predicate) {
		return (a, b) -> this.call(a, b) || predicate.call(a, b);
	}
	default Predicate2<A, B> not() {
		return (a, b) -> ! this.call(a, b);
	}
	
	@Override
	default Consumer2<A, B> trap(Consumer<? super Boolean> consumer) {
		return (a, b) -> consumer.call(this.call(a, b));
	}
	
	default Predicate<B> linkA(Supplier<? extends A> supplier) {
		return b -> this.call(supplier.call(), b);
	}
	default Predicate<A> linkB(Supplier<? extends B> supplier) {
		return a -> this.call(a, supplier.call());
	}
	default Supplier<Boolean> link(Supplier<? extends A> supplierA, Supplier<? extends B> supplierB) {
		return () -> this.call(supplierA.call(), supplierB.call());
	}
	
	default Predicate<B> bindA(A a) {
		return b -> this.call(a, b);
	}
	default Predicate<A> bindB(B b) {
		return a -> this.call(a, b);
	}
	default Supplier<Boolean> bind(A a, B b) {
		return () -> this.call(a, b);
	}
}
