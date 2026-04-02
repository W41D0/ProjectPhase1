public abstract class Room 
{
    //Shared room data
    protected Hotel currentHotel;
    protected double roomSize;
    protected int currentNumberGuest;
    protected int guestCapacity;
    protected Guest[] guestList;
    protected int roomNo;

    //Constructor
    public Room(double roomSize, int guestCapacity) 
    {
        this.roomSize = roomSize;
        this.guestCapacity = guestCapacity;
        this.currentNumberGuest = 0;
        this.guestList= new Guest[guestCapacity];
    }

    //Must be implemented in subclasses
    public abstract void AddGuest(Guest guest, int days);
    public abstract void AddGuests(Guest[] guestS, int days);
    public abstract void RemoveGuest(Guest guest);
    public abstract void RemoveGuests(Guest[] guestS);

    //check room is full
    public boolean IsFull() 
    {
        return currentNumberGuest == guestCapacity ;
    }

    //check room is empty
    public boolean IsEmpty() 
    {
        return currentNumberGuest == 0 ;
    }

    //Display room info and guest list
    public void Display()
    {
        System.out.print("Room Size: " + roomSize);
        System.out.print("\tMaximum Guest Capacity: " + guestCapacity);
        System.out.println("\tNumber of currently booked Guests: " + currentNumberGuest);
        if(currentNumberGuest > 0)
        {
            System.out.println("Guest List:");
            for (int i = 0; i < currentNumberGuest; i++)
            {
                guestList[i].Display();
            }
        }
        else
        {
            System.out.println("Room is empty.");
        }
        System.out.println("");
    }

    //Getters-----setters
    public double getRoomSize() 
    {
        return roomSize;
    }

    public int getCurrentNumberGuest() 
    {
        return currentNumberGuest;
    }

    public int getGuestCapacity() 
    {
        return guestCapacity;
    }

    public Guest[] getGuestList() 
    {
        return guestList;
    }

    public void setHotel(Hotel hotel)
    {
        currentHotel = hotel;
    }

    public Hotel getHotel()
    {
        return currentHotel;
    }

    public void SetRoomNo(int roomNo)
    {
        this.roomNo = roomNo;
    }

}

