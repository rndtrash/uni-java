package calc;
import calc.operation.*;

public class Calculator
{
	public int sum(int... a)
	{
		Adder adder = new Adder();
		for (int i : a)
		{
			adder.add(i);
		}
		return adder.getSum();
	}
	
	public int sub(int... a)
	{
		Substractor substractor = new Substractor(a[0]);
		for (int i = 1; i < a.length; i++)
		{
			substractor.substract(a[i]);
		}
		return substractor.getSum();
	}

	public int mul(int... a)
	{
		Multiplier multiplier = new Multiplier(a[0]);
		for (int i = 1; i < a.length; i++)
		{
			multiplier.multiply(a[i]);
		}
		return multiplier.getProduct();
	}

	public int div(int... a)
	{
		Divider divider = new Divider(a[0]);
		for (int i = 1; i < a.length; i++)
		{
			divider.divide(a[i]);
		}
		return divider.getProduct();
	}

	public int square(int a, int b)
	{
		Square square = new Square(a);
		for (int i = 0; i < b; i++) {
			square.square();
		}
		return square.getProduct();
	}
}
