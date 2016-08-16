package jei._main;

import jei.Base;
import jei.data.annotation.Data;
import jei.data.xml.reflect.XML;

public class Main extends Base
{
	public static void main(String[] args) throws Throwable {
		Person p = new Person();
		p.age = 42;
		p.name = "Peter";
		XML.write("out.xml", p);
	}
	
	@Data
	static class Person
	{
		private String
			name;
		
		private int
			age;
	}
}

