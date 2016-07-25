package jz.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jz.JObject;
import jz.func.FConsumer;

public class TMap<K, V> extends JObject implements JMap<K, V>
{
//#Variables: Instance
//{
	private final V
		nfVal;
	
	private JList<JMapEntry<K, V>>
		entries = list();
//}
//#Constructor
//{
	public TMap() {
		this.nfVal = null;
	}
	public TMap(V nfVal) {
		this.nfVal = nfVal;
	}
	@SafeVarargs
	public TMap(JMapEntry<K, V>... entries) {
		this.nfVal = null;
		this.entries.add(entries);
	}
//}
//#Get
//{
	public V get(K key) {
		return this.getOr(key, this.nfVal);
	}
	public V getOr(K key, V or) {
		JMapEntry<K, V> entry = this.getEntry(key);
		if(null == entry) 
			return or;
		return entry.getVal();
	}
	public JArray<K> getAll(V val) {
		JList<K> keys = list();
		if(null == val) {
			for(int i = 0; i < this.entries.getSize(); ++i) {
				JMapEntry<K, V> current = this.entries.get(i);
				if(null == current.getVal())
					keys.add(current.getKey());
			}
		} else {
			for(int i = 0; i < this.entries.getSize(); ++i) {
				JMapEntry<K, V> current = this.entries.get(i);
				if(null != current.getVal() && val.equals(current.getVal()))
					keys.add(current.getKey());
			}
		}
		return keys.toArray();
	}
	private JMapEntry<K, V> getEntry(K key) {
		if(null == key) {
			for(int i = 0; i < this.entries.getSize(); ++i) {
				JMapEntry<K, V> current = this.entries.get(i);
				if(null == current.getKey())
					return current;
			}
		} else {
			for(int i = 0; i < this.entries.getSize(); ++i) {
				JMapEntry<K, V> current = this.entries.get(i);
				if(null != current.getKey() && key.equals(current.getKey()))
					return current;
			}
		}
		return null;
	}
//}
//#Set
//{
	public V set(K key, V val) {
		JMapEntry<K, V> entry = this.getEntry(key);
		if(null == entry) {
			entry = entry(key, val);
			this.entries.add(entry);
			return this.nfVal;
		} else {
			V before = entry.getVal();
			entry.setVal(val);
			return before;
		}
	}
	public int setAll(V currVal, V newVal) {
		int amount = 0;
		if(null == currVal) {
			for(int i = 0; i < this.entries.getSize(); ++i) {
				JMapEntry<K, V> current = this.entries.get(i);
				if(null == current.getVal()) {
					current.setVal(newVal);
					++amount;
				}
			}
		} else {
			for(int i = 0; i < this.entries.getSize(); ++i) {
				JMapEntry<K, V> current = this.entries.get(i);
				if(null != current.getVal() && currVal.equals(current.getVal())) {
					current.setVal(newVal);
					++amount;
				}
			}
		}
		return amount;
	}
//}
//#Contains
//{
	@Override
	public boolean contains(K key, V val) {
		JMapEntry<K, V> entry = this.getEntry(key);
		if(null != entry)
			if(null == val) 
				return entry.getVal() == null;
			else
				return entry.getVal() != null && val.equals(entry.getVal());
		return false;
	}
	@Override
	public boolean containsVal(V val) {
		if(null == val) {
			for(JMapEntry<K, V> entry : this)
				if(null == entry.getVal())
					return true;
		} else {
			for(JMapEntry<K, V> entry : this)
				if(null != entry.getVal() && val.equals(entry.getVal()))
					return true;
		}
		return false;
	}
	@Override
	public boolean containsKey(K key) {
		if(null == key) {
			for(JMapEntry<K, V> entry : this)
				if(null == entry.getKey())
					return true;
		} else {
			for(JMapEntry<K, V> entry : this)
				if(null != entry.getKey() && key.equals(entry.getKey()))
					return true;
		}
		return false;
	}
//}
//#Remove
//{
	public boolean remove(K key) {
		if(null == key) {
			for(int i = 0; i < this.entries.getSize(); ++i) {
				if(null == this.entries.get(i).getKey()) {
					this.entries.remove(i);
					return true;
				}
			}
		} else {
			for(int i = 0; i < this.entries.getSize(); ++i) {
				JMapEntry<K, V> current = this.entries.get(i);
				if(null != current.getKey() && key.equals(current.getKey())) {
					this.entries.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	public int removeAll(V val) {
		int amount = 0;
		if(null == val) {
			for(int i = this.getSize() - 1; i >= 0; ++i) {
				JMapEntry<K, V> current = this.entries.get(i);
				if(null == current.getVal()) {
					this.entries.remove(i);
					++amount;
				}
			}
		} else {
			for(int i = this.getSize() - 1; i >= 0; ++i) {
				JMapEntry<K, V> current = this.entries.get(i);
				if(null != current.getVal() && val.equals(current.getVal())) {
					this.entries.remove(i);
					++amount;
				}
			}
		}
		return amount;
	}
//}
//#Query
//{
	public void eachKey(FConsumer<K> consumer) {
		for(JMapEntry<K, V> entry : this.entries) 
			consumer.consume(entry.getKey());
	}
	public void eachVal(FConsumer<V> consumer) {
		for(JMapEntry<K, V> entry : this.entries) 
			consumer.consume(entry.getVal());
	}
//}
//#Size
//{
	@Override
	public void clear() {
		this.entries.clear();
	}
	@Override
	public int getSize() {
		return this.entries.getSize();
	}
//}
//#Sub
//{
	public JArray<K> subKeys() {
		JArray<K> array = array(this.getSize());
		int i = 0; for(JMapEntry<K, V> entry : this.entries)
			array.set(i++, entry.getKey());
		return array;
	}
	public JArray<V> subVals() {
		JArray<V> array = array(this.getSize());
		int i = 0; for(JMapEntry<K, V> entry : this.entries)
			array.set(i++, entry.getVal());
		return array;
	}
//}
//#Transform
//{
	public Map<K, V> toNativeMap() {
		Map<K, V> nmap = new HashMap<>(this.getSize());
		for(JMapEntry<K, V> entry : this.entries)
			nmap.put(entry.getKey(), entry.getVal());
		return nmap;
	}
//}
//#Copy
//{
	@Override
	public TMap<K, V> copy() {
		TMap<K, V> map = new TMap<>(this.nfVal);
		for(JMapEntry<K, V> entry : this.entries)
			map.set(entry.getKey(), entry.getVal());
		return map;
	}
//}
//#Iterate
//{
	@Override
	public Iterator<JMapEntry<K, V>> iterator() {
		return this.entries.iterator();
	}
//}
}
