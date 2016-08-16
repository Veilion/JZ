package jei.collections;

import java.util.Iterator;

import jei.functional.Consumer;
import jei.functional.Predicate;
import jei.functional.Producer;
import jei.functional.Producer2;
import jei.simple.Option;

public interface FinalArray<T> extends Stream<T>
{
	public static <T> FinalArray<T> delegate(FinalArray<T> array) {
		return new FinalArray<T>() {
			@Override
			public boolean includes(Predicate<T> predicate) {
				return array.includes(predicate);
			}
			@Override
			public boolean matches(Predicate<T> predicate) {
				return array.matches(predicate);
			}
			@Override
			public int amountWhere(Predicate<T> predicate) {
				return array.amountWhere(predicate);
			}
			@Override
			public void each(Consumer<T> consumer) {
				array.each(consumer);
			}
			@Override
			public Array<T> filter(Predicate<T> predicate) {
				return array.filter(predicate);
			}
			@Override
			public <R> Array<R> map(Producer<T, R> producer) {
				return array.map(producer);
			}
			@Override
			public <R> R reduce(R start, Producer2<R, T, R> producer) {
				return array.reduce(start, producer);
			}
			@Override
			public int count() {
				return array.count();
			}
			@Override
			public Iterator<T> iterator() {
				return array.iterator();
			}
			@Override
			public String jsonify() {
				return array.jsonify();
			}
			@Override
			public T get(int index) {
				return array.get(index);
			}
			@Override
			public Array<T> getAll(int... indices) {
				return array.getAll(indices);
			}
			@Override
			public Array<T> getRange(int start, int end) {
				return array.getRange(start, end);
			}
			@Override
			public int amountOf(T entry) {
				return array.amountOf(entry);
			}
			@Override
			public boolean has(T entry) {
				return array.has(entry);
			}
			@Override
			public Option<Integer> firstIndexOf(T entry) {
				return array.firstIndexOf(entry);
			}
			@Override
			public Option<Integer> firstIndexWhere(Predicate<T> predicate) {
				return array.firstIndexWhere(predicate);
			}
			@Override
			public Option<Integer> lastIndexOf(T entry) {
				return array.lastIndexOf(entry);
			}
			@Override
			public Option<Integer> lastIndexWhere(Predicate<T> predicate) {
				return array.lastIndexWhere(predicate);
			}
			@Override
			public Option<T> firstWhere(Predicate<T> predicate) {
				return array.firstWhere(predicate);
			}
			@Override
			public Option<T> lastWhere(Predicate<T> predicate) {
				return array.lastWhere(predicate);
			}
			@Override
			public T first() {
				return array.first();
			}
			@Override
			public T last() {
				return array.last();
			}
			@Override
			public T[] toNativeArray(Class<T> clazz) {
				return array.toNativeArray(clazz);
			}
			@Override
			public int hashCode() {
				return array.hashCode();
			}
			@Override
			public String toString() {
				return "final " + array.toString();
			}
			@Override
			public boolean equals(Object object) {
				return array.equals(object);
			}
		};
	}
	
	T get(int index);
	Array<T> getAll(int... indices);
	Array<T> getRange(int start, int end);
	
	int amountOf(T entry);
	boolean has(T entry);
	
	Option<Integer> firstIndexOf(T entry);
	Option<Integer> firstIndexWhere(Predicate<T> predicate);
	Option<Integer> lastIndexOf(T entry);
	Option<Integer> lastIndexWhere(Predicate<T> predicate);
	
	Option<T> firstWhere(Predicate<T> predicate);
	Option<T> lastWhere(Predicate<T> predicate);
	
	T first();
	T last();
	
	T[] toNativeArray(Class<T> clazz);
}
