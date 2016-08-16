package jei.collections;

import jei.Base;
import jei.data.json.JSON;
import jei.functional.Consumer;
import jei.functional.Predicate;
import jei.functional.Producer;
import jei.functional.Producer2;

public abstract class AbstractStream<T> extends Base implements Stream<T>
{
	@Override
	public boolean includes(Predicate<T> predicate) {
		for(T value : this) {
			if(predicate.invoke(value)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean matches(Predicate<T> predicate) {
		for(T value : this) {
			if(!predicate.invoke(value)) {
				return false;
			}
		}
		return true;
	}
	@Override
	public int amountWhere(Predicate<T> predicate) {
		int amount = 0;
		for(T value : this) {
			if(predicate.invoke(value)) {
				++amount;
			}
		}
		return amount;
	}

	@Override
	public void each(Consumer<T> consumer) {
		for(T value : this) {
			consumer.invoke(value);
		}
	}
	@Override
	public Array<T> filter(Predicate<T> predicate) {
		Array<T> results = array();
		for(T value : this) {
			if(predicate.invoke(value)) {
				results.add(value);
			}
		}
		return results;
	}
	@Override
	public <R> Array<R> map(Producer<T, R> producer) {
		Array<R> results = empty(this.count());
		for(T value : this) {
			results.add(producer.invoke(value));
		}
		return results;
	}
	@Override
	public <R> R reduce(final R start, Producer2<R, T, R> producer) {
		R result = start;
		for(T value : this) {
			result = producer.invoke(result, value);
		}
		return result;
	}

	@Override
	public String toString() {
		int count = this.count();
		StringBuilder builder = new StringBuilder(format("Stream({0}) {", count));
		for(T value : this) {
			builder.append("\n    ").append(stringify(value));
		}
		return builder.append(count > 0 ? "\n}" : "}").toString();
	}
	
	@Override
	public String jsonify() {
		StringBuilder builder = new StringBuilder('[');
		boolean first = true;
		for(T value : this) {
			if(!first) {
				builder.append(", ");
			} else {
				first = false;
			}
			builder.append(JSON.parse(value));
		}
		return builder.append(']').toString();
	}
}
