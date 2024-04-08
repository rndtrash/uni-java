public class Car implements Property {
    protected int worth;
    public double Volume;
    public int Year;

    public Car(int worth, double volume) {
        this.worth = worth;

        Volume = volume;
    }

    @Override
    public int CalculateTax() {
        return (int) (Volume / 10d * worth);
    }

    @Override
    public void Sell() {

    }

    @Override
    public void ChangeOwner() {

    }
}
