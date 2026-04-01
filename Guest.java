public class Guest 
{
    protected String name;
    protected double balance;

    public Guest(String name, double balance)
    {
        this.name = name;
        this.balance = balance;
    }

    //method checks if user can afford then books him to a room if available one is found
    public void bookHotel(Hotel hotel, int days)
    {
        if (hotel.calculatePrice(this, days) <= getBalance())
        {
            if (!hotel.standardsFullyBooked())
            {
                hotel.availableRoom().AddGuest(this, days);
            }
            else
                System.out.println("No Available Rooms");
        }
        else
            System.out.println("Cant Afford Room");
    }

    //displays user info
    public void Display()
    {
        System.out.print("Name: " + getName());
        System.out.println("\tBalance: " + getBalance() + " Riyals\n");
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
