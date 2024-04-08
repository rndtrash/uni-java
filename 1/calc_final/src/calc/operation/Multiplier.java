package calc.operation;

public class Multiplier
{
	private int product;
	public Multiplier() {product = 0;}
	public Multiplier(int a) {this.product = a;}

	public void multiply(int b)
	{
		product *= b;
	}

	public int getProduct() {return product;}
}
