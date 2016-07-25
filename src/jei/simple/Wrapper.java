package jei.simple;

import jei.Base;

public class Wrapper<T> extends Base
{
	private T 
		value;
	
	public Wrapper() {
		this(null);
	}
	public Wrapper(T value) {
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
		if(this == object) {
			return true;
		}
		if(object instanceof Wrapper) {
			return equals(((Wrapper<?>) object).value, this.value);
		}
		return false;
	}
	@Override
	public String toString() {
		return format("{0}({1})", typeof(this).getName(), value);
	}
}
