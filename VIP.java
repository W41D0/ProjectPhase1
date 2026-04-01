public class VIP extends Guest
{
    protected int loyaltyPoints;

    public VIP(String name, double balance, int loyaltyPoints)
    {
        super(name, balance);
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    //method checks if user can afford then books him to a room if available one is found
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
    //displays user info
    public void Display()
    {
        System.out.print("Name: " + getName());
        System.out.print("\tBalance: " + getBalance());
        System.out.println("\tLoyalty Points: " + GetLoyaltyPoints() + "\n");
    }

    //setter and getters-------------------------------------------------------------
    public void SetLoyaltyPoints(int loyaltyPoints)
    {
        this.loyaltyPoints = loyaltyPoints;
    }

    public int GetLoyaltyPoints()
    {
        return loyaltyPoints;
    }
}
