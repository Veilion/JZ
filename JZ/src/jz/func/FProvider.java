package jz.func;

@FunctionalInterface
public interface FProvider<T>
{
	public T provide();
}
