package jei.collections;

public interface Archive<K, V> 
{
	Set<V> get(K key);
	boolean add(K key, V value);
	
	
	
	interface Entry<K, V> 
	{
		K getKey();
		Set<V> getValues();
		void addValue(V value);
	}
}
