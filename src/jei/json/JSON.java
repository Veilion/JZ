package jei.json;

public class JSON 
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
		return object.getClass().toString() + "@" + object.hashCode();
	}
}
