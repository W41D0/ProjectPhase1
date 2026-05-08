import javax.swing.*;
import java.awt.*;
public class HotelGUI 
{
    private JFrame frame;
    private JPanel mainContainer;
    private CardLayout cardLayout;

    private Hotel hotel1;
    private Hotel hotel2;
    private Resort resort;

    
    public HotelGUI(Hotel hotel1, Hotel hotel2, Resort resort) 
    {
        this.hotel1 = hotel1;
        this.hotel2 = hotel2;
        this.resort = resort;
        
        //making the window
        frame = new JFrame("Hotel Management System"); 
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout(); //cardLayout holds the menus 
        mainContainer = new JPanel(cardLayout); //JPanel holds the cardLayout so that the frame can see cards

        //adds the menus to the mainContainer
        mainContainer.add(mainMenu(), "MainMenu");
        mainContainer.add(managerMenu(), "ManagerMenu");

        //adding mainContainer (and cards) then setting window to be visible
        frame.add(mainContainer);
        frame.setVisible(true);
    }

    private JPanel mainMenu()
    {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));//panel holds 3 items ontop of each other
        panel.setBorder(BorderFactory.createEmptyBorder(200, 400, 200, 400));//making border so that nothings too big
        
        //making the items in the window
        JLabel titleLabel = new JLabel("Welcome to Hotel Management System", SwingConstants.CENTER);
        JButton managerButton = new JButton("1. Travel Manager");
        JButton guestButton = new JButton("2. Sign up as a Guest");

        //giving buttons their functions
        managerButton.addActionListener(e -> cardLayout.show(mainContainer, "ManagerMenu"));
        guestButton.addActionListener(e -> cardLayout.show(mainContainer, "GuestMenu"));

        //adding them to the panel
        panel.add(titleLabel);
        panel.add(managerButton);
        panel.add(guestButton);
        return panel;
    }

    private JPanel managerMenu()
    {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10)); //cardLayout holds the menus 
        panel.setBorder(BorderFactory.createEmptyBorder(150, 400, 150, 400));//making border so that nothings too big


        //making the items in the window
        JLabel titleLabel = new JLabel("Manager Menu", SwingConstants.CENTER);

        //this is a dropDown menu
        String[] hotelNames = {hotel1.getName(), hotel2.getName(), resort.getName()};
        JComboBox<String> hotelDropdown = new JComboBox<>(hotelNames);

        JButton displayButton = new JButton("Display Details");
        JButton addRoomButton = new JButton("Add a Room");
        JButton backButton = new JButton("Back to Main Menu");
        
        //finctionality of display
        displayButton.addActionListener(e -> 
        {
            String selectedHotel = (String) hotelDropdown.getSelectedItem();
            String hotelInfo;
            if (selectedHotel.equals(hotel1.getName())) 
                hotelInfo = hotel1.getDetails();

            else if (selectedHotel.equals(hotel2.getName())) 
                hotelInfo = hotel2.getDetails();

            else 
                hotelInfo = resort.getDetails();
            
            //shows the details
            JTextArea textArea = new JTextArea(30, 60);
            textArea.setText(hotelInfo);
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(frame, scrollPane, selectedHotel + " Details", JOptionPane.INFORMATION_MESSAGE);
        });
        
        //making the functionality of add Room Button
        addRoomButton.addActionListener(e -> 
        {
            String selectedHotel = (String) hotelDropdown.getSelectedItem(); //gets dropdown option
            
            //doesnt continue if Restort
            if (selectedHotel.contains("Resort")) 
            {
                JOptionPane.showMessageDialog(frame, "You cannot add rooms to a Resort.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Checks what room type you want
            String[] roomTypes = {"Standard", "Suite"};
            int typeChoice = JOptionPane.showOptionDialog(frame, "Select Room Type", "Room Setup", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, roomTypes, roomTypes[0]);

            //if X isnt pressed
            if (typeChoice >= 0)
            {
                String roomSizeInput = JOptionPane.showInputDialog(frame, "Enter Room Size:");

                if (roomSizeInput != null && !roomSizeInput.isEmpty())
                {
                    try
                    {
                        //validates room size is correct
                        double size = Double.parseDouble(roomSizeInput);
                        if (size <= 0)
                        {
                            JOptionPane.showMessageDialog(frame, "Room size must be greater than 0!", "Invalid Size", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        //grabs chosen hotel
                        Hotel hotel;
                        if (selectedHotel.contains(hotel1.getName()))
                            hotel = hotel1;
                        else
                            hotel = hotel2;

                        //adds room to the hote;
                        String chosenType = roomTypes[typeChoice];
                        if (chosenType.equals("Standard"))
                            hotel.AddRoom(new StandardRoom(size));
                        else
                            hotel.AddRoom(new Suite(size));


                        JOptionPane.showMessageDialog(frame, "Succesfully added " + chosenType + "("+ roomSizeInput+"m^2) to " + selectedHotel);
                    }
                    catch (NumberFormatException ex)
                    {
                        //pop up if Room Size is 0 or less.
                        JOptionPane.showMessageDialog(frame, "Please enter a valid whole number for the size.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainContainer, "MainMenu"));

        panel.add(titleLabel);
        panel.add(hotelDropdown);
        panel.add(displayButton);
        panel.add(addRoomButton);
        panel.add(backButton);
        return panel;
    }
}
