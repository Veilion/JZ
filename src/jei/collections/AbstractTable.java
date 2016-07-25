package jei.collections;

import java.util.Iterator;

import jei.functional.Predicate;
import jei.json.JSON;

public abstract class AbstractTable<K, V> extends AbstractStream<Table.Entry<K, V>> implements Table<K, V>
{
	private Stream<K>
		keyStream = null;
	
	private Stream<V>
		valueStream = null;
	
	private Set<Table.Entry<K, V>>
		entrySet = null;
	
	@Override
	public V getOr(K key, V or) {
		return this.get(key).or(or);
	}
	@Override
	public boolean setAll(V currValue, V newValue) {
		boolean found = false;
		for(Table.Entry<K, V> entry : this) {
			if(equals(entry.getValue(), currValue)) {
				entry.setValue(newValue);
				found = true;
			}
		}
		return found;
	}
	@Override
	public boolean setWhere(V value, Predicate<Table.Entry<K, V>> predicate) {
		boolean found = false;
		for(Table.Entry<K, V> entry : this) {
			if(predicate.invoke(entry)) {
				entry.setValue(value);
				found = true;
			}
		}
		return found;
	}
	@Override
	public boolean has(K key, V value) {
		return this.get(key).map(e -> equals(e, value)).or(false);
	}
	@Override
	public boolean hasKey(K key) {
		return this.get(key).isset();
	}
	@Override
	public boolean hasValue(V value) {
		for(Table.Entry<K, V> entry : this) {
			if(equals(entry.getValue(), value)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int amountOf(V value) {
		int amount = 0;
		for(Table.Entry<K, V> entry : this) {
			if(equals(entry.getValue(), value)) {
				++amount;
			}
		}
		return amount;
	}

	@Override
	public Stream<K> keys() {
		if(this.keyStream == null) {
			final AbstractTable<K, V> that = this;
			this.keyStream = new AbstractStream<K>() {
				@Override
				public int count() {
					return that.count();
				}
				@Override
				public Iterator<K> iterator() {
					Iterator<Table.Entry<K, V>> entryIterator = that.iterator();
					return new Iterator<K>() {
						@Override
						public boolean hasNext() {
							return entryIterator.hasNext();
						}
						@Override
						public K next() {
							return entryIterator.next().getKey();
						}
					};
				}
			};
		}
		return this.keyStream;
	}
	@Override
	public Stream<V> values() {
		if(this.valueStream == null) {
			final AbstractTable<K, V> that = this;
			this.valueStream = new AbstractStream<V>() {
				@Override
				public int count() {
					return that.count();
				}
				@Override
				public Iterator<V> iterator() {
					Iterator<Table.Entry<K, V>> entryIterator = that.iterator();
					return new Iterator<V>() {
						@Override
						public boolean hasNext() {
							return entryIterator.hasNext();
						}
						@Override
						public V next() {
							return entryIterator.next().getValue();
						}
					};
				}
			};
		}
		return this.valueStream;
	}

	@Override
	public Set<Table.Entry<K, V>> entries() {
		if(this.entrySet == null) {
			final AbstractTable<K, V> that = this;
			this.entrySet = new AbstractSet<Table.Entry<K,V>>() {
				@Override
				public boolean add(Table.Entry<K, V> value) {
					return that.add(value.getKey(), value.getValue());
				}
				@Override
				public boolean removeWhere(Predicate<Table.Entry<K, V>> predicate) {
					return that.removeWhere(predicate);
				}
				@Override
				public void clear() {
					that.clear();
				}
				@Override
				public int count() {
					return that.count();
				}
				@Override
				public Iterator<Table.Entry<K, V>> iterator() {
					return that.iterator();
				}
			};
		}
		return this.entrySet;
	}
	
	@Override
	public Array<K> keyArray() {
		Array<K> results = empty(this.count());
		for(Table.Entry<K, V> entry : this) {
			results.add(entry.getKey());
		}
		return results;
	}
	@Override
	public Array<V> valueArray() {
		Array<V> results = empty(this.count());
		for(Table.Entry<K, V> entry : this) {
			results.add(entry.getValue());
		}
		return results;
	}
	
	@Override
	public String toString() {
		int count = count();
		StringBuilder builder = new StringBuilder(format("Table({0}) {", count));
		String[][] entries = new String[count][];
		int keyLength = 0;
		int strIndex = 0;
		for(Table.Entry<K, V> entry : this) {
			String keyString = stringify(entry.getKey()).replace("\n", "    \n");
			if(keyString.length() > keyLength) {
				keyLength = keyString.length();
			}
			entries[strIndex++] = new String[] {keyString, stringify(entry.getValue()).replace("\n", "    \n")};
		}
		String format = "\n    %-" + (keyLength + 2) + "s";
		for(int i = 0; i < entries.length; ++i) {
			if(i != 0) {
				builder.append(',');
			}
			builder.append(String.format(format, entries[i][0] + ": ")).append(entries[i][1]);
		}
		return builder.append(count == 0 ? '}' : "\n}").toString();
	}
	
	@Override
	public String jsonify() {
		StringBuilder builder = new StringBuilder("{");
		boolean first = true;
		for(Table.Entry<K, V> entry : this) {
			if(!first) {
				builder.append(", ");
			} else {
				first = false;
			}
			builder.append(JSON.parse(entry.getKey())).append(": ").append(JSON.parse(entry.getValue()));
		}
		return builder.append('}').toString();
	}
}
