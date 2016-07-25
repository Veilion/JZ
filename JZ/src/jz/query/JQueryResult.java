package jz.query;

public class JQueryResult<T>
{
//#Variables
//{
	private final T
		value;
	
	private final boolean
		isSuccess;
//}
//#Constructor
//{
	public JQueryResult() {
		this.value = null;
		this.isSuccess = false;
	}
	public JQueryResult(T value) {
		this.value = value;
		this.isSuccess = true;
	}
//}
//#Getters
//{
	public T getValue() {
		if(this.isSuccess())
			return this.value;
		throw new IllegalStateException("cannot access value of failed query");
	}
	public boolean isSuccess() {
		return this.isSuccess;
	}
//}
}
