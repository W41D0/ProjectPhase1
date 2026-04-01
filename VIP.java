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

        @Override
        public void Display()
        {
            System.out.print("Name: " + getName());
            System.out.print("\tBalance: " + getBalance());
            System.out.println("\tLoyalty Points: " + GetLoyaltyPoints());
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
