public class Suite extends Room 
{
	public Suite(double roomSize) 
    {
		super(roomSize, 4);
	}

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
			System.out.println("Can Not add guest the suite is Full");
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
					currentHotel.AddProfit(finalPrice);
					guestList[i].setBalance(guestList[i].getBalance() - finalPrice);
					if (guestList[i] instanceof MVP)
					{
						double savings = (currentHotel.GetPricePerDay() * days) - finalPrice;
						int freeDaysUsed = (int) (savings / currentHotel.GetPricePerDay());
    					((MVP)(guestList[i])).SetLoyaltyPoints(((MVP)(guestList[i])).GetLoyaltyPoints() - (freeDaysUsed * 10));
						System.out.println(guestList[i].getName() + " Succesfully used " + freeDaysUsed * 10 + " Loyalty points to save " + savings + " Riyals.");
                    	System.out.println("Loyalty points left: " + ((MVP)(guestList[i])).GetLoyaltyPoints() + "\n");
					}
					else if (guestList[i] instanceof VIP)
					{
						double savings = (currentHotel.GetPricePerDay() * days) - finalPrice;
						int freeDaysUsed = (int) (savings / currentHotel.GetPricePerDay());
    					((VIP)(guestList[i])).SetLoyaltyPoints(((VIP)(guestList[i])).GetLoyaltyPoints() - (freeDaysUsed * 20));
						System.out.println(guestList[i].getName() + " Succesfully used " + freeDaysUsed * 20 + " Loyalty points to save " + savings + " Riyals.");
						System.out.println("Loyalty points left: " + ((VIP)(guestList[i])).GetLoyaltyPoints() + "\n");
					}
					currentNumberGuest++;
					System.out.println(guestList[i].getName() + " Paid " + finalPrice + " Riyals.");
					System.out.println(guestList[i].getName() + " Succesfully booked into Room No." + roomNo);
					Display();
					return;
				}
			}
		}
	}

	@Override
	public  void AddGuests(Guest[] guestS, int days) 
    {
		if(guestS == null) 
        {
			System.out.println("Vaild Value.....");   	
			return;
        }
		int count = 0;
		for(int i =0; i< guestS.length;i++) 
        {
			if(guestS != null)
				count++;
		}
			if(count + currentNumberGuest > guestCapacity) 
            {
				System.out.println("Error The Number of new guest Bigger than the size...");
				return;
            }
		for(int i = 0; i<guestS.length;i++) 
        {
			if(guestS != null)
				AddGuest(guestS[i], days);
		}
	
	}

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
				for(int i = index ; i<  currentNumberGuest-1 ; i++) 
                {
					guestList[i] = guestList[i+1];
				}

				guestList[currentNumberGuest-1] = null;
				currentNumberGuest--;
			}
				
		}
	}

	@Override
	public  void RemoveGuest(Guest[] guestS) 
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
	
	public boolean hasMVP() {
		for (int i = 0; i < currentNumberGuest; i++) {
			if (guestList[i] != null && guestList[i] instanceof MVP) {
				return true;
			}
		}
		return false;
	}

	public boolean hasFreeRoomService() {
		return hasMVP();
	}

	@Override
	public void Display()
    {
		System.out.println("Room type: Suite");
		System.out.println("Has free Room Service?: " + hasFreeRoomService());
		super.Display();
	}
}
