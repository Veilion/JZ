package jz.iface;

import jz.reflect.JType;

public interface IReflectable<T extends IReflectable<T>> 
{
	public JType<? extends T> getType();
}
