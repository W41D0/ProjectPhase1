public class Hotel implements MinistryOfTravel
{
    private String name;
    private int maxNumberOfRooms;
    private int currentNumberOfRooms = 0;
    private double totalProfit = 0;
    private double pricePerDay;
    public LinkedList<Room> roomList;
    private int rating;

    public Hotel(String name, double pricePerDay, int maxNumberOfRooms, int rating)
    {
        this.name = name;
        SetPricePerDay(pricePerDay);
        SetMaxNumberOfRooms(maxNumberOfRooms);
        roomList = new LinkedList<>();
        this.rating = rating;
    }

    public void AddRoom(Room room)  //check if there is a space before adding a room
    {
        if (currentNumberOfRooms < maxNumberOfRooms)
        {
            if(room instanceof StandardRoom)
                roomList.insertAtBack(new StandardRoom(room.getRoomSize()));
            else if (room instanceof Suite)
                roomList.insertAtBack(new Suite(room.getRoomSize()));

            roomList.getData(currentNumberOfRooms).SetRoomNo(currentNumberOfRooms + 1);
            roomList.getData(currentNumberOfRooms++).setHotel(this);
            
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
            if (roomList.getData(i) instanceof StandardRoom) 
            {
                if (roomList.getData(i).IsEmpty()) 
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
            if (roomList.getData(i) instanceof Suite) 
            {
                if (roomList.getData(i).IsEmpty()) 
                {
                    return false;
                }
            }
        }
        return true;
    }

    public StandardRoom availableRoom() //recursive search to find the first empty standard room
    {
        return findStandardRecursive(0);
    }

    private StandardRoom findStandardRecursive(int index) 
    {
        if (index >= currentNumberOfRooms) 
        {
            return null;
        }

        if (roomList.getData(index) instanceof StandardRoom) 
        {
            if (roomList.getData(index).IsEmpty()) 
            {
                return (StandardRoom) roomList.getData(index);
            }
        }
        return findStandardRecursive(index + 1);
    }

    public Suite availableSuite() ////recursive search to find the first empty suite.
    {
        return findSuiteRecursive(0);
    }

    private Suite findSuiteRecursive(int index) 
    {

        if (index >= currentNumberOfRooms) 
        {
            return null;
        }

        if (roomList.getData(index) instanceof Suite) 
        {
            if (roomList.getData(index).IsEmpty()) 
            {
                return (Suite) roomList.getData(index);
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
                roomList.getData(i).Display();    
            }
        }
        else
        {
            System.out.println("The Hotel is empty of rooms.");
        }
    }

    public String getDetails() 
    {
        String result;
        result = "Hotel: " + name + "\n";
        result += "Rating: " + rating + " stars.";
        result += "\tPrice Per Day: " + pricePerDay + " Riyals.";
        result += "\tTotal Profit: " + totalProfit + " Riyals.\n";
        result += "Number of current rooms: " + currentNumberOfRooms + "\n\n";
        result += "Room Info:\n";
        if (currentNumberOfRooms > 0) 
        {
            for (int i = 0; i < currentNumberOfRooms; i++) 
            {
                result += "Room No." + (i + 1) + "\n";
                result += roomList.getData(i).getDetails();
            }
        } 
        else 
            result += "The Hotel is empty of rooms.\n";
        return result;
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
