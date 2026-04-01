import java.util.Scanner;
public class Main 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        Hotel Hilton = new Hotel("Hilton Hotel", 200, 10, 4);
        Hotel BurjAlArab = new Hotel("Burj Al Arab Hotel", 400, 20, 5);
        Resort Rosewood = new Resort("Rosewood Resort", 500);
        Hilton.AddRoom(new StandardRoom(30));
        Hilton.AddRoom(new StandardRoom(25));
        Hilton.AddRoom(new Suite(60));
        BurjAlArab.AddRoom(new StandardRoom(20));
        BurjAlArab.AddRoom(new StandardRoom(35));
        BurjAlArab.AddRoom(new Suite(50));

        System.out.println("Welcome to the Hotel Management System");
        
        while(true)
        {
            int choice;

            System.out.println("\nChoose your Option: \n1. Travel Manager \n2. Sign up as a Guest \n3. Exit");
            choice = input.nextInt();

            //Travel Manager Menu----------------------------------------------------------------
            if(choice == 1)
            {
                while (true) 
                {
                    Hotel chosenHotel;
                    Resort chosenResort;

                    System.out.println("\nWelcome Manager. What would you like to manage?");
                    System.out.println("1. Hilton Hotel \n2. Burj Al Arab Hotel \n3. Rosewood Resort \n4. Exit");

                    choice = input.nextInt();

                    //Hotel Menu----------------------------------------------------------------
                    if (choice == 1 || choice == 2)
                    {
                        if (choice == 1)
                            chosenHotel = Hilton;
                        else 
                            chosenHotel = BurjAlArab;

                        System.out.println("\nWhat would you like to do?");
                        System.out.println("1. Add a room\n2. Diplay Details \n3. Exit");
                        choice = input.nextInt();

                        //Room Creation Menu------------------------------------------------
                        if (choice == 1)
                        {
                            System.out.println("\n1. Standard\n2. Suite \n3. Exit");
                            choice = input.nextInt();
                            System.out.print("\nRoom Size?: ");
                            double roomSize = input.nextDouble();

                            if (choice == 1)
                                chosenHotel.AddRoom(new StandardRoom(roomSize));
                            else if (choice == 2)
                                chosenHotel.AddRoom(new Suite(roomSize));
                            else
                                continue;  

                            System.out.println("Room No." + chosenHotel.GetCurrentNumberOfRooms() + " Added.");
                            continue;
                        }
                        //Display---------------------------------------
                        else if (choice == 2) 
                        {
                            chosenHotel.Display();    
                            continue;
                        }
                        //Exit------------------------------------------
                        else
                            continue;

                    }

                    //Resort Menu---------------------------------------------------
                    else if (choice == 3)
                    {
                        chosenResort = Rosewood;
                        System.out.println("\nWhat would you like to do?");
                        System.out.println("1. Diplay Details \n2. Exit");
                        choice = input.nextInt();

                        //Display-----------------------------------------
                        if (choice == 1)
                        {
                            chosenResort.Display();
                            continue;
                        }
                        //Exit--------------------------------------------
                        else
                            continue;
                    }
                    //Exit-----------------------------------------------------
                    else
                        break;
                }
            }

            //Guest SignUp Menu----------------------------------------------------------
            else if (choice == 2)
            {           
                //choose Single or Multiple Guests
                System.out.println("\n1. Single Guest \n2. Multiple Guests \n3. Exit");
                int guestChoice = input.nextInt();

                Guest userGuest = null;
                Guest[] guestList = null;
                boolean listHasVIP = false;
                int numOfGuests = 1;

                //choose number of guests (if multiple)
                if (guestChoice == 2)
                {
                    System.out.print("\nchoose Number of Guests: "); 
                    numOfGuests = input.nextInt();
                    
                    if(numOfGuests <=1)
                        guestChoice = 1;    
                    else
                        guestList = new Guest[numOfGuests];
                }

                for(int i = 0; i < numOfGuests; i++)
                {
                    if (guestChoice == 2)
                    {
                        System.out.println("Guest No." + (i + 1) + ":");
                    }

                    //Choose name
                    System.out.println("Enter Your Name: ");
                    input.nextLine();
                    String name = input.nextLine();

                    //Selecting guest type
                    System.out.println("\nSelect Guest Type: \n1. Regular Guest\n2. VIP\n3. MVP");
                    choice = input.nextInt();

                    //Selecting balance
                    System.out.println("\nEnter your balance: ");
                    double balance = input.nextDouble();

                    //selecting loyalty points ammount
                    int Lpoints = 0;
                    if (choice == 2 || choice == 3)
                    {
                        listHasVIP = true;
                        System.out.println("\nEnter how many Loyalty Points you have: ");
                        if (choice == 2)
                            System.out.print("VIP Conversion: 20 points = 1 free day: ");
                        else
                            System.out.print("MVP Conversion: 10 points = 1 free day: ");

                        Lpoints = input.nextInt();
                    }

                    //creates Guest
                    if (choice == 2) 
                    {
                        userGuest = new VIP(name, balance, Lpoints);
                    } 
                    else if (choice == 3) 
                    {
                        userGuest = new MVP(name, balance, Lpoints);
                    } 
                    else 
                    {
                        userGuest = new Guest(name, balance);
                    }

                    if(guestChoice == 2)
                    {
                        guestList[i] = userGuest;
                    }
                    System.out.println("");
                }

                //Hotel Booking Menu----------------------------------------------------------
                System.out.println("\nWhere would you like to stay?\n1. Hilton Hotel\n2. Burj Al Arab");
                choice = input.nextInt();

                //choose hotel
                Hotel selectedPlace;
                if (choice == 1) 
                {
                    selectedPlace = Hilton;
                } 
                else 
                {
                    selectedPlace = BurjAlArab;
                }

                //choose nights
                System.out.println("\nHow many nights?");
                int days = input.nextInt();

                //books single user
                if(guestChoice == 1 && userGuest != null)
                {
                    userGuest.bookHotel(selectedPlace, days);
                    System.out.println("");
                }
                //books multiple users
                else if (guestChoice == 2 && userGuest != null)
                {
                    if(listHasVIP)
                        selectedPlace.availableSuite().AddGuests(guestList, days);
                    else
                        selectedPlace.availableRoom().AddGuests(guestList, days);
                }
            }
            //Exit----------------------------------------------------------  
            else
            {
                System.out.print("\nGoodBye!");
                break;
            }
        }
        input.close();
    }
}
