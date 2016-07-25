package jz.query;

public class JStatementBase<T> extends JStatement<T, T, T> 
{
//#Query
//{
	@Override
	public JQueryResult<T> check(T value) {
		if(this.evaluate(value))
			return new JQueryResult<>(value);
		return new JQueryResult<>();
	}
//}
}
