import java.io.Serializable;
public class VIP extends Guest
{
    //VIP loyalty points
    protected int loyaltyPoints;

    //Constructor
    public VIP(String name, double balance, int loyaltyPoints)
    {
        super(name, balance);
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    //method checks if user can afford then books him to a room if available one is found
    //throws InsufficientBalanceException instead of printing "Cant Afford Room" handled in HotelGUI and Main
    public void bookHotel(Hotel hotel, int days) throws InsufficientBalanceException
    {
        double price = hotel.calculatePrice(this, days);

        if (price > getBalance())
        {
            //replaces: System.out.println("Cant Afford Room");
            throw new InsufficientBalanceException(price, getBalance());
        }

        if (!hotel.suitesFullyBooked())
        {
            Suite s = hotel.availableSuite();
            s.AddGuest(this, days);
            s.Display();
        }
        else
        {
            System.out.println("No Available Rooms");
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

    @Override
    public String getDetails() 
    {
        String result;
        result = "Name: " + getName();
        result += "\tBalance: " + getBalance();
        result += "\tLoyalty Points: " + GetLoyaltyPoints() + "\n\n";
        return result;
    }


    //setter and getters.......................................................................ز
    public void SetLoyaltyPoints(int loyaltyPoints)
    {
        this.loyaltyPoints = loyaltyPoints;
    }

    public int GetLoyaltyPoints()
    {
        return loyaltyPoints;
    }
}