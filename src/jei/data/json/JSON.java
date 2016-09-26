package jei.data.json;

import jei.Base;

public class JSON extends Base
{
	public static String parse(Object object) {
		if(object == null) {
			return "null";
		}
		if(object instanceof Jsonifyable) {
			return ((Jsonifyable)object).jsonify();
		}
		if(object instanceof String) {
			return '"' + object.toString() + '"';
		}
		if(object instanceof Number || object instanceof Boolean) {
			return object.toString();
		}
		if(object.getClass().isArray()) {
			StringBuilder builder = new StringBuilder("[");
			if(object instanceof Object[]) {
				Object[] array = (Object[]) object;
				for(int i = 0; i < array.length; ++i) {
					if(i != 0) {
						builder.append(", ");
					}
					builder.append(JSON.parse(array[i]));
				}
				//TODO array parsing
			}
			return builder.append(']').toString();
		}
		return typeOf(object).getQualifiedName() + "@" + object.hashCode();
	}
	
	public JSON() {
		
	}
}
