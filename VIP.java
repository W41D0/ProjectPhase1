package ProjectPhase1;

public class VIP extends Guest
{
    protected int loyaltyPoints;

    public VIP(double balance, int loyaltyPoints)
    {
        super(balance);
        this.loyaltyPoints = loyaltyPoints;
    }

    public void bookHotel(Hotel hotel, int days)
    {
        if (!hotel.suitesFullyBooked())
        {
            hotel.availableSuite().AddGuest(this);
        }
    }

    public void SetLoyaltyPoints(int loyaltyPoints)
    {
        this.loyaltyPoints = loyaltyPoints;
    }

    public int GetLoyaltyPoints()
    {
        return loyaltyPoints;
    }
}
