package jei.functional;

@FunctionalInterface
public interface Predicate<T> 
{
	boolean invoke(T value);
	
	default Predicate<T> and(Predicate<T> predicate) {
		return e -> this.invoke(e) && predicate.invoke(e);
	}
	default Predicate<T> or(Predicate<T> predicate) {
		return e -> this.invoke(e) || predicate.invoke(e);
	}
	default Predicate<T> not() {
		return e -> !this.invoke(e);
	}
}
