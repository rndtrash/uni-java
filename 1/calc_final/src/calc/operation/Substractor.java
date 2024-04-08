package calc.operation;

public class Substractor
{
	private int sum;
	public Substractor() {sum = 0;}
	public Substractor(int a) {this.sum = a;}

	public void substract(int b)
	{
		sum -= b;
	}

	public int getSum() {return sum;}
}
