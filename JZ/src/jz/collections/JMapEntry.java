package jz.collections;

import jz.iface.ICopyable;

public interface JMapEntry<K, V> extends ICopyable<JMapEntry<K, V>>
{
//#Key
//{
	public K getKey();
	public K setKey(K key);
//}
//#Value
//{
	public V getVal();
	public V setVal(V val);
//}
//#Transform
//{
	public IPair<K, V> toPair();
//}
//#Copy
//{
	public JMapEntry<K, V> copy();
//}
}
