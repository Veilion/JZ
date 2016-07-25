package jz.reflect;

import jz.JObject;
import jz.collections.JMap;
import jz.error.UnknownTypeException;
import jz.note.Initializer;

public class JType<T> extends JObject 
{
//#Variables
//{
	private final Class<T>
		clazz;
	
	private final ClassType
		type;
//}
//#Constructor
//{
	private JType(Class<T> clazz) {
		this.clazz = clazz;
		this.type = this.initClassType();
	}
//}
//#Inheritance
//{
	public boolean isChildOf(JType<?> type) {
		return type.toClass().isAssignableFrom(this.toClass());
	}
	public boolean isChildOf(Class<?> clazz) {
		return clazz.isAssignableFrom(this.toClass());
	}
	public boolean isParentOf(JType<?> type) {
		return this.toClass().isAssignableFrom(type.toClass());
	}
	public boolean isParentOf(Class<?> clazz) {
		return this.toClass().isAssignableFrom(clazz);
	}
	public boolean is(Class<?> clazz) {
		return this.toClass() == clazz;
	}
	public boolean isTypeOf(Object object) {
		if(null == object)
			return false;
		else 
			return this.toClass().isInstance(object);
	}
//}
//#Details
//{
	public boolean isPrimitive() {
		return this.clazz.isPrimitive();
	}
	public boolean isArray() {
		return this.clazz.isArray();
	}
	public boolean isAnonymous() {
		//clazz implementation on initialization (ex. [new Bar() {}]);
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
//}
//#Class Type
//{
	public ClassType getClassType() {
		return this.type;
	}
	public boolean isClass() {
		return ClassType.CLASS == this.type;
	}
	public boolean isIFace() {
		return ClassType.IFACE == this.type;
	}
	public boolean isEnum() {
		return ClassType.ENUM == this.type;
	}
	public boolean isAnnotation() {
		return ClassType.ANNOTATION == this.type;
	}
//}
//#Transform
//{
	public Class<T> toClass() {
		return this.clazz;
	}
//}
//#Initializers
//{
	@Initializer
	private ClassType initClassType() {
		return null;
		//TODO imp JType.initClassType()
	}
//}
//#Static: Variables
//{
	private static final JMap<Class<?>, JType<?>>
		INSTANCES = map();
//}
//#Static: Factory
//{
	public static <T> JType<T> forClass(Class<T> clazz) {
		@SuppressWarnings("unchecked") JType<T> jType = (JType<T>) INSTANCES.get(clazz);
		if(null == jType) {
			jType = new JType<>(clazz);
			INSTANCES.set(clazz, jType);
		}
		return jType;
	}
	public static JType<?> forName(String name) {
		if(!name.contains(".")) {
			switch(name) {
				case "byte": 
					return forClass(byte.class);
				case "short": 
					return forClass(short.class);
				case "int": 
					return forClass(int.class);
				case "long": 
					return forClass(long.class);
				case "float": 
					return forClass(float.class);
				case "double": 
					return forClass(double.class);
				case "char": 
					return forClass(char.class);
				case "boolean": 
					return forClass(boolean.class);
				case "void": 
					return forClass(void.class);
			}
		}
		try {
			return forClass(Class.forName(name));
		} catch(ClassNotFoundException e) {
			throw new UnknownTypeException(string("no type found at '{0}'", name));
		}
		
	}
//}
//#Type: Enum
//{
	public static enum ClassType 
	{
		CLASS, IFACE, ENUM, ANNOTATION,;
	}
//}
}
