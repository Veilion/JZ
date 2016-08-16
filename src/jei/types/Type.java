package jei.types;

import java.lang.reflect.Modifier;

import jei.Base;
import jei.annotation.Lazy;
import jei.collections.Table;

public class Type<T> extends Base implements Accessable
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
		name;	
	
	@Lazy 
	private String
		path   = null,
		string = null,
		qualifiedName = null;
	
	@Lazy
	private Kind
		kind = null;
	
	@Lazy
	private Visibility
		visibility = null;

	@Lazy
	private Field.Stream
		fields = null;
	
	@Lazy
	private Note.Stream
		notes = null,
		declaredNotes = null;
	
	@Lazy
	private Constructor.Stream<? super T>
		consts = null;
	
	@Lazy
	private Constructor.Stream<T>
		declaredConsts = null;
	
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
			} else if(this.isArray()) {
				Class<?> c = this.clazz;
				while(c.isArray()) {
					c = c.getComponentType();
				}
				if(c.isPrimitive()) {
					this.path = "";
				} else {
					this.path = c.getName().substring(0, c.getName().lastIndexOf('.'));
				}
			} else {
				this.path = this.clazz.getName().substring(0, this.clazz.getName().lastIndexOf('.'));	
			}
		}
		return this.path;
	}
	
	public Field.Stream fields() {
		if(this.fields == null) {
			this.fields = new Field.Stream(this.clazz.getFields());
		}
		return this.fields;
	}
	public Note.Stream notes() {
		if(this.notes == null) {
			this.notes = new Note.Stream(this.clazz.getAnnotations());
		}
		return this.notes;
	}
	public Note.Stream declaredNotes() {
		if(this.declaredNotes == null) {
			this.declaredNotes = new Note.Stream(this.clazz.getDeclaredAnnotations());
		}
		return this.declaredNotes;
	}
	public Constructor.Stream<? super T> consts() {
		if(this.consts == null) {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Constructor.Stream<? super T> _consts = (Constructor.Stream<? super T>) new Constructor.Stream(this.clazz.getConstructors());
			this.consts = _consts;
		}
		return this.consts;
	}
	public Constructor.Stream<T> declaredConsts() {
		if(this.declaredConsts == null) {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Constructor.Stream<T> _declaredConsts = (Constructor.Stream<T>) new Constructor.Stream(this.clazz.getDeclaredConstructors());
			this.declaredConsts = _declaredConsts;
		}
		return this.declaredConsts;
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
	public Visibility getVisibility() {
		if(this.visibility == null) {
			int modifiers = this.clazz.getModifiers();
			if (Modifier.isPublic(modifiers)) {
				this.visibility = Visibility.PUBLIC;
			} else if (Modifier.isProtected(modifiers)) {
				this.visibility = Visibility.PROTECTED;
			} else if (Modifier.isPrivate(modifiers)) {
				this.visibility = Visibility.PRIVATE;
			} else {
				this.visibility = Visibility.PACKAGE_PRIVATE;
			}
		}
		return this.visibility;
	}
	@Override
	public boolean isPublic() {
		return this.getVisibility() == Visibility.PUBLIC;
	}
	@Override
	public boolean isProtected() {
		return this.getVisibility() == Visibility.PROTECTED;
	}
	@Override
	public boolean isPackagePrivate() {
		return this.getVisibility() == Visibility.PACKAGE_PRIVATE;
	}
	@Override
	public boolean isPrivate() {
		return this.getVisibility() == Visibility.PRIVATE;
	}
	
	public boolean isStatic() {
		return Modifier.isStatic(this.clazz.getModifiers());
	}
	public boolean isFinal() {
		return Modifier.isFinal(this.clazz.getModifiers());
	}
	public boolean isInterface() {
		return Modifier.isInterface(this.clazz.getModifiers());
	}
	public boolean isAbstract() {
		return Modifier.isAbstract(this.clazz.getModifiers());
	}
	
	public boolean isChildOf(Type<?> type) {
		return nonNull(type).toClass().isAssignableFrom(this.clazz);
	}
	public boolean isChildOf(Class<?> clazz) {
		return nonNull(clazz).isAssignableFrom(this.clazz);
	}
	public boolean isParentOf(Type<?> type) {
		return this.clazz.isAssignableFrom(nonNull(type).toClass());
	}
	public boolean isParentOf(Class<?> clazz) {
		return this.clazz.isAssignableFrom(nonNull(clazz));
	}
	public boolean isTypeOf(Object object) {
		return this.clazz.isInstance(object);
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
