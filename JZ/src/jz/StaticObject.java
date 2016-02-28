package jz;

public class StaticObject extends JObject
{
	public StaticObject() {
		throw new IllegalAccessError("cannot create instances of a static class");
	}
}
