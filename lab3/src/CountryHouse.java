public class CountryHouse implements Property{
    protected int worth;
    public int Square_h;
    public int Square_t;

    public CountryHouse(int worth, int square_h, int square_t) {
        this.worth = worth;

        Square_h = square_h;
        Square_t = square_t;
    }

    @Override
    public int CalculateTax() {
        return (int) ((Square_t / 5000d + Square_h / 100d) * worth);
    }

    @Override
    public void Sell() {

    }

    @Override
    public void ChangeOwner() {

    }
}
