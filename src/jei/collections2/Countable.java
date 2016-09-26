package jei.collections2;

public interface Countable 
{
	int count();
	default boolean isEmpty() {
		return this.count() == 0;
	}
}
