package jei.functional;

import java.util.function.BiConsumer;

import jei.StandardLibraryContender;

@FunctionalInterface
public interface Consumer2<A, B> extends StandardLibraryContender<BiConsumer<A, B>>
{
	public static final Consumer2<Object, Object>
		PASS = (a, b) -> {};
	
	void call(A a, B b);
	
	@Override
	default BiConsumer<A, B> java() {
		return this::call;
	}
	
	default Consumer2<A, B> before(Consumer2<? super A,  ? super B> consumer) {
		return (a, b) -> {
			this.call(a, b);
			consumer.call(a, b);
		};
	}
	default Consumer2<A, B> after(Consumer2<? super A, ? super B> consumer) {
		return (a, b) -> {
			consumer.call(a, b);
			this.call(a, b);
		};
	}
	
	default Consumer<B> linkA(Supplier<? extends A> supplier) {
		return b -> this.call(supplier.call(), b);
	}
	default Consumer<A> linkB(Supplier<? extends B> supplier) {
		return a -> this.call(a, supplier.call());
	}
	default Sequence link(Supplier<? extends A> supplierA, Supplier<? extends B> supplierB) {
		return () -> this.call(supplierA.call(), supplierB.call());
	}

	default Consumer<B> bindA(A a) {
		return b -> this.call(a, b);
	}
	default Consumer<A> bindB(B b) {
		return a -> this.call(a, b);
	}
	default Sequence bind(A a, B b) {
		return () -> this.call(a, b);
	}
}
