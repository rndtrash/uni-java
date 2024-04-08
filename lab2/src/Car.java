public class Car extends Property {
    public double Volume;
    public int Year;

    public Car(int worth, double volume) {
        super(worth);

        Volume = volume;
    }

    @Override
    public int CalculateTax() {
        return (int) (Volume / 10d * worth);
    }
}
