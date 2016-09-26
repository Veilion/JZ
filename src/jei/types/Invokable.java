package jei.types;

import java.lang.reflect.Executable;

import jei.Base;
import jei.annotation.Lazy;
import jei.collections.Array;
import jei.collections.FinalArray;
import jei.types.Parameter.Stream;

public abstract class Invokable extends Base implements Accessable, Note.Streamable, Parameter.Streamable
{
	private final Executable
		exec;
	
	@Lazy
	private Type<?>
		declaringType = null;
	
	@Lazy
	private FinalArray<Generic>
		generics = null;
	
	@Lazy
	private FinalArray<Type<?>>
		paramTypes = null;
	
	@Lazy
	private FinalArray<Type<? extends Throwable>>
		throwingTypes = null;
	
	@Lazy
	private Note.Stream
		notes = null,
		declaredNotes = null;
	
	@Lazy
	private Parameter.Stream
		params = null;
	
	@Lazy
	private Visibility
		visibility = null;
	
	protected Invokable(Executable exec) {
		this.exec = exec;
	}
	
	public Type<?> getDeclaringType() {
		if (this.declaringType == null) {
			this.declaringType = typeby(this.exec.getDeclaringClass());
		}
		return this.declaringType;
	}
	public String getName() {
		return this.exec.getName();
	}
	public FinalArray<Generic> getGenerics() {
		if (this.generics == null) {
			this.generics = map(this.exec.getTypeParameters(), e -> new Generic(_5)).readOnly();
		}
		return this.generics;
	}
	public FinalArray<Type<?>> getParamTypes() {
		if (this.paramTypes == null) {
			//TODO
			Object o = map(this.exec.getParameterTypes(), e -> typeby(_5));
		}
		return null;
	}
	public FinalArray<Type<? extends Throwable>> getThrowingTypes() {
		if(this.throwingTypes == null) {
			Class<?>[] exceptionTypes = this.exec.getExceptionTypes();
			Array<Type<? extends Throwable>> throwTypes = empty(exceptionTypes.length);
			for(Class<?> currentType : exceptionTypes) {
				@SuppressWarnings("unchecked")
				Class<? extends Throwable> throwType = (Class<? extends Throwable>) currentType;
				throwTypes.add(typeby(throwType));
			}
			this.throwingTypes = throwTypes.readOnly();
		}
		return this.throwingTypes;
	}
	public boolean hasVarargs() {
		return this.exec.isVarArgs();
	}
	public boolean isSynthetic() {
		return this.exec.isSynthetic();
	}
	
	@Override
	public Note.Stream notes() {
		if(this.notes == null) {
			this.notes = new Note.Stream(this.exec.getAnnotations());
		}
		return this.notes;
	}
	@Override
	public Note.Stream declaredNotes() {
		if(this.declaredNotes == null) {
			this.declaredNotes = new Note.Stream(this.exec.getDeclaredAnnotations());
		}
		return this.declaredNotes;
	}
	@Override
	public Parameter.Stream params() {
		if(this.params == null) {
			this.params = new Parameter.Stream(this, this.exec.getParameters());
		}
		return this.params;
	}
	@Override
	public Visibility getVisibility() {
		if(this.visibility == null) {
			this.visibility = Visibility.forModifier(this.exec.getModifiers());
		}
		return this.visibility;
	}
	
	@Override
	public int hashCode() {
		return this.exec.hashCode();
	}
	@Override
	public boolean equals(Object object) {
		if(object instanceof Invokable) {
			Invokable invokable = (Invokable) object;
			return invokable.exec.equals(this.exec);
		}
		return false;
	}
	@Override
	public abstract String toString();
}
