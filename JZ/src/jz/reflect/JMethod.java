package jz.reflect;

import java.lang.reflect.Method;

import jz.JObject;
import jz.collections.JMap;

public class JMethod extends JObject
{
//#Variables
//{
	private final Method
		method;
//}
//#Constructor
//{
	private JMethod(Method method) {
		this.method = method;
	}
//}
//#Static: 
//{
	private static final JMap<Method, JMethod>
		INSTANCES = map();
//}
//#Static: Factory
//{
	public static JMethod forMethod(Method method) {
		JMethod jMethod = INSTANCES.get(method);
		if(null == jMethod) {
			jMethod = new JMethod(method);
			INSTANCES.set(method, jMethod);
		}
		return jMethod;
	}
//}
}
