package ProjectPhase1;

import java.util.Scanner;
public class Main 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        Hotel Hilton = new Hotel("Hilton Hotel", 200, 10, 4);
        Hotel BurjAlArab = new Hotel("Burj Al Arab Hotel", 400, 20, 5);
        Resort myResort = new Resort("Rosewood Resort", 500);
        Hilton.AddRoom(new StandardRoom(30.0));
        Hilton.AddRoom(new StandardRoom(25.0));
        Hilton.AddRoom(new Suite(60.0));

        System.out.println("Welcome to the Hotel Management System");

        System.out.println("Select Guest Type: 1. Regular Guest, 2. VIP, 3. MVP");
        int type = input.nextInt();

        System.out.println("Enter your balance: ");
        double balance = input.nextDouble();

        Guest userGuest;

        if (type == 2) 
        {
            userGuest = new VIP("Ahmad", balance, 100);
        } 
        else if (type == 3) 
        {
            userGuest = new MVP("Ahmad", balance, 500);
        } 
        else 
        {
            userGuest = new Guest("Ahmad", balance);
        }

        System.out.println("Where would you like to stay? 1. Hilton Hotel, 2. Burj Al Arab");
        int choice = input.nextInt();

        Hotel selectedPlace;

        if (choice == 1) 
        {
            selectedPlace = Hilton;
        } 
        else 
        {
            selectedPlace = BurjAlArab;
        }

        System.out.println("How many nights?");
        int days = input.nextInt();

        userGuest.bookHotel(selectedPlace, days);

        System.out.println("Total Profit is: " + selectedPlace.GetTotalProfit());
        System.out.println("Standards Full: " + selectedPlace.standardsFullyBooked());
        System.out.println("Suites Full: " + selectedPlace.suitesFullyBooked());
        if (userGuest instanceof VIP v)
            System.out.println("Loyalty points left: " + v.GetLoyaltyPoints());
    }
}
