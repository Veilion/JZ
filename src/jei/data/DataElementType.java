package jei.data;

import jei.collections.Tuple.Tuple2;
import jei.functional.Predicate;

public interface DataElementType<Self extends DataElementType<Self>> extends DataElement
{
	Self append(DataElement... elements);
	Self prepend(DataElement... elements);
	
	Self appendTo(DataElement element);
	Self prependTo(DataElement element);
	
	Self before(DataElement... elements);
	Self after(DataElement... elements);
	
	Self set(String name, Object value);
	Self set(@SuppressWarnings("unchecked") Tuple2<String, String>... attributes);
	Self set(Object value);
	Self set(DataElement elements);
	
	Self remove();
	Self eject(); 

	Self parent();
	Self childs();
	
	// merge this with ...elements
	Self cat(DataElement... elements);
	
	Self find(Predicate<DataElement> predicate);
	
	Self at(int index);

	Self copy();

	Self filter(Predicate<DataElement> predicate);
}
