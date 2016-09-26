package jei.types;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import jei.Base;
import jei.annotation.Lazy;
import jei.collections.Countable;
import jei.collections.FinalArray;
import jei.error.ArgumentException;
import jei.error.BlockedAccessException;
import jei.error.IllegalInvocationError;
import jei.error.UnreachableError;
import jei.simple.Option;
import jei.utils.UArray;
import jei.utils.UError;

public class Constructor<T> extends Invokable
{
	private final java.lang.reflect.Constructor<T>
		cst;
	
	@Lazy
	private String
		string = null;
	
	private Constructor(java.lang.reflect.Constructor<T> cst) {
		super(cst);
		this.cst = cst;
	}
	
	public Type<T> getDeclaringType() {
		return typeBy(this.cst.getDeclaringClass());
	}
	public T invoke(Object... args) throws IllegalInvocationError {
		if(args.length != this.params().count()) {
			throw new ArgumentException("illegal number of arguments: expected " + this.params().count() + ", got " + args.length);
		}
		for(int i = 0; i < args.length; ++i) {
			Parameter param = this.params().get(i);
			if(!param.getType().isTypeOf(args[i])) {
				throw new ArgumentException("illegal type argument #" + i + ": excepted " + param.getType().getQualifiedName() + ", got " + args[i].getClass().getName());
			}
		}
		
		try {
			boolean isAccessible = this.cst.isAccessible();
			this.cst.setAccessible(true);
			T instance = this.cst.newInstance(args);
			this.cst.setAccessible(isAccessible);
			return instance;
		} catch (InstantiationException e) {
			throw new IllegalInvocationError("cannot invoke the constructor of the abstract class " + this.getDeclaringType().getQualifiedName());
		} catch (IllegalAccessException e) {
			throw new BlockedAccessException("no permission to invoke constructor of the class " + this.getDeclaringType().getQualifiedName());
		} catch (IllegalArgumentException e) {
			throw new UnreachableError(e);
		} catch (InvocationTargetException e) {
			throw UError.unchecked(e.getCause());
		}
	}

	@Override
	public String toString() {
		if (this.string == null) {
			StringBuilder builder = new StringBuilder(this.getVisibility().getKeyword());
			builder.append(this.getName()).append('(');
			int i = 0; 
			for (Parameter param : this.params()) {
				if(i != 0) {
					builder.append(", ");
				}
				builder.append(param.toString());
				++i;
			}
			builder.append(')');
			FinalArray<Type<? extends Throwable>> throwings = this.getThrowingTypes();
			if (!throwings.isEmpty()) {
				builder.append(" throws ");
				for (i = 0; i < throwings.count(); ++i) {
					if(i != 0) {
						builder.append(", ");
					}
					builder.append(throwings.get(i).getQualifiedName());
				}
			}
			this.string = builder.toString().trim();
		}
		return this.string;
	}
	
	public static class Stream<T> extends Base implements Countable, Iterable<Constructor<?>>
	{
		private final Constructor<T>[]
			csts;
		
		@Lazy
		private String
			string = null;
		
		Stream(java.lang.reflect.Constructor<T>[] csts) {
			@SuppressWarnings("unchecked")
			Constructor<T>[] _csts = (Constructor<T>[]) new Constructor[csts.length];
			this.csts = _csts;
			for (int i = 0; i < csts.length; ++i) {
				this.csts[i] = new Constructor<>(csts[i]);
			}
		}
		
		public boolean has(Type<?>... argTypes) {
			return this.get(argTypes).isset();
		}
		public boolean has(Class<?>... argTypes) {
			return this.get(argTypes).isset();
		}
		
		public Option<Constructor<T>> getEmpty() {
			return this.get(new Type<?>[] {});
		}
		public Option<Constructor<T>> get(Type<?>... argTypes) {
			CST_LOOP: for (Constructor<T> cst : this.csts) {
				if (cst.params().count() == argTypes.length) {
					for (int i = 0; i < argTypes.length; ++i) {
						if (!equals(argTypes[i], cst.params().get(i).getType())) {
							continue CST_LOOP;
						}
					}
					return some(cst);
				}
			}
			return none();
		}
		public Option<Constructor<T>> get(Class<?>... argTypes) {
			CST_LOOP: for (Constructor<T> cst : this.csts) {
				if (cst.params().count() == argTypes.length) {
					for (int i = 0; i < argTypes.length; ++i) {
						if (!equals(argTypes[i], cst.params().get(i).getType().toClass())) {
							continue CST_LOOP;
						}
					}
					return some(cst);
				}
			}
			return none();
		}
		
		@Override
		public int count() {
			return this.csts.length;
		}
		@Override
		public Iterator<Constructor<?>> iterator() {
			return UArray.iterator(this.csts);
		}
		
		@Override
		public boolean equals(Object object) {
			if(object instanceof Constructor.Stream) {
				
			}
			return false;
		}
		@Override
		public String toString() {
			if (this.string == null) {
				StringBuilder builder = new StringBuilder(typeOf(this).getQualifiedName());
				builder.append('(').append(this.count()).append(") {");
				for(Constructor<?> cst : this.csts) {
					builder.append("\n    ").append(cst.toString());
				}
				if(this.csts.length > 0) {
					builder.append('\n');
				}
				builder.append('}');
				this.string = builder.toString();
			}
			return this.string;
		}
	}
}
