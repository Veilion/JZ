package jei.collections;

public abstract class AbstractSet<T> extends AbstractStream<T> implements Set<T>
{
	@Override
	public void addAll(T[] values) {
		for(T value : values) {
			this.add(value);
		}
	}
	@Override
	public void addAll(Iterable<T> values) {
		for(T value : values) {
			this.add(value);
		}
	}
	@Override
	public boolean remove(T entry) {
		return this.removeWhere(e -> equals(entry, e));
	}
	@Override
	public boolean has(T entry) {
		for(T value : this) {
			if(equals(value, entry)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public Array<T> toArray() {
		Array<T> results = empty(this.count());
		for(T value : this) {
			results.add(value);
		}
		return results;
	}
	@Override
	public String toString() {
		int count = this.count();
		StringBuilder builder = new StringBuilder(format("Set({0}) {", count));
		boolean first = true;
		for(T value : this) {
			if(!first) {
				builder.append(',');
			} else {
				first = false;
			}
			builder.append("\n    ").append(stringify(value));
		}
		return builder.append(count > 0 ? "\n}" : "}").toString();
	}
}
