public class CountryHouse extends Property{
    public int Square_h;
    public int Square_t;

    public CountryHouse(int worth, int square_h, int square_t) {
        super(worth);

        Square_h = square_h;
        Square_t = square_t;
    }

    @Override
    public int CalculateTax() {
        return (int) ((Square_t / 5000d + Square_h / 100d) * worth);
    }
}
