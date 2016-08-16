package jei.types;

import java.lang.reflect.Modifier;
import java.util.Iterator;

import jei.Base;
import jei.annotation.Lazy;
import jei.collections.Array;
import jei.error.BlockedAccessException;
import jei.error.IllegalInvocationError;
import jei.error.NullArgumentException;
import jei.error.UndefinedEntryException;
import jei.error.UnreachableError;
import jei.error.WrongTypeException;

public class Field extends Base implements Accessable, Note.Streamable
{
	private final java.lang.reflect.Field
		field;

	@Lazy
	private Visibility
		visibility = null;
	
	@Lazy
	private String
		string = null;
	
	@Lazy
	private Note.Stream
		notes = null,
		declaredNotes = null;
	
	public Field(java.lang.reflect.Field field) {
		this.field = field;
	}
	
	private <R> R privateGet(Object object, Class<R> type) throws BlockedAccessException, WrongTypeException {
		if(!this.getType().isChildOf(type)) {
			throw new WrongTypeException("the field \"" + this.getName() + "\" is not an instance of " + type.getName());
		}
		try {
			boolean isAccessible = this.field.isAccessible();
			this.field.setAccessible(true);
			@SuppressWarnings("unchecked")
			R result = (R) this.field.get(object);
			this.field.setAccessible(isAccessible);
			return result;
		} catch(IllegalArgumentException e) {
			throw new UnreachableError(e);
		} catch(IllegalAccessException e) {
			throw new BlockedAccessException("field \"" + this.getName() + "\" could not be read", e);
		}
	}
	private void privateSet(Object object, Object value) throws BlockedAccessException, WrongTypeException {
		if(!this.getType().isChildOf(value.getClass())) {
			throw new WrongTypeException("the field \"" + this.getName() + "\" is not an instance of " + value.getClass().getName());
		}
		try {
			boolean isAccessible = this.field.isAccessible();
			this.field.setAccessible(true);
			this.field.set(object, value);
			this.field.setAccessible(isAccessible);
		} catch (IllegalArgumentException e) {
			throw new UnreachableError(e);
		} catch (IllegalAccessException e) {
			throw new BlockedAccessException("field \"" + this.getName() + "\" could not be written", e);
		}
	}
	
	public String getName() {
		return this.field.getName();
	}
	
	public Object get(Object object) throws BlockedAccessException {
		return this.get(object, Object.class);
	}
	public <R> R get(Object object, Type<R> type) throws BlockedAccessException, WrongTypeException {
		return this.get(object, type.toClass());
	}
	public <R> R get(Object object, Class<R> type) throws BlockedAccessException, WrongTypeException {
		if(this.isStatic()) {
			throw new IllegalInvocationError("unable to read the static field \"" + this.getName() + "\" via instance");
		}
		if(object == null) {
			throw new NullArgumentException("unable to read the non-static field \"" + this.getName() + "\" with a null-instance");
		}
		return privateGet(object, type);
	}
	public Object staticGet() throws BlockedAccessException {
		return this.staticGet(Object.class);
	}
	public <R> R staticGet(Type<R> type) throws BlockedAccessException, WrongTypeException {
		return this.staticGet(type.toClass());
	}
	public <R> R staticGet(Class<R> type) throws BlockedAccessException, WrongTypeException {
		if(!this.isStatic()) {
			throw new IllegalInvocationError("unable to read the non-static field \"" + this.getName() + "\" without an instance of " + this.getDeclaringType().getQualifiedName());
		}
		return this.privateGet(null, type);
	}
	
	public void set(Object object, Object value) {
		if(this.isStatic()) {
			throw new IllegalInvocationError("unable to write to the static field \"" + this.getName() + "\" via instance");
		}
		if(object == null) {
			throw new NullArgumentException("unable to write to the non-static field \"" + this.getName() + "\" with a null-instance");
		}
		this.privateSet(object, value);
	}
	public void staticSet(Object value) {
		if(!this.isStatic()) {
			throw new IllegalInvocationError("unable to read the non-static field \"" + this.getName() + "\" without an instance of " + this.getDeclaringType().getQualifiedName());
		}
		this.privateSet(null, value);
	}
	
	@Override
	public Note.Stream notes() {
		if(this.notes == null) {
			this.notes = new Note.Stream(this.field.getAnnotations());
		}
		return this.notes;
	}
	@Override
	public Note.Stream declaredNotes() {
		if(this.declaredNotes == null) {
			this.declaredNotes = new Note.Stream(this.field.getDeclaredAnnotations());
		}
		return this.declaredNotes;
	}

	public Type<?> getType() {
		return typeby(this.field.getType());
	}
	public Type<?> getDeclaringType() {
		return typeby(this.field.getDeclaringClass());
	}
	
	@Override
	public Visibility getVisibility() {
		if(this.visibility == null) {
			int modifiers = this.field.getModifiers();
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
	
	public boolean isSynthetic() {
		return this.field.isSynthetic();
	}
	public boolean isStatic() {
		return Modifier.isStatic(this.field.getModifiers());
	}
	public boolean isFinal() {
		return Modifier.isFinal(this.field.getModifiers());
	}
	public boolean isVolatile() {
		return Modifier.isVolatile(this.field.getModifiers());
	}
	public boolean isTransient() {
		return Modifier.isTransient(this.field.getModifiers());
	}
	
	public boolean isEnumConstant() {
		return this.field.isEnumConstant();
	}
	
	@Override
	public int hashCode() {
		return this.field.hashCode();
	}
	@Override
	public boolean equals(Object object) {
		return this.field.equals(object);
	}
	@Override
	public String toString() {
		if(this.string == null) {
			StringBuilder builder = new StringBuilder(this.isPackagePrivate() ? "" : this.getVisibility().toString().toLowerCase() + " ");
			if (this.isStatic()) {
				builder.append("static ");
			}
			if (this.isFinal()) {
				builder.append("final ");
			}
			if (this.isVolatile()) {
				builder.append("volatile ");
			}
			if (this.isTransient()) {
				builder.append("transient ");
			}
			builder.append(this.getName());
			this.string =  builder.toString();
		}
		return this.string;
	}
	
	public static class Stream extends Base implements Iterable<Field>
	{
		private final Field[]
			fields;
		
		Stream(java.lang.reflect.Field[] fields) {
			this.fields = map(fields, e -> new Field(e)).toNativeArray(Field.class);
		}

		public Field withName(String name) throws UndefinedEntryException {
			for(Field field : this.fields) {
				if(field.getName() == name) {
					return field;
				}
			}
			throw new UndefinedEntryException("no field with the name \"" + name + "\" found");
		}
		
		
		public Array<Field> asArray() {
			return array(this.fields);
		}

		@Override
		public Iterator<Field> iterator() {
			final Stream that = this;
			return new Iterator<Field>() {
				private int
					i = 0;
				@Override
				public boolean hasNext() {
					return this.i < that.fields.length;
				}
				@Override
				public Field next() {
					return that.fields[this.i++];
				}
			};
		}
	}
}
