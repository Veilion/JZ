package jei.simple;

import jei.Base;
import jei.annotation.NonNull;
import jei.functional.Supplier;

public final class Lazy<T> extends Base
{
	public static <T> Lazy<T> of(@NonNull Supplier<T> supplier) {
		return new Lazy<>(nonNull(supplier));
	}
	
	private Supplier<T>
		supplier;
	
	private T
		value = null;
	
	private Lazy(Supplier<T> supplier) {
		this.supplier = supplier;
	}
	
	public T get() {
		if (this.value == null) {
			this.value = supplier.call();
			this.supplier = null;
		}
		return this.value;
	}
	
	public boolean isset() {
		return this.value != null;
	}
	
	@Override
	public int hashCode() {
		return hash(this.get());
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Lazy) {
			Lazy<?> lazy = (Lazy<?>) object;
			return equals(lazy.get(), this.get());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return format("lazy({0})", this.get());
	}
}
