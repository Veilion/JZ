package jz.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jz.StaticObject;

public class UString extends StaticObject
{
	private UString() {}
	
//#Regex
//{
	//TODO implement UString.lastMatch(), UString.firstMatch()
	public static String firstMatch(String string, String regex) {
		Matcher matcher = Pattern.compile(regex).matcher(string);
		if(matcher.find()) 
			return matcher.group();
		else
			throw new IllegalArgumentException(string("the String {0} does not match the regex '{1}'", string, regex));
	}
	public static String lastMatch(String string, String regex) {
		Matcher matcher = Pattern.compile(regex).matcher(string);
		String match = null;
		while(matcher.find()) 
			match = matcher.group();
		if(null != match)
			return match;
		else
			throw new IllegalArgumentException(string("the String '{0}' does not match the regex '{1}'", string, regex));
	}
	public static String getMatch(String string, String regex, int matchNumber) {
		Matcher matcher = Pattern.compile(regex).matcher(string);
		for(int i = 0; i < matchNumber; ++i) {
			if(!matcher.find())
				throw new IllegalArgumentException(string("the String '{0}' does not contain a {1}. group that matches '{2}'", string, matchNumber, regex));
		}
		if(matcher.find()) 
			return matcher.group();
		else
			throw new IllegalArgumentException(string("the String '{0}' does not match the regex '{1}'", string, regex));
	}
//}

//#Manipulation
//{
	public static String insert(String string, Object... objects) {
		notNull(string);
		String replacement = UUID.randomUUID().toString();
		for(int i = 0; i < objects.length; ++i)
			string = string.replaceAll("\\{" + i + "\\}", replacement + "_" + i);
		for(int i = 0; i < objects.length; ++i)
			string = string.replaceAll(replacement + "_" + i, string(objects[i]));
		return string;
	}
//}
	
//#Create
//{
	public static String toString(Object object) {
		if(null == object)
			return "null";
		return object.toString();
		//TODO extend UString.toString(Object)
	}
//}
	
//#Transform
//{
	private final static String
		REGEX_ANY_INTEGER_OR_LONG = "\\d+",
		REGEX_ANY_FLOAT_OR_DOUBLE = "(\\d+\\.\\d+e\\d+)|(\\d+(\\.(\\d+)?)?)|(\\d+((.)?))(e\\d+)?";
	public static int toInt(String string) {
		string = string.trim();
		if(string.matches(REGEX_ANY_INTEGER_OR_LONG))
			return Integer.valueOf(string);
		else if(string.matches(REGEX_ANY_FLOAT_OR_DOUBLE))
			return Double.valueOf(string).intValue();
		else
			throw new IllegalArgumentException(string("String '{0}' cannot be parsed to an Integer", string));
	}
	public static long toLong(String string) {
		string = string.trim();
		if(string.matches(REGEX_ANY_INTEGER_OR_LONG))
			return Long.valueOf(string);
		else if(string.matches(REGEX_ANY_FLOAT_OR_DOUBLE))
			return Double.valueOf(string).intValue();
		else
			throw new IllegalArgumentException(string("String '{0}' cannot be parsed to a Long", string));
	}
	public static double toFloat(String string) {
		string = string.trim();
		if(string.matches(REGEX_ANY_FLOAT_OR_DOUBLE))
			return Float.valueOf(string);
		else if(string.matches(REGEX_ANY_INTEGER_OR_LONG)) 
			return Integer.valueOf(string);
		else
			throw new IllegalArgumentException(string("String '{0}' cannot be parsed to a Float", string));
	}
	public static double toDouble(String string) {
		string = string.trim();
		if(string.matches(REGEX_ANY_FLOAT_OR_DOUBLE))
			return Double.valueOf(string);
		else if(string.matches(REGEX_ANY_INTEGER_OR_LONG)) 
			return Long.valueOf(string);
		else
			throw new IllegalArgumentException(string("String '{0}' cannot be parsed to a Double", string));
	}
//}
}
