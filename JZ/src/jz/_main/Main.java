package jz._main;

import jz.JObject;
import jz.collections.JMap;

public class Main extends JObject
{
	public static void main(String[] args) throws Exception {
		JMap<String, String> map = map(entry("hallo", "du"));
		map.set("du", "hallo");
		map.set("hallo", "ihr");
		//TODO Set!
		dump(map);
	}
}
