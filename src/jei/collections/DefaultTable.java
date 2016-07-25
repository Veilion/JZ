package jei.collections;

import java.util.Iterator;

import jei.Base;
import jei.functional.Predicate;
import jei.simple.Option;

class DefaultTable<K, V> extends AbstractTable<K, V>
{
	private static final int
		DEFAULT_SIZE = 10;
	
	private Entry<K, V>[]
		table;
	
	private int 
		count = 0;

	private int
		capacity;
	
	DefaultTable() {
		this.capacity = DEFAULT_SIZE;
		this.table = this.createTable(this.capacity);
	}
	
	@SuppressWarnings("unchecked")
	private Entry<K, V>[] createTable(int size) {
		return new Entry[size];
	}
	private int indexOf(K key) {
		return Math.abs(hash(key)) % this.table.length;
	}
	private void resize() {
		Entry<K, V>[] oldTable = this.table; 
		this.table = createTable(this.table.length * 2);
		this.capacity *= 2;
		for(Entry<K, V> entry : oldTable) {
			entry.hash = indexOf(entry.key);
			indexEntry(entry);
		}
	}
	private void indexEntry(Entry<K, V> entry) {
		if(this.table[entry.hash] == null) {
			this.table[entry.hash] = entry;
		} else {
			Entry<K, V> last = this.table[entry.hash];
			while(last.next != null) {
				last = last.next;
			}
			last.next = entry;
		}
	}
	private void increase() {
		++this.count;
		if(this.count == this.capacity) {
			this.resize();
		}
	}
	
	@Override
	public Option<V> get(K key) {
		int index = indexOf(key);
		Entry<K, V> entry = this.table[index];
		while(entry != null) {
			if(equals(entry.key, key)) {
				return some(entry.value);
			}
			entry = entry.next;
		}
		return none();
	}
	@Override
	public Option<V> set(K key, V value) {
		int index = indexOf(key);
		Entry<K, V> entry = this.table[index];
		if(entry == null) {
			this.table[index] = new Entry<>(key, value, null, index);
			this.increase();
			return none();
		} 
		Entry<K, V> last = null;
		while(entry != null) {
			if(equals(entry.key, key)) {
				return some(entry.setValue(value));
			}
			last = entry;
			entry = entry.next;
		}
		last.next = new Entry<>(key, value, null, index);
		this.increase();
		return none();
	}
	@Override
	public boolean remove(K key) {
		int index = indexOf(key);
		Entry<K, V> entry = this.table[index];
		Entry<K, V> last = null;
		while(entry != null) {
			if(equals(entry.key, key)) {
				if(last == null) {
					this.table[index] = entry.next;
				} else {
					last.next = entry.next;
				}
				--this.count;
				return true;
			}
			last = entry;
			entry = entry.next;
		}
		return false;
	}
	@Override
	public boolean removeAll(V value) {
		boolean found = false;
		for(int i = 0; i < this.table.length; ++i) {
			Entry<K, V> entry = this.table[i];
			Entry<K, V> last = null;
			while(entry != null) {
				if(equals(entry.value, value)) {
					if(last == null) {
						this.table[i] = entry.next;
					} else {
						last.next = entry.next;
					}
					--this.count;
					found = true;
				} else {
					last = entry;
				}
				entry = entry.next;
			}
		}
		return found;
	}
	@Override
	public boolean removeWhere(Predicate<Table.Entry<K, V>> predicate) {
		boolean found = false;
		for(int i = 0; i < this.table.length; ++i) {
			Entry<K, V> entry = this.table[i];
			Entry<K, V> last = null;
			while(entry != null) {
				if(predicate.invoke(entry)) {
					if(last == null) {
						this.table[i] = entry.next;
					} else {
						last.next = entry.next;
					}
					--this.count;
					found = true;
				} else {
					last = entry;
				}
				entry = entry.next;
			}
		}
		return found;
	}
	
	@Override
	public int count() {
		return this.count;
	}
	@Override
	public Iterator<Table.Entry<K, V>> iterator() {
		final DefaultTable<K, V> that = this;
		return new Iterator<Table.Entry<K,V>>() {
			private int
				index = 0;
			private DefaultTable.Entry<K, V> 
				entry = null;

			@Override
			public boolean hasNext() {
				if(this.entry == null) {
					for(int i = index; i < that.table.length; ++i) {
						this.entry = that.table[i];
						if(this.entry == null) {
							continue;
						} else {
							index = i + 1;
							return true;
						}
					}
					return false;
				}
				return true;
			}
			@Override
			public Table.Entry<K, V> next() {
				DefaultTable.Entry<K, V> current = this.entry; 
				this.entry = this.entry.next;
				return current;
			}
		};
	}
	@Override
	public boolean add(K key, V value) {
		int index = indexOf(key);
		Entry<K, V> entry = this.table[index];
		if(entry == null) {
			this.table[index] = new Entry<>(key, value, null, index);
			this.increase();
			return true;
		} 
		Entry<K, V> last = null;
		while(entry != null) {
			if(equals(entry.key, key)) {
				return false;
			}
			last = entry;
			entry = entry.next;
		}
		last.next = new Entry<>(key, value, null, index);
		this.increase();
		return true;
	}

	@Override
	public void clear() {
		this.count = 0;
		this.table = this.createTable(DEFAULT_SIZE);
	}
	
	private static class Entry<K, V> extends Base implements Table.Entry<K, V> 
	{
		private final K
			key;
		
		private V
			value;
		
		private Entry<K, V>
			next;
		
		private int
			hash;
		
		private Entry(K key, V value, Entry<K, V> next, int hash) {
			this.key = key;
			this.value = value;
			this.next = next;
			this.hash = hash;
		}
		@Override
		public K getKey() {
			return this.key;
		}
		@Override
		public V getValue() {
			return this.value;
		}
		@Override
		public V setValue(V value) {
			V safe = this.value;
			this.value = value;
			return safe;
		}
		@Override
		public boolean equals(Object object) {
			if(this == object) {
				return true;
			}
			if(object instanceof Table.Entry) {
				Table.Entry<?, ?> entry = (Table.Entry<?, ?>) object;
				return equals(this.key, entry.getKey()) && equals(this.value, entry.getValue());
			}
			return false;
		}
		@Override
		public int hashCode() {
			return hash(this.key) ^ hash(this.value);
		}
		@Override
		public String toString() {
			return format("({0}: {1})", this.key, this.value);
		}
	}
}
