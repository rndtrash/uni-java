public abstract class Property {
    protected int worth;

    public Property(int worth) {
        this.worth = worth;
    }

    public abstract int CalculateTax();

    public void Sell() {}

    public void ChangeOwner() {}
}
