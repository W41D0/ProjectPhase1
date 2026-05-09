import java.io.Serializable;
public class Guest implements Serializable
{
    protected String name;
    protected double balance;

    public Guest(String name, double balance)
    {
        this.name = name;
        this.balance = balance;
    }

    //method checks if user can afford then books him to a room if available one is found
    // throws InsufficientBalanceException "checked" propagated too and handled in HotelGUI
    public void bookHotel(Hotel hotel, int days) throws InsufficientBalanceException
    {
        double price = hotel.calculatePrice(this, days);

        if (price > getBalance())
        {
            throw new InsufficientBalanceException(price, getBalance());
        }

        if (!hotel.standardsFullyBooked())
        {
            StandardRoom r = hotel.availableRoom();
            r.AddGuest(this, days);
            r.Display();
        }
        else
            System.out.println("No Available Rooms");
    }


    //displays user info
    public void Display()
    {
        System.out.print("Name: " + getName());
        System.out.println("\tBalance: " + getBalance() + " Riyals\n");
    }

    public String getDetails() 
    {
        String result;
        result = "Name: " + getName();
        result += "\tBalance: " + getBalance() + " Riyals\n\n";
        return result;
    }



    //setter and getters-------------------------------------------------------------
    public void setBalance(double balance) 
    {
        this.balance = balance;
    }

    public double getBalance()
    {
        return balance;
    }

    public String getName()
    {
        return name;
    }
}
