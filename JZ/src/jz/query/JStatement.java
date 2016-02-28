package jz.query;

import jz.JObject;
import jz.collections.JList;
import jz.func.FEval;
import jz.func.FProducer;

public abstract class JStatement<T, PT, MyT> extends JObject implements JQuery<T, MyT>
{
//#Variables
//{
	private JList<FEval<MyT>>
		evals = list();
//}
//#Query
//{
	public JStatement<T, PT, MyT> where(FEval<MyT> eval) {
		this.evals.add(eval);
		return this;
	}
	public <ItsT> JStatement<T, MyT, ItsT> as(FProducer<MyT, ItsT> producer) {
		return new JStatementClause<>(this, producer);
	}
//}
//#Check
//{
	protected boolean evaluate(MyT value) {
		for(FEval<MyT> current : this.evals) 
			if(!current.eval(value))
				return false;
		return true;
	}
//}
//#Static: Factory
//{
	public static <T> JStatement<T, T, T> create() {
		return new JStatementBase<>();
	}
//}
}
