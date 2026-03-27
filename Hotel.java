package ProjectPhase1;

public class Hotel implements MinistryOfTravel
{
    private int maxNumberOfRooms;
    private int currentNumberOfRooms;
    private double totalProfit;
    private double pricePerDay;
    public Room[] roomList;

    private int rating;
    private boolean localsAllowed;

    public Hotel(double pricePerDay, int maxNumberOfRooms, int rating, boolean allowLocals)
    {
        SetPricePerDay(pricePerDay);
        SetMaxNumberOfRooms(maxNumberOfRooms);
        roomList = new Room[maxNumberOfRooms];

        this.rating = rating;
        this.localsAllowed = allowLocals;
    }

    public void AddRoom(Room room)
    {
        if (currentNumberOfRooms < maxNumberOfRooms)
        {
            roomList[currentNumberOfRooms] = room; 
        }
        else
        {
            System.out.println("Hotel does not have the capacity");
        }
    }

    public void AddProfit(double profitGain)
    {
        totalProfit += profitGain;
    }

    public int getRating()
    {
        return rating;
    }

    public boolean allowLocals()
    {
        return localsAllowed;
    }

    public void SetMaxNumberOfRooms(int maxNumberOfRooms)
    {
        this.maxNumberOfRooms = maxNumberOfRooms;
    }

    public int GetMaxNumberOfRooms()
    {
        return maxNumberOfRooms;
    }

    public void SetPricePerDay(double pricePerDay)
    {
        this.pricePerDay = pricePerDay;
    }

    public double GetPricePerDay()
    {
        return pricePerDay;
    }

    public double GetTotalProfit()
    {
        return totalProfit;
    }

    //-------------------------------------------------------------------------------------------------------
    //finish everything after this comment (the code under here is just a placeholder)

    public boolean standardsFullyBooked()
    {
        return false;
    }

    public boolean suitesFullyBooked()
    {
        return false;
    }

    public StandardRoom availableRoom()
    {
        return availableRoom();
    }

    public Suite availableSuite()
    {
        return availableSuite();
    }
}
