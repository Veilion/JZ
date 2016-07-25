package jei.functional;

@FunctionalInterface
public interface Producer2<I1, I2, O> 
{
	O invoke(I1 value1, I2 value2);
}
