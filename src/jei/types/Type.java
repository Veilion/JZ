package jei.types;

import jei.Base;
import jei.annotation.Lazy;
import jei.collections.Table;

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

	private final String
		name
	;	
	
	@Lazy 
	private String
		path   = null,
		string = null,
		qualifiedName = null
	;
	
	@Lazy
	private Kind
		kind = null
	;
	
	
	private Type(Class<T> clazz) {
		this.clazz = clazz;
		this.name = this.clazz.getSimpleName();
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getQualifiedName() {
		if(this.qualifiedName == null) {
			this.qualifiedName = this.getPath();
			if(!path.isEmpty()) {
				this.qualifiedName += '.';
			}
			this.qualifiedName += this.getName();
		}
		return this.qualifiedName;
	}
	public String getPath() {
		if(this.path == null) {
			if(this.isPrimitive()) {
				this.path = "";
			} else {
				this.path = this.clazz.getName().substring(0, this.clazz.getName().lastIndexOf('.'));	
			}
		}
		return this.path;
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
	
	public boolean isChildOf(Type<?> type) {
		return nonNull(type).toClass().isAssignableFrom(this.clazz);
	}
	public boolean isChildOf(Class<?> clazz) {
		return nonNull(clazz).isAssignableFrom(this.clazz);
	}
	
	
	public Kind getKind() {
		if(this.kind == null) {
			if(this.clazz.isAnnotation()) {
				this.kind = Kind.ANNOTATION;
			} else if(this.clazz.isInterface()) {
				this.kind = Kind.INTERFACE;
			} else if(this.clazz.isEnum()) {
				this.kind = Kind.ENUMERATION;
			} else if(this.clazz.isPrimitive()) {
				this.kind = Kind.PRIMITIVE;
			} else {
				this.kind = Kind.CLASS;
			}
		}
		return this.kind;
	}
	
	public Class<T> toClass() {
		return this.clazz;
	}
	@Override
	public String toString() {
		//TODO toString should create a valid java class
		if(this.string == null) {
			this.string = this.getQualifiedName();
			if(!this.isPrimitive()) {
				this.string = this.getKind().getKeyword() + " " + this.string;
			}
		}
		return this.string;
	}
	
	public enum Kind {
		CLASS("class"), 
		INTERFACE("interface"), 
		ENUMERATION("enum"), 
		ANNOTATION("@interface"), 
		PRIMITIVE(""),
		;
		
		private final String
			keyword;
		
		private Kind(String keyword) {
			this.keyword = keyword;
		}
		public String getKeyword() {
			return this.keyword;
		}
	}
}
