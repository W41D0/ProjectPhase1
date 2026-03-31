package ProjectPhase1;

public class Guest 
{
    protected double balance;

    public Guest(double balance)
    {
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

    public void setBalance(double balance) 
    {
        this.balance = balance;
    }

    public double getBalance()
    {
        return balance;
    }
}
