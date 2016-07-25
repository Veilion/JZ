package jei.functional;

import jei.$;

@FunctionalInterface
public interface Consumer<T> 
{
	void unsafeInvoke(T value) throws Exception;
	
	default void invoke(T value) {
		try {
			this.invoke(value);
		} catch(RuntimeException e) {
			throw e;
		} catch(Exception e) {
			throw $.unchecked(e);
		}
	}
	
	default Consumer<T> before(Consumer<T> consumer) {
		return e -> {
			this.invoke(e);
			consumer.invoke(e);
		};
	}
	default Consumer<T> after(Consumer<T> consumer) {
		return e -> {
			consumer.invoke(e);
			this.invoke(e);
		};
	}
}
