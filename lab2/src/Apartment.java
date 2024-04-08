public class Apartment extends Property{
    public String Address;
    public int Square;

    public Apartment(int worth, String address, int square) {
        super(worth);

        Address = address;
        Square = square;
    }

    @Override
    public int CalculateTax() {
        return (int) (Square / 1000d * worth);
    }
}
