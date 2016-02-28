package jz.func;

@FunctionalInterface
public interface FEval<T>
{
	public boolean eval(T object);
}
