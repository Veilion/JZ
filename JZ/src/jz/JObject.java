package jz;

import jz.collections.JArray;
import jz.collections.JList;
import jz.collections.JMap;
import jz.collections.JMapEntry;
import jz.collections.TArray;
import jz.collections.TList;
import jz.collections.TMap;
import jz.collections.TMapEntry;
import jz.func.FConsumer;
import jz.iface.IReflectable;
import jz.query.IQueryable;
import jz.query.JStatement;
import jz.reflect.JType;
import jz.utils.UString;

public class JObject implements IReflectable<JObject>
{
//#IFace
//{
	@Override
	public JType<? extends JObject> getType() {
		return JType.forClass(this.getClass());
	}
//}
//#Default
//{
	@Override
	public String toString() {
		return super.toString();
		//TODO implement JObject.toString()
	}
//}
//#Key: Console
//{
	protected static void write(Object... objects) {
		JConsole.write(objects);
	}
	protected static void dump(Object object) {
		JConsole.dump(object);
	}
//}
//#Key: Manipulation
//{
	protected static String string(Object object) {
		return UString.toString(object);
	}
	protected static String string(String string, Object... objects) {
		return UString.insert(string, objects);
	}
	protected static <T> T notNull(T object) {
		if(null == object) 
			throw new IllegalArgumentException("argument cannot be null");
		return object;
	}
//}
//#Key: Collections
//{
	@SafeVarargs
	protected static <T> JArray<T> array(T... values) {
		return new TArray<>(values);
	}
	protected static <T> JArray<T> array(int size) {
		return new TArray<>(size);
	}
	@SafeVarargs
	protected static <T> JList<T> list(T... values) {
		return new TList<>(values);
	}
	protected static <K, V> JMapEntry<K, V> entry(K key, V val) {
		return new TMapEntry<K, V>(key, val);
	}
	@SafeVarargs
	protected static <K, V> JMap<K, V> map(JMapEntry<K, V>... entries) {
		return new TMap<>(entries);
	}
//}
//#Key: Query
//{
	protected static <T> void each(T[] objects, FConsumer<T> consumer) {
		for(T object : objects)
			consumer.consume(object);
	}
	protected static <T> void each(Iterable<T> objects, FConsumer<T> consumer) {
		for(T object : objects)
			consumer.consume(object);
	}
	protected static <T> JStatement<T, T, T> from(IQueryable<T> objects) {
		return JStatement.create();
	}
//}
}
