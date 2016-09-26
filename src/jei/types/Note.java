package jei.types;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Iterator;

import jei.Base;
import jei.annotation.Lazy;
import jei.annotation.Uninhabited;
import jei.collections.Array;
import jei.collections.Countable;
import jei.functional.Predicate;
import jei.simple.Option;
import jei.utils.UArray;

@Uninhabited
public enum Note
{
	;
	public interface Streamable 
	{
		Note.Stream notes();
		Note.Stream declaredNotes();
	}
	public static class Stream extends Base implements Countable, Iterable<Annotation>
	{
		private final Annotation[]
			notes;
		
		@Lazy
		private String 
			string = null;
		
		Stream(Annotation[] notes) {
			this.notes = UArray.copy(notes);
		}
		
		public boolean has(Type<? extends Annotation> noteType) {
			return this.has(noteType.toClass());
		}
		public boolean has(Class<? extends Annotation> noteType) {
			for(Annotation note : this.notes) {
				if(noteType.isInstance(note)) {
					return true;
				}
			}
			return false;
		}
		public boolean hasWhere(Predicate<Annotation> predicate) {
			for(Annotation note : this.notes) {
				if(predicate.call(note)) {
					return true;
				}
			}
			return false;
		}
		
		public <T extends Annotation> Array<T> of(Type<T> noteType) {
			return this.of(noteType.toClass());
		}
		public <T extends Annotation> Array<T> of(Class<T> noteType) {
			Array<T> result = array();
			for(Annotation note : this.notes) {
				if(noteType.isInstance(note)) {
					@SuppressWarnings("unchecked")
					T resultPart = (T) note;
					result.add(resultPart);
				}
			}
			return result;
		}
		public <T extends Annotation> Option<T> oneOf(Type<T> noteType) {
			return this.oneOf(noteType.toClass());
		}
		public <T extends Annotation> Option<T> oneOf(Class<T> noteType) {
			for(Annotation note : this.notes) {
				if(noteType.isInstance(note)) {
					@SuppressWarnings("unchecked")
					T result = (T) note;
					return some(result);
				}
			}
			return none();
		}
		
		public Array<Annotation> where(Predicate<Annotation> predicate) {
			Array<Annotation> result = array();
			for(Annotation note : this.notes) {
				if(predicate.call(note)) {
					result.add(note);
				}
			}
			return result;
		}
		public Option<Annotation> oneWhere(Predicate<Annotation> predicate) {
			for(Annotation note : this.notes) {
				if(predicate.call(note)) {
					return some(note);
				}
			}
			return none();
		}
		
		@Override
		public int count() {
			return this.notes.length;
		}
		@Override
		public Iterator<Annotation> iterator() {
			return UArray.iterator(this.notes);
		}
		public Array<Annotation> toArray() {
			return array(this.notes);
		}
		
		@Override
		public int hashCode() {
			return notes.length ^ Arrays.toString(this.notes).hashCode();
		}
		@Override
		public boolean equals(Object object) {
			if(object instanceof Note.Stream) {
				Note.Stream stream = (Note.Stream) object;
				return UArray.equals(stream.notes, this.notes);
			}
			return false;
		}
		@Override
		public String toString() {
			if(this.string == null) {
				StringBuilder builder = new StringBuilder(typeof(this).getQualifiedName());
				builder.append("(").append(this.count()).append(") {");
				for(Annotation note : this.notes) {
					builder.append("\n    ").append(note.toString());
				}
				if(this.count() > 0) {
					builder.append('\n');
				}
				builder.append('}');
				this.string = builder.toString();
			}
			return this.string;
		}
	}
}
