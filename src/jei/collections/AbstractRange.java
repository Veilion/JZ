package jei.collections;

import jei.Base;
import jei.annotation.Lazy;

abstract class AbstractRange<T extends Number> extends Base implements Range<T>
{
	private final boolean
		isLazy
	;
	
	@Lazy
	private String
		string = null
	;
	
	public AbstractRange(boolean isLazy) {
		this.isLazy = isLazy;
	}
	
	@Override
	public abstract int hashCode();
	@Override
	public boolean equals(Object object) {
		if(object instanceof Range) {
			Range<?> range = (Range<?>) object;
			return range.first() 	   == this.first() &&
			       range.last()  	   == this.last()  &&
			       range.getJumpSize() == this.getJumpSize();
		}
		return false;
	}
	@Override
	public String toString() {
		if(this.string == null) {
			filter(this.getClass().getInterfaces(), iface -> {
				if(iface.isAssignableFrom(Range.class)) {
					
				}
				return true;
			});
			this.string = format("{0}[{4}]({1} -> {2}, jump: {3})", typeby(IntRange.class).getName(), this.first(), this.last(), this.getJumpSize(), this.isLazy ? "lazy" : "eager");
		}
		return this.string;
		
	}
}
