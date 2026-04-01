package ProjectPhase1;

public class VIP extends Guest
{
    protected int loyaltyPoints;

    public VIP(String name, double balance, int loyaltyPoints)
    {
        super(name, balance);
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public void bookHotel(Hotel hotel, int days)
    {
        if (hotel.calculatePrice(this, days) <= getBalance())
        {
            if (!hotel.suitesFullyBooked())
            {
                hotel.availableSuite().AddGuest(this, days);
            }
            else
            {
                System.out.println("No Available Rooms");
            }
        }
        else
        {
            System.out.println("Cant Afford Room");
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
