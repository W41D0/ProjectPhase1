public class StandardRoom extends Room
{
	//Constructor (standard room capacity = 2)
	public StandardRoom(double roomSize) 
    {
		super(roomSize, 2);
	}

	//Add one guest if valid, room not full and guest can afford
    @Override
	public  void AddGuest(Guest guest, int days) 
    {
		if(guest == null) 
        {
	 		System.out.println("Error...");
			return;
        }
		else if(IsFull()) 
        {
			System.out.println("Can Not add" + guest.getName() + ", the room is Full");
			return;
        }
		else if (currentHotel.calculatePrice(guest, days) > guest.getBalance())
        {
			System.out.println("Can Not add " + guest.getName() + ", cant afford.");
			return;
		}
		else 
        {
			for(int i = 0 ; i < guestList.length; i++) 
            {
				if(guestList[i] == null) 
                {
					guestList[i] = guest;
					double finalPrice = currentHotel.calculatePrice(guestList[i], days);

					// Update hotel profit and deduct payment from guest balance
					currentHotel.AddProfit(finalPrice);
					guestList[i].setBalance(guestList[i].getBalance() - finalPrice);
					currentNumberGuest++;

					//Booking confirmation output
					System.out.println(guestList[i].getName() + " Paid " + finalPrice + " Riyals.");
					System.out.println(guestList[i].getName() + " Succesfully booked into Room No." + roomNo);
					Display();

					return;
				}
			}
		}
	}

	//Add multiple guests after capacity check
    @Override
	public  void AddGuests(Guest[] guestS, int days) 
    {
		if(guestS == null) 
		{
			System.out.println("Error...");   	
			return;
		}

		//Count guests that can afford booking
		int count = 0;
		for(int i = 0; i< guestS.length; i++) 
		{
			if(guestS[i] != null && currentHotel.calculatePrice(guestS[i], days) < guestS[i].getBalance())
				count++;
		}
		
		//Check if adding guests exceeds room capacity
		if(count + currentNumberGuest > guestCapacity) 
		{
			System.out.println("Error The Number of new guest Bigger than the size...");
			return;
		}

		// Add all guests one by one
		for(int i = 0; i<guestS.length;i++) 
		{
			if(guestS != null)
				AddGuest(guestS[i], days);
		}
	}

	//Remove one guest from room
	@Override
	public  void RemoveGuest(Guest guest) 
	{
		if(guest == null) 
		{
			System.out.println("Error...");
			return;
		}
		else if(IsEmpty()) 
		{
			System.out.println("Can Not remove because it is empty...");
			return;
		}
		else 
		{
			int index = -1;

			//Search for guest index
			for(int i = 0 ; i < currentNumberGuest; i++) 
			{
				if(guest.equals(guestList[i])) 
				{
					index = i;
					System.out.println("Done Remove: " + i + "From the List.");
					break;
				}
			}
			if(index ==-1)
				return;
			else 
			{
				//Shift elements after removed guest
				for(int i = index ; i<  currentNumberGuest-1 ; i++) 
				{
					guestList[i] = guestList[i+1];
				}
				guestList[currentNumberGuest-1] = null;
				currentNumberGuest--;
			}
				
		}
	}

	//Remove multiple guests
	@Override
	public  void RemoveGuests(Guest[] guestS) 
	{
		if(guestS == null)
			return;
		for(int i = 0 ; i< guestS.length;i++) 
		{
			if(guestS != null) 
			{
				RemoveGuest(guestS[i]);
			}
		}
	}

	//Display room info
	@Override
	public void Display()
    {
		System.out.println("Room type: Standard Room");
		super.Display();
	}
	
}
