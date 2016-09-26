package jei.simple;

import jei.Base;
import jei.functional.Supplier;

public final class Computed<T> extends Base 
{
	public static <T> Computed<T> of(Supplier<T> supplier) {
		return new Computed<>(supplier);
	}
	
	private final Supplier<T>
		supplier;
	
	private Computed(Supplier<T> supplier) {
		this.supplier = supplier;
	}
	
	public T get() {
		return this.supplier.call();
	}
	
	@Override
	public int hashCode() {
		return hash(this.get());
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Computed) {
			Computed<?> computed = (Computed<?>) object;
			return equals(computed.get(), this.get());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return format("computed({0})", this.get());
	}
}
