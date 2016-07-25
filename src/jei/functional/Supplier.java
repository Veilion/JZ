package jei.functional;

@FunctionalInterface
public interface Supplier<T> 
{
	T invoke();
}
