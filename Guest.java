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
        if (!hotel.standardsFullyBooked())
        {
            hotel.availableRoom().addGuest(this);
        }
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
