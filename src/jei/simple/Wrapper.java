package jei.simple;

import jei.Base;

public class Wrapper<T> extends Base
{
	public static <T> Wrapper<T> empty() {
		return new Wrapper<>(null);
	}
	public static <T> Wrapper<T> of(T value) {
		return new Wrapper<>(value);
	}
	
	private T 
		value;
	
	private Wrapper(T value) {
		this.value = value;
	}
	
	public T get() {
		return this.value;
	}
	public T set(T value) {
		T safe = this.value;
		this.value = value;
		return safe;
	}
	
	public boolean isset() {
		return this.value != null;
	}
	
	@Override
	public int hashCode() {
		return hash(this.value);
	}
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof Wrapper) {
			return equals(((Wrapper<?>) object).value, this.value);
		}
		return false;
	}
	@Override
	public String toString() {
		return format("{0}({1})", typeOf(this).getName(), value);
	}
}
