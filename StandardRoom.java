package ProjectPhase1;

public class StandardRoom extends Room
{

	public StandardRoom(double roomSize) 
    {
		super(roomSize, 2);
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
			System.out.println("Can Not add geust the room is Full");
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
					currentNumberGuest++;
					System.out.println("Done Add "+ i + " To The list.");
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
			System.out.println("Error...");   	
    		return;
        }

		int count = 0;
    	for(int i = 0; i< guestS.length; i++) 
        {
    		if(guestS[i] != null && currentHotel.calculatePrice(guestS[i], days) < guestS[i].getBalance())
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
   
    
}
