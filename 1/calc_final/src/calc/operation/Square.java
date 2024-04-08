package calc.operation;

public class Square
{
	private int product;
	public Square(int a) {this.product = a;}

	public void square()
	{
		product *= product;
	}

	public int getProduct() {return product;}
}
