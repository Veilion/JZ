package jei.types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;

import jei.Base;
import jei.annotation.Lazy;
import jei.collections.Array;
import jei.error.UnreachableError;

public class Generic extends Base
{
	private final TypeVariable<?>
		variable;
	
	@Lazy
	private Type<?>[]
		bounds = null;
	
	Generic(TypeVariable<?> variable) {
		this.variable = variable;
	}
	private Type<?> getTypeOf(ParameterizedType type) {
		print(type.getRawType().getClass());
		return null;
	}
	
	public String getName() {
		return this.variable.getName();
	}
	public Array<Type<?>> getBounds() {
		if(this.bounds == null) {
			java.lang.reflect.Type[] jBounds = variable.getBounds();
			this.bounds = new Type<?>[jBounds.length];
			for(int i = 0; i < jBounds.length; ++i) {
				if(jBounds[i] instanceof Class) {
					this.bounds[i] = typeby((Class<?>)jBounds[i]);
				} else if(jBounds[i] instanceof ParameterizedType) {
					this.bounds[i] = typeby((Class<?>)((ParameterizedType)jBounds[i]).getRawType());
				} else {
					throw new UnreachableError("yet unchecked type found: " + jBounds[i].getClass().getCanonicalName());
				}
			}
		}
		return array(this.bounds);
	}
	
	@Override
	public int hashCode() {
		return this.variable.hashCode();
	}
	@Override
	public boolean equals(Object object) {
		if(object instanceof Generic) {
			Generic generic = (Generic) object;
			return generic.variable.equals(this.variable);
		}
		return false;
	}
	@Override
	public String toString() {
		String string = this.getName();
		Array<Type<?>> bounds = this.getBounds();
		if(!bounds.isEmpty()) {
			string += " extends " + bounds.first().getQualifiedName();
			for(int i = 1; i < bounds.count(); ++i) {
				string += " & " + bounds.get(i).getQualifiedName();
			}
		}
		return string;
	}
}
