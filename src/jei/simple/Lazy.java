package jei.simple;

import jei.Base;
import jei.functional.Supplier;

public class Lazy<T> extends Base
{
	private Supplier<T>
		supplier;
	
	private boolean
		isset = false;
	
	private T
		value = null;
	
	public Lazy(Supplier<T> creator) {
		this.supplier = nonNull(creator);
	}
	
	public boolean isset() {
		return this.isset;
	}
	public T get() {
		if(!this.isset) {
			this.value = this.supplier.invoke();
			this.isset = true;
		}
		return this.value;
	}
	
	@Override
	public int hashCode() {
		return hash(this.get());
	}
	@Override
	public boolean equals(Object object) {
		if(this == object) {
			return true;
		} 
		if(object instanceof Lazy) {
			return equals(((Lazy<?>) object).get(), this.get());
		}
		return equals(object, this.get());
	}
	@Override
	public String toString() {
		return stringify(this.get());
	}
}
