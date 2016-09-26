package jei.types;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Iterator;

import jei.Base;
import jei.annotation.Lazy;
import jei.collections.Countable;
import jei.error.UndefinedEntryException;
import jei.simple.Option;
import jei.utils.UArray;

public class Parameter extends Base implements Note.Streamable
{
	private final java.lang.reflect.Parameter
		param;
	
	private final Invokable
		declaringInvokable;
	
	@Lazy
	private Type<?>
		type = null;
	
	@Lazy
	private Note.Stream
		notes = null,
		declaredNotes = null;
	
	@Lazy
	private String
		string = null;
	
	private Parameter(Invokable declaringInvokable, java.lang.reflect.Parameter param) {
		this.param = param;
		this.declaringInvokable = declaringInvokable;
	}
	
	public String getName() {
		return this.param.getName();
	}
	public Type<?> getType() {
		if(this.type == null) {
			this.type = typeby(this.param.getType());
		}
		return this.type;
	}
	public Generic getGeneric() {
		this.param.getParameterizedType();
		// TODO
		return null;
	}
	
	public Invokable getDeclaringInvokable() {
		return this.declaringInvokable;
	}
	
	@Override
	public Note.Stream notes() {
		if(this.notes == null) {
			this.notes = new Note.Stream(this.param.getAnnotations());
		}
		return this.notes;
	}
	@Override
	public Note.Stream declaredNotes() {
		if(this.declaredNotes == null) {
			this.declaredNotes = new Note.Stream(this.param.getDeclaredAnnotations());
		}
		return this.declaredNotes;
	}
	
	public boolean isFinal() {
		return Modifier.isFinal(this.param.getModifiers());
	}
	public boolean isImplicit() {
		return this.param.isImplicit();
	}
	public boolean isSynthetic() {
		return this.param.isSynthetic();
	}
	public boolean isVariadic() {
		return this.param.isVarArgs();
	}
	
	@Override
	public int hashCode() {
		return this.param.hashCode();
	}
	@Override
	public boolean equals(Object object) {
		if(object instanceof Parameter) {
			Parameter param = (Parameter) object;
			return param.param.equals(this.param);
		}
		return false;
	}
	@Override
	public String toString() {
		if(string == null) {
			string = "";
			for(Annotation note : this.notes()) {
				string += note.toString() + " ";
			}
			string += this.getType().getQualifiedName() + " " + this.getName();
		}
		return string;
	}
	
	public interface Streamable
	{
		public Parameter.Stream params();
	}
	public static class Stream extends Base implements Countable, Iterable<Parameter>
	{
		private final Parameter[]
			params;
		
		@Lazy
		private String
			string = null;
		
		Stream(Invokable declaringInvokable, java.lang.reflect.Parameter[] params) {
			this.params = map(params, e -> new Parameter(declaringInvokable, _5)).toNativeArray(Parameter.class);
		}
		
		public boolean has(String name) {
			for(Parameter param : this.params) {
				if(param.getName().equals(name)) {
					return true;
				}
			}
			return false;
		}
		public boolean has(String name, Type<?> type) {
			return this.has(name, type.toClass());
		}
		public boolean has(String name, Class<?> type) {
			return this.get(name).map(e -> e.type.isChildOf(type)).or(false);
		}
		
		public Option<Parameter> get(String name) {
			for(Parameter param : this.params) {
				if(param.getName().equals(name)) {
					return some(param);
				}
			}
			return none();
		}
		public Parameter get(int index) {
			if(index >= this.params.length || index < 0) {
				throw new UndefinedEntryException(index, 0, this.params.length - 1);
			}
			return this.params[index];
		}
		public Option<Parameter> get(String name, Type<?> type) {
			return this.get(name, type.toClass());
		}
		public Option<Parameter> get(String name, Class<?> type) {
			Option<Parameter> param = this.get(name);
			return param.isset() && param.get().getType().isChildOf(type) ? param : none();
		}
		
		@Override
		public int count() {
			return this.params.length;
		}
		@Override
		public Iterator<Parameter> iterator() {
			return UArray.iterator(this.params);
		}
		
		@Override
		public int hashCode() {
			return this.params.hashCode() ^ this.params.toString().hashCode();
		}
		@Override
		public boolean equals(Object object) {
			if (object instanceof Parameter.Stream) {
				Parameter.Stream stream = (Parameter.Stream) object;
				return UArray.equals(stream.params, this.params);
			}
			return false;
		}
		@Override
		public String toString() {
			if (this.string == null) {
				StringBuilder builder = new StringBuilder(typeby(Parameter.Stream.class).getQualifiedName()).append('(');
				for (int i = 0; i < this.params.length; ++i) {
					if(i != 0) {
						builder.append(", ");
					}
					builder.append(this.params[i]);
				}
				this.string = builder.append(')').toString();
			}
			return this.string;
		};
	}
}
