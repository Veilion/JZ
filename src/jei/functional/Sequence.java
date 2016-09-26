package jei.functional;

import jei.StandardLibraryContender;

@FunctionalInterface
public interface Sequence extends StandardLibraryContender<Runnable>
{
	void call();
	
	@Override
	default Runnable java() {
		return this::call;
	}
	
	default Sequence before(Sequence sequence) {
		return () -> {
			this.call();
			sequence.call();
		};
	}
	
	default Sequence after(Sequence sequence) {
		return () -> {
			sequence.call();
			this.call();
		};
	}
}
