package ProjectPhase1;

public class Guest 
{
    protected String name;
    protected double balance;

    public Guest(String name, double balance)
    {
        this.name = name;
        this.balance = balance;
    }

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

        public void Display()
        {
            System.out.print("Name: " + getName());
            System.out.println("\tBalance: " + getBalance() + " Riyals");
        }

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
