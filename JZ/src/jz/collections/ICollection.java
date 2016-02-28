package jz.collections;

import jz.func.FConsumer;
import jz.iface.ICopyable;
import jz.iface.ISizeable;

public interface ICollection<T> extends Iterable<T>, ISizeable, ICopyable<ICollection<T>>
{
//#Query
//{
	public default void each(FConsumer<T> consumer) {
		for(T object : this) 
			consumer.consume(object);
	}
//}
}
