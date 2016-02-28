package jz.collections;

import jz.JObject;

public class TMapEntry<K, V> extends JObject implements JMapEntry<K, V>
{
//#Variables: Instance
//{
	private K
		key;
	private V
		val;
//}
//#Constructor
//{
	public TMapEntry() {
		this(null, null);
	}
	public TMapEntry(K key, V val) {
		this.key = key;
		this.val = val;
	}
//}
//#Key
//{
	@Override
	public K getKey() {
		return this.key;
	}
	@Override
	public K setKey(K key) {
		K before = this.getKey();
		this.key = key;
		return before;
	}
//}
//#Value
//{
	@Override
	public V getVal() {
		return this.val;
	}
	@Override
	public V setVal(V val) {
		V before = this.getVal();
		this.val = val;
		return before;
	}
//}
//#Transform
//{
	@Override
	public JPair<K, V> toPair() {
		return new JPair<>(this.getKey(), this.getVal());
	}
//}
//#Copy
//{
	@Override
	public TMapEntry<K, V> copy() {
		return new TMapEntry<>(this.getKey(), this.getVal());
	}
//}
}
