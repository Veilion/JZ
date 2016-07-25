package jei.functional;

@FunctionalInterface
public interface Producer<I, O> 
{
	O invoke(I value);
	
	default <R> Producer<I, R> before(Producer<O, R> producer) {
		return e -> {
			return producer.invoke(this.invoke(e));
		};
	}
	default <A> Producer<A, O> after(Producer<A, I> producer) {
		return e -> {
			return this.invoke(producer.invoke(e));
		};
	}
}
