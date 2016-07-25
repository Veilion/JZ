package jz;

import jz.iface.IDumpable;

public class JConsole extends StaticObject
{
//#Constructor
//{
	private JConsole() {}
//}
//#Write
//{
	public static void write(Object... objects) {
		for(Object current : objects)
			System.out.println(string(current));
	}
//}
//#Dump
//{
	public static void dump(Object object) {
		JConsole.write(dumpOf(object));
	}
	private static String dumpOf(Object object) {
		String dump = "";
		if(object instanceof IDumpable) {
			dump = ((IDumpable) object).createDump();
		} else if(object instanceof Iterable) {
			Iterable<?> iterable = (Iterable<?>) object;
			String header = object.getClass().getName();
			String content = "";
			int i = 0; for(Object current : iterable) {
				content += string("    #{0} => {1}\n", i++, dumpOf(current));
			}
			dump = string("{0} ({1}) {{2}" + content + "}", header, i, i > 0 ? "\n" : "");
			
		} else {
			dump = string(object);
		}
		return dump;
	}
//}
}
