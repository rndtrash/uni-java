package calc;

public class Calc {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		Calculator calc = new Calculator();
		System.out.println("2+2="+calc.sum(2,2));
		System.out.println("10-8="+calc.sub(10,8));
		System.out.println("1*2*3="+calc.mul(1, 2, 3));
		System.out.println("10/5="+calc.div(10, 5));
		// Вариант 3
		System.out.println("(10^2)^2="+calc.square(10, 2));
	}
}
