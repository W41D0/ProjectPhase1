public class MainGUI 
{
    public static void main(String[] args) 
    {
        Hotel Hilton = new Hotel("Hilton Hotel", 200, 10, 4);
        Hotel BurjAlArab = new Hotel("Burj Al Arab Hotel", 400, 20, 5);
        Resort Rosewood = new Resort("Rosewood Resort", 500);
        Hilton.AddRoom(new StandardRoom(30));
        Hilton.AddRoom(new StandardRoom(25));
        Hilton.AddRoom(new Suite(60));
        BurjAlArab.AddRoom(new StandardRoom(20));
        BurjAlArab.AddRoom(new StandardRoom(35));
        BurjAlArab.AddRoom(new Suite(50));
        new HotelGUI(Hilton, BurjAlArab, Rosewood);
    }    
}
