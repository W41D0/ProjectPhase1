public class Hotel implements MinistryOfTravel
{
    private String name;
    private int maxNumberOfRooms;
    private int currentNumberOfRooms = 0;
    private double totalProfit = 0;
    private double pricePerDay;
    public Room[] roomList;
    private int rating;

    public Hotel(String name, double pricePerDay, int maxNumberOfRooms, int rating)
    {
        this.name = name;
        SetPricePerDay(pricePerDay);
        SetMaxNumberOfRooms(maxNumberOfRooms);
        roomList = new Room[maxNumberOfRooms];

        this.rating = rating;
    }

    public void AddRoom(Room room)
    {
        if (currentNumberOfRooms < maxNumberOfRooms)
        {
            if(room instanceof StandardRoom)
                roomList[currentNumberOfRooms] = new StandardRoom(room.getRoomSize()); 
            else if (room instanceof Suite)
                roomList[currentNumberOfRooms] = new Suite(room.getRoomSize());

            roomList[currentNumberOfRooms].SetRoomNo(currentNumberOfRooms + 1);
            roomList[currentNumberOfRooms++].setHotel(this);
            
        }
        else
        {
            System.out.println("Hotel does not have the capacity");
        }
    }

    public double calculatePrice(Guest guest, int days) 
    {
        double totalPrice = GetPricePerDay() * days;
        int freeDays = 0;

        // 1. Figure out how many free days they can afford
        if (guest instanceof MVP) 
        {
            freeDays = ((MVP)(guest)).GetLoyaltyPoints() / 10;
        } 
        else if (guest instanceof VIP) 
        {
            freeDays = ((VIP)(guest)).GetLoyaltyPoints() / 20;
        }

        if (freeDays > days) 
        {
            freeDays = days;
        }

        totalPrice -= (freeDays * GetPricePerDay());
        return totalPrice;
    }

    public boolean standardsFullyBooked() 
    {
        for (int i = 0; i < currentNumberOfRooms; i++) 
        {
            if (roomList[i] instanceof StandardRoom) 
            {
                if (roomList[i].IsEmpty()) 
                {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean suitesFullyBooked() 
    {
        for (int i = 0; i < currentNumberOfRooms; i++) 
        {
            if (roomList[i] instanceof Suite) 
            {
                if (roomList[i].IsEmpty()) 
                {
                    return false;
                }
            }
        }
        return true;
    }

    public StandardRoom availableRoom() 
    {
        return findStandardRecursive(0);
    }

    private StandardRoom findStandardRecursive(int index) 
    {
        if (index >= currentNumberOfRooms) 
        {
            return null;
        }

        if (roomList[index] instanceof StandardRoom) 
        {
            if (roomList[index].IsEmpty()) 
            {
                return (StandardRoom) roomList[index];
            }
        }
        return findStandardRecursive(index + 1);
    }

    public Suite availableSuite() 
    {
        return findSuiteRecursive(0);
    }

    private Suite findSuiteRecursive(int index) 
    {

        if (index >= currentNumberOfRooms) 
        {
            return null;
        }

        if (roomList[index] instanceof Suite) 
        {
            if (roomList[index].IsEmpty()) 
            {
                return (Suite) roomList[index];
            }
        }
        return findSuiteRecursive(index + 1);
    }

    public void Display()
    {
        System.out.println("Hotel: " + name);
        System.out.print("Rating: " + rating + " stars.");
        System.out.print("\tPrice Per Day: " + pricePerDay + " Riyals.");
        System.out.println("\tTotal Profit: " + totalProfit + " Riyals.");
        System.out.println("Number of current rooms: " + currentNumberOfRooms + "\n");
        System.out.println("Room Info:");
        if(currentNumberOfRooms > 0)
        {
            for (int i = 0; i < currentNumberOfRooms; i++) 
            {
                System.out.println("Room No." + (i + 1));
                roomList[i].Display();    
            }
        }
        else
        {
            System.out.println("The Hotel is empty of rooms.");
        }
    }

    public void AddProfit(double profitGain)
    {
        totalProfit += profitGain;
    }

    //setter and getters-------------------------------------------------------------
    public int getRating()
    {
        return rating;
    }

    public String getName()
    {
        return name;
    }

    public void SetMaxNumberOfRooms(int maxNumberOfRooms)
    {
        this.maxNumberOfRooms = maxNumberOfRooms;
    }

    public int GetMaxNumberOfRooms()
    {
        return maxNumberOfRooms;
    }

    public int GetCurrentNumberOfRooms()
    {
        return currentNumberOfRooms;
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
}
