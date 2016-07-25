package jz.query;

import jz.func.FProducer;

class JStatementClause<T, PT, MyT> extends JStatement<T, PT, MyT>
{
//#Variables
//{
	private final JStatement<T, ?, PT> 
		parent;
	
	private final FProducer<PT, MyT>
		producer;
//}
//#Constructor
//{
	public JStatementClause(JStatement<T, ?, PT> parent, FProducer<PT, MyT> producer) {
		this.parent = parent;
		this.producer = producer;
	}
//}
//#Query
//{
	@Override
	public JQueryResult<MyT> check(T value) {
		JQueryResult<PT> result = this.parent.check(value);
		if(result.isSuccess()) {
			MyT myValue = this.producer.produce(result.getValue());
			if(this.evaluate(myValue))
				return new JQueryResult<>(myValue);
		}
		return new JQueryResult<>();
	}
//}
}
