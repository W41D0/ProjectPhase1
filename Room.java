package ProjectPhase1;

public abstract class Room {


    protected double roomSize;
    protected int currentNumberGuest;
    protected int guestCapacity;
    protected Guest[] guestList;

    public Room(double roomSize, int guestCapacity) 
    {
        this.roomSize = roomSize;
        this.guestCapacity = guestCapacity;
        this.currentNumberGuest = 0;
        this.guestList= new Guest[guestCapacity];
    }

    public boolean IsFull() 
    {
        return currentNumberGuest == guestCapacity ;
    }

    public boolean IsEmpty() 
    {
        return currentNumberGuest == 0 ;
    }
    public boolean hasMVP;
    public boolean hasFreeRoomService;


    public abstract void AddGuest(Guest guest);
    public abstract void AddGuest(Guest[] guestS);
    public abstract void RemoveGuest(Guest guest);
    public abstract void RemoveGuest(Guest[] guestS);

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

}

