package jei.collections;

import jei.collections.Tuple.Tuple2;
import jei.functional.Predicate;
import jei.simple.Option;

public interface Table<K, V> extends Stream<Table.Entry<K, V>>
{
	public static <K, V> Table<K, V> empty() {
		return new DefaultTable<>();
	}
	@SafeVarargs
	public static <K, V> Table<K, V> of(Tuple2<K, V>... values) {
		Table<K, V> hash = new DefaultTable<>();
		for(Tuple2<K, V> value : values) {
			hash.set(value._1, value._2);
		}
		return hash;
	}
	
	Option<V> get(K key);
	V getOr(K key, V or);
	
	boolean add(K key, V value);
	
	Option<V> set(K key, V value);
	boolean setAll(V currValue, V newValue);
	boolean setWhere(V value, Predicate<Table.Entry<K, V>> predicate);
	
	boolean has(K key, V value);
	boolean hasKey(K key);
	boolean hasValue(V value);
	
	boolean remove(K key);
	boolean removeAll(V value);
	boolean removeWhere(Predicate<Table.Entry<K, V>> predicate);
	
	int amountOf(V value);
	
	Stream<K> keys();
	Stream<V> values();
	Set<Table.Entry<K, V>> entries();
	
	Array<K> keyArray();
	Array<V> valueArray();
	
	void clear();
	
	interface Entry<K, V> {
		K getKey();
		V getValue();
		V setValue(V value);
	}
}
