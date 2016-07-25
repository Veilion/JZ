package jz.collections;

import java.util.Map;

import jz.func.FConsumer;
import jz.iface.IDumpable;
import jz.utils.UString;

public interface JMap<K, V> extends ICollection<JMapEntry<K, V>>, IDumpable 
{
//#Get
//{
	public V get(K key);
	public V getOr(K key, V or);
	public JArray<K> getAll(V val);
//}
//#Set
//{
	public V set(K key, V val);
	public int setAll(V currVal, V newVal);
//}
//#Contains
//{
	public boolean contains(K key, V val);
	public boolean containsVal(V val);
	public boolean containsKey(K key);
//}
//#Remove
//{
	public boolean remove(K key);
	public int removeAll(V val);
//}
//#Query
//{
	public void eachKey(FConsumer<K> consumer);
	public void eachVal(FConsumer<V> consumer);
//}
//#Sub
//{
	public JArray<K> subKeys();
	public JArray<V> subVals();
//}
//#Transform
//{
	public Map<K, V> toNativeMap();
//}
//#Copy
//{
	@Override
	public JMap<K, V> copy();
//}
//#Dump
//{
	@Override
	default String createDump() {
		String header = this.getClass().getName();
		String content = "";
		for(JMapEntry<K, V> current : this) {
			content += UString.insert("    #{0} => {1}\n", current.getVal(), current.getKey());
		}
		return UString.insert("{0} ({1}) {{2}" + content + "}", header, this.getSize(), this.getSize() > 0 ? "\n" : "");
	}
//}
}
