package jei.functional;

import jei.StandardLibraryContender;

@FunctionalInterface
public interface Consumer<A> extends StandardLibraryContender<java.util.function.Consumer<A>>
{
	public static final Consumer<Object>
		PASS = it -> {};
	
	void call(A it);
	
	@Override
	default java.util.function.Consumer<A> java() {
		return this::call;
	}
	
	default Consumer<A> before(Consumer<? super A> consumer) {
		return e -> {
			this.call(e);
			consumer.call(e);
		};
	}
	default Consumer<A> after(Consumer<? super A> consumer) {
		return e -> {
			consumer.call(e);
			this.call(e);
		};
	}
	
	default Sequence link(Supplier<? extends A> supplier) {
		return () -> this.call(supplier.call());
	}
	default Sequence bind(A value) {
		return () -> this.call(value);
	}
}
