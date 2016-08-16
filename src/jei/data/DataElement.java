package jei.data;

import jei.Copyable;
import jei.collections.CustomStream;
import jei.collections.Tuple.Tuple2;
import jei.functional.Predicate;

public interface DataElement extends Copyable<DataElement>, CustomStream<DataElement, DataElement>
{
	DataElement append(DataElement... elements);
	DataElement prepend(DataElement... elements);
	
	DataElement appendTo(DataElement element);
	DataElement prependTo(DataElement element);
	
	DataElement before(DataElement... elements);
	DataElement after(DataElement... elements);
	
	DataElement set(String name, Object value);
	DataElement set(@SuppressWarnings("unchecked") Tuple2<String, String>... attributes);
	DataElement set(Object value);
	DataElement set(DataElement elements);
	
	DataElement remove();
	DataElement eject(); 

	DataElement parent();
	DataElement childs();
	
	// merge this with ...elements
	DataElement cat(DataElement... elements);
	
	DataElement find(Predicate<DataElement> predicate);
	
	DataElement at(int index);
}
