package jei.types;

import jei.Base;
import jei.collections.Table;
import jei.simple.Lazy;

public abstract class Type<T> extends Base 
{
	private static Table<Class<?>, Type<?>>
		instances = table();
	
	public static <T> Type<T> forClass(Class<T> clazz) {
		@SuppressWarnings("unchecked")
		Type<T> instance = (Type<T>) instances.get(clazz).orGet(() -> {
			Type<T> newType = new Type<T>(clazz) {};
			instances.add(clazz, newType);
			return newType;
		});
		return instance;
	}
	
	private final Class<T>
		clazz;

	private Lazy<String>
		name = lazy(() -> {
			return this.clazz.getName();
		}),
		path = lazy(() -> {
			String name = this.getName();
			return name.substring(0, name.lastIndexOf('.'));
		})
	;
	
	
	private Type(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public String getName() {
		return this.name.get();
	}
	public String getPath() {
		return this.path.get();
	}
	
	public boolean isPrimitive() {
		return this.clazz.isPrimitive();
	}
	public boolean isArray() {
		return this.clazz.isArray();
	}
	public boolean isAnonymous() {
		//clazz implementation on initialization, eg. [new Bar() {}];
		return this.clazz.isAnonymousClass();
	}
	public boolean isMember() {
		//non-static inner class
		return this.clazz.isMemberClass();
	}
	public boolean isLocal() {
		//declared in a block of code
		return this.clazz.isLocalClass();
	}
	public boolean isSynthetic() {
		//created by the compiler
		return this.clazz.isSynthetic();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
