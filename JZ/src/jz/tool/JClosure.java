package jz.tool;

import jz.JObject;
import jz.iface.ICopyable;
import jz.iface.IValue;

public class JClosure<T> extends JObject implements IValue<T>, ICopyable<JClosure<T>>
{
//#Variables: Instance
//{	
	private T
		value;
//}
//#Constructor
//{
	public JClosure() {
		this(null);
	}
	public JClosure(T value) {
		this.value = value;
	}
//}
//#Getters
//{
	public T get() {
		return this.value;
	}
//}
//#Setters
//{
	public void set(T value) {
		this.value = value;
	}
//}
//#Copy
//{
	@Override
	public JClosure<T> copy() {
		return new JClosure<>(this.get());
	}
//}
}
