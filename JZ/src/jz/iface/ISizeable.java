package jz.iface;

public interface ISizeable
{
	public void clear();
	public int getSize();
	public default boolean isEmpty() {
		return this.getSize() <= 0;
	}
}
