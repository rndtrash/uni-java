public class Apartment implements Property{
    protected int worth;
    public String Address;
    public int Square;

    public Apartment(int worth, String address, int square) {
        this.worth = worth;

        Address = address;
        Square = square;
    }

    @Override
    public int CalculateTax() {
        return (int) (Square / 1000d * worth);
    }

    @Override
    public void Sell() {
    }

    @Override
    public void ChangeOwner() {
    }
}
