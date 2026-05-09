//Custom unchecked exception: thrown when trying to add a guest to a fully booked room
public class RoomFullException extends RuntimeException
{
    private int roomNo;

    public RoomFullException(int roomNo)
    {
        super("Room No." + roomNo + " is fully booked and cannot accept more guests.");
        this.roomNo = roomNo;
    }
    //Getters----------
    public int getRoomNo()
    {
        return roomNo;
    }
}