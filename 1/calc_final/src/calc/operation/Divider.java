package calc.operation;

public class Divider
{
	private int product;
	public Divider() {product = 0;}
	public Divider(int a) {this.product = a;}

	public void divide(int b)
	{
		product /= b;
	}

	public int getProduct() {return product;}
}
