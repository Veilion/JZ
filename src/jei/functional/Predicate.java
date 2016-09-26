package jei.functional;

import jei.StandardLibraryContender;

@FunctionalInterface
public interface Predicate<A> extends Trapable<Consumer<A>, Boolean>, StandardLibraryContender<java.util.function.Predicate<A>>
{
	public static final Predicate<Object>
		TRUE = it -> true,
		FALSE = it -> false
	;
	
	boolean call(A it);

	@Override
	default java.util.function.Predicate<A> java() {
		return this::call;
	}
	
	default Predicate<A> and(Predicate<? super A> predicate) {
		return it -> this.call(it) && predicate.call(it);
	}
	default Predicate<A> or(Predicate<? super A> predicate) {
		return it -> this.call(it) || predicate.call(it);
	}
	default Predicate<A> not() {
		return it -> ! this.call(it);
	}
	
	@Override
	default Consumer<A> trap(Consumer<? super Boolean> consumer) {
		return it -> consumer.call(this.call(it));
	}
	
	default Supplier<Boolean> link(Supplier<? extends A> supplier) {
		return () -> this.call(supplier.call());
	}
	
	default Supplier<Boolean> bind(A value) {
		return () -> this.call(value);
	}
}
