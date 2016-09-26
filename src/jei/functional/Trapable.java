package jei.functional;

public interface Trapable<T, R> 
{
	T trap(Consumer<? super R> consumer);
}
