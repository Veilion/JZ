package jz.func;

@FunctionalInterface
public interface FProducer<TIn, TOut> 
{
	public TOut produce(TIn from);
}
