package jei.types;

import java.lang.reflect.Modifier;

public enum Visibility 
{
	PUBLIC("public"),
	PROTECTED("protected"),
	PACKAGE_PRIVATE(""),
	PRIVATE("private"),
	;
	private final String
		keyword;
	
	private Visibility(String keyword) {
		this.keyword = keyword;
	}
	
	public String getKeyword() {
		return this.keyword;
	}
	
	static Visibility forModifier(int modifier) {
		if(Modifier.isPublic(modifier)) {
			return PUBLIC;
		}
		if(Modifier.isProtected(modifier)) {
			return PROTECTED;
		}
		if(Modifier.isPrivate(modifier)) {
			return PRIVATE;
		}
		return PACKAGE_PRIVATE;
	}
}
