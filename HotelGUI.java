import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class HotelGUI
{
    private JFrame frame;
    private JPanel mainContainer;
    private CardLayout cardLayout;

    private Hotel hotel1;
    private Hotel hotel2;
    private Resort resort;

    LinkedList<Guest> guestList = new LinkedList<>();
    boolean listHasVIP = false;
    private final String FILE_NAME = "Hotels.dat";

    Font mainFont = new Font("sansSerif", Font.BOLD, 34);
    Font buttonFont = new Font("sansSerif", Font.BOLD, 24);
    Font displayFont = new Font("Monospaced", Font.PLAIN, 28);

    
    public HotelGUI(Hotel hotel1, Hotel hotel2, Resort resort) 
    {
        loadData(hotel1, hotel2, resort);
        
        //making the window
        frame = new JFrame("Hotel Management System"); 
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                saveData();
                System.exit(0);
            }
        });

        cardLayout = new CardLayout(); //cardLayout holds the menus 
        mainContainer = new JPanel(cardLayout); //JPanel holds the cardLayout so that the frame can see cards

        //adds the menus to the mainContainer
        mainContainer.add(mainMenu(), "MainMenu");
        mainContainer.add(managerMenu(), "ManagerMenu");
        mainContainer.add(guestMenu(), "GuestMenu");

        //adding mainContainer (and cards) then setting window to be visible
        frame.add(mainContainer);
        frame.setVisible(true);
    }

    private void saveData() 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) 
        {
            oos.writeObject(hotel1);
            oos.writeObject(hotel2);
            oos.writeObject(resort);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "SAVE FAILED: " + e.toString(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadData(Hotel h1, Hotel h2, Resort r) 
    {
        File f = new File(FILE_NAME);
        if (f.exists()) 
        {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) 
            {
                hotel1 = (Hotel) ois.readObject();
                hotel2 = (Hotel) ois.readObject();
                resort = (Resort) ois.readObject();
            } 
            catch (Exception e) 
            {
                hotel1 = h1; hotel2 = h2; resort = r;
            }
        } 
        else 
        {
            hotel1 = h1; hotel2 = h2; resort = r;
        }
    }

    //--------------------------------------------------------------
    //                      MAIN MENU
    //--------------------------------------------------------------
    private JPanel mainMenu()
    {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));//panel holds 3 items ontop of each other
        panel.setBorder(BorderFactory.createEmptyBorder(200, 400, 200, 400));//making border so that nothings too big
        
        //making the items in the window
        JLabel titleLabel = new JLabel("Welcome to Hotel Management System", SwingConstants.CENTER);
        JButton managerButton = new JButton("1. Travel Manager");
        JButton guestButton = new JButton("2. Sign up as a Guest");
        titleLabel.setFont(mainFont);
        managerButton.setFont(buttonFont);
        guestButton.setFont(buttonFont);

        //giving buttons their functions
        managerButton.addActionListener(e -> cardLayout.show(mainContainer, "ManagerMenu"));
        guestButton.addActionListener(e -> cardLayout.show(mainContainer, "GuestMenu"));

        //adding them to the panel
        panel.add(titleLabel);
        panel.add(managerButton);
        panel.add(guestButton);
        return panel;
    }

    //--------------------------------------------------------------
    //                      MANAGER MENU
    //--------------------------------------------------------------
    private JPanel managerMenu()
    {   
        //LAYOUT--------------------------------------------------------------------------------------
        JPanel dashboardPanel = new JPanel(new BorderLayout(10,10));//dashboard on right
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(50, 250, 50, 250));//dashboard borders
        JPanel controlsPanel = new JPanel(new GridLayout(5, 1, 50, 50)); //layout holds the menu items 

        //making the items in the window---------------------------------------------------------------
        JLabel titleLabel = new JLabel("Manager Menu", SwingConstants.CENTER);
        titleLabel.setFont(mainFont);
        //this is a dropDown menu
        String[] hotelNames = {hotel1.getName(), hotel2.getName(), resort.getName()};
        JComboBox<String> hotelDropdown = new JComboBox<>(hotelNames);
        hotelDropdown.setFont(buttonFont);
        //buttons
        JButton displayButton = new JButton("Display Details");
        JButton addRoomButton = new JButton("Add a Room");
        JButton backButton = new JButton("Back to Main Menu");
        displayButton.setFont(buttonFont);
        addRoomButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
        //text box
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setText("Welcome Manager. What would you like to manage?");
        displayArea.setFont(buttonFont);
        JScrollPane displayPane = new JScrollPane(displayArea);
        
        //finctionality of display===================================================================
        displayButton.addActionListener(e -> 
        {
            //checks which option is on the dropdown menu before displaying details
            String selectedHotel = (String) hotelDropdown.getSelectedItem();
            String hotelInfo;
            if (selectedHotel.equals(hotel1.getName())) 
                hotelInfo = hotel1.getDetails();

            else if (selectedHotel.equals(hotel2.getName())) 
                hotelInfo = hotel2.getDetails();

            else 
                hotelInfo = resort.getDetails();
            
            //displays the details
            displayArea.setText(hotelInfo);
        });
        
        //making the functionality of add Room===============================================================
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

                        
                        displayArea.setText("Succesfully added " + chosenType + "("+ roomSizeInput+"m^2) as Room No." + hotel.GetCurrentNumberOfRooms() + " to " + selectedHotel);
                    }
                    catch (NumberFormatException ex)
                    {
                        //pop up if Room Size is 0 or less.
                        JOptionPane.showMessageDialog(frame, "Please enter a valid whole number for the size.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //functionality of backButton===================================================================
        backButton.addActionListener(e -> cardLayout.show(mainContainer, "MainMenu"));

        //adding everything to the panels---------------------------------------------------------------
        controlsPanel.add(titleLabel);
        controlsPanel.add(hotelDropdown);
        controlsPanel.add(displayButton);
        controlsPanel.add(addRoomButton);
        controlsPanel.add(backButton);

        dashboardPanel.add(controlsPanel, BorderLayout.WEST);
        dashboardPanel.add(displayPane, BorderLayout.CENTER);
        return dashboardPanel;
    }

    //--------------------------------------------------------------
    //                      GUEST MENU
    //--------------------------------------------------------------
    private JPanel guestMenu()
    {
        
        JPanel dashboardPanel = new JPanel(new BorderLayout(20,20));
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));//dashboard borders
        JPanel controlsPanel = new JPanel(new GridLayout(7, 2, 10, 10)); //layout holds the menu items 
        
        //Title
        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JLabel titleLabel = new JLabel("Guest Sign-Up", SwingConstants.CENTER);
        titleLabel.setFont(mainFont);
        dashboardPanel.add(titleLabel, BorderLayout.NORTH);

        //Guest Mode Panel
        JPanel modePanel = new JPanel(new FlowLayout());
        JLabel modeLabel = new JLabel("Booking Mode:");
        modeLabel.setFont(mainFont);
        modePanel.add(modeLabel);
        String[] modes = {"Single Booking", "Group Booking"};
        JComboBox<String> modeDropdown = new JComboBox<>(modes);
        modeDropdown.setFont(buttonFont);
        modePanel.add(modeDropdown);

        titlePanel.add(titleLabel);
        titlePanel.add(modePanel);
        dashboardPanel.add(titlePanel, BorderLayout.NORTH);

        
        //making all the fields-----------------------------------------------------------------------------
        JLabel nameLabel = new JLabel("Guest Name:");
        JTextField nameField = new JTextField();
        nameLabel.setFont(mainFont);
        nameField.setFont(mainFont);

        JLabel guestTypeLabel = new JLabel("Guest Type:");
        String[] guestType = {"Regular", "VIP", "MVP"};
        JComboBox<String> typeDropdown = new JComboBox<>(guestType);
        guestTypeLabel.setFont(mainFont);
        typeDropdown.setFont(buttonFont);

        JLabel balanceLabel = new JLabel("Balance ($):");
        JTextField balanceField = new JTextField();
        balanceLabel.setFont(mainFont);
        balanceField.setFont(mainFont);

        JLabel pointsLabel = new JLabel("Loyalty Points:");
        JTextField pointsField = new JTextField();
        pointsField.setEnabled(false);
        pointsLabel.setFont(mainFont);
        pointsField.setFont(mainFont);

        JLabel hotelLabel = new JLabel("Select Hotel:");
        String[] hotelNames = {hotel1.getName(), hotel2.getName()}; 
        JComboBox<String> hotelDropdown = new JComboBox<>(hotelNames);
        hotelDropdown.setEnabled(true);
        modeDropdown.setEnabled(true);
        hotelLabel.setFont(mainFont);
        hotelDropdown.setFont(buttonFont);

        //buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton backButton = new JButton("Back to Main Menu");
        JButton addGuestButton = new JButton("Add Guest to Group");
        JButton submitButton = new JButton("Submit");
        addGuestButton.setVisible(false);
        backButton.setFont(buttonFont);
        submitButton.setFont(buttonFont);
        addGuestButton.setFont(buttonFont);
        buttonPanel.add(backButton);
        buttonPanel.add(addGuestButton);
        buttonPanel.add(submitButton);


        //making the text field area-------------------------------------------------------------------------
        JTextArea displayField = new JTextArea(10,70);
        displayField.setEditable(false);
        displayField.setFont(displayFont);
        displayField.setText("> Guest Sign up... \n");
        JScrollPane displayPane = new JScrollPane(displayField);

        //making so that VIP and MVP activate points field---------------------------------------------------
        typeDropdown.addActionListener(e ->
        {
            String selectedType = (String)typeDropdown.getSelectedItem();

            if (selectedType.contains("Regular"))
            {
                pointsField.setEnabled(false);
                pointsField.setText("");
            }
            else
                pointsField.setEnabled(true);
        });

        //logic for single and multiple guests
        modeDropdown.addActionListener(e->
        {
            String selectedMode = (String) modeDropdown.getSelectedItem();
            if (selectedMode.equals("Group Booking")) 
            {
                addGuestButton.setVisible(true);
                displayField.setText("");
                submitButton.setText("Finalize Group Booking");
                displayField.append("Switched to Group Booking. Add guests, then finalize.\n");
            }
            else 
            {
                addGuestButton.setVisible(false);
                displayField.setText("");
                submitButton.setText("Submit Booking");
                guestList.clear();
                listHasVIP = false;
                hotelDropdown.setEnabled(true);
                modeDropdown.setEnabled(true);
                displayField.append("Switched to Single Booking.\n");
            }
        });

        addGuestButton.addActionListener(e -> 
        {
            //gets all the user info---------------------------------------
            String name = nameField.getText().trim();
            String typeInput = (String) typeDropdown.getSelectedItem();
            String hotelInput = (String) hotelDropdown.getSelectedItem();
            String balanceInput = balanceField.getText().trim();
            String pointsInput = pointsField.getText().trim();

            //validates info------------------------------------------
            if(name.isEmpty())
            {
                displayField.append("ERROR: Please enter a guest name.\n");
                return;
            }

            if (balanceInput.isEmpty())
            {
                displayField.append("ERROR: Balance cannot be empty.\n");
                return;
            }

            double balance = 0;
            int points = 0;
            try
            {
                balance = Double.parseDouble(balanceInput);

                if (balance < 0)
                {
                    displayField.append("ERROR: Balance cannot be less than 0.\n");
                    return;
                }
            }
            catch(NumberFormatException ex)
            {
                displayField.append("ERROR: Balance must be a valid number.\n");
                return;
            }

            if (pointsField.isEnabled() && !pointsInput.isEmpty()) 
            {
                try
                {
                    points = Integer.parseInt(pointsInput);
                }
                catch(NumberFormatException ex)
                {
                    displayField.append("ERROR: Loyalty points cannot be less than 0.\n");
                    return;
                }
            }

            //creates user Guest account----------------------------------------
            Guest userGuest;
            if (typeInput.contains("VIP")) 
            {
                userGuest = new VIP(name, balance, points);
                listHasVIP = true;
            } 
            else if (typeInput.contains("MVP")) 
            {
                userGuest = new MVP(name, balance, points);
                listHasVIP = true;
            } 
            else 
            {
                userGuest = new Guest(name, balance);
            }

            guestList.insertAtBack(userGuest);
            displayField.append("Guest No." + guestList.length() + ": " + userGuest.getName() + " was added.\n");
            nameField.setText("");
            balanceField.setText("0");
            pointsField.setText("");
            typeDropdown.setSelectedIndex(0);
            hotelDropdown.setEnabled(false);
            modeDropdown.setEnabled(false);
        });

        //this allows me to put system.out.print into diplay Field
        PrintStream printStream = new PrintStream(new OutputStream() 
        {
            @Override
            public void write(int b) 
            {
                displayField.append(String.valueOf((char) b));
                displayField.setCaretPosition(displayField.getDocument().getLength());
            }
        });
        System.setOut(printStream);
        System.setErr(printStream);

        //functionality of Submit Button---------------------------------------------------------------------
        submitButton.addActionListener(e -> 
        {
            //gets all the user info---------------------------------------
            String name = nameField.getText().trim();
            String typeInput = (String) typeDropdown.getSelectedItem();
            String hotelInput = (String) hotelDropdown.getSelectedItem();
            String balanceInput = balanceField.getText().trim();
            String pointsInput = pointsField.getText().trim();

            //validates info------------------------------------------
            if(guestList.isEmpty() && name.isEmpty())
            {
                displayField.append("ERROR: Please enter a guest name.\n");
                return;
            }

            if (guestList.isEmpty() && balanceInput.isEmpty())
            {
                displayField.append("ERROR: Balance cannot be empty.\n");
                return;
            }

            double balance = 0;
            int points = 0;
            try
            {
                balance = Double.parseDouble(balanceInput);

                if (balance < 0)
                {
                    displayField.append("ERROR: Balance cannot be less than 0.\n");
                    return;
                }
            }
            catch(NumberFormatException ex)
            {
                displayField.append("ERROR: Balance must be a valid number.\n");
                return;
            }

            if (pointsField.isEnabled() && !pointsInput.isEmpty()) 
            {
                try
                {
                    points = Integer.parseInt(pointsInput);
                }
                catch(NumberFormatException ex)
                {
                    displayField.append("ERROR: Loyalty points cannot be less than 0.\n");
                    return;
                }
            }

            //creates user Guest account----------------------------------------
            Guest userGuest;
            if (typeInput.contains("VIP")) 
            {
                userGuest = new VIP(name, balance, points);
            } 
            else if (typeInput.contains("MVP")) 
            {
                userGuest = new MVP(name, balance, points);
            } 
            else 
            {
                userGuest = new Guest(name, balance);
            }

            //asks for number of days
            String daysInput = JOptionPane.showInputDialog(dashboardPanel, "How many days would you like to book at " + hotelInput + "?", "Book Stay", JOptionPane.QUESTION_MESSAGE);

            //validates days input---------------------------------------
            if (daysInput != null && !daysInput.trim().isEmpty()) 
            {
                try 
                {
                    int days = Integer.parseInt(daysInput.trim());
                    
                    if (days <= 0) 
                    {
                        JOptionPane.showMessageDialog(dashboardPanel, "Number of days must be at least 1.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        displayField.append("BOOKING CANCELLED: Invalid number of days entered.\n");
                    } 
                    //books guest to hotel------------------
                    else 
                    {   
                        displayField.setText("");
                        Hotel hotel;
                        if(hotelInput.contains(hotel1.getName()))
                            hotel = hotel1;
                        else
                            hotel = hotel2;
                        
                        String selectedMode = (String) modeDropdown.getSelectedItem();
                        if(selectedMode.contains("Group Booking"))
                        {
                            Guest[] guestArr = new Guest[guestList.length()];
                            for(int i = 0; i < guestList.length(); i++)
                            {
                                guestArr[i] = guestList.getData(i);
                            }
                            if (listHasVIP)
                            {
                                hotel.availableSuite().AddGuests(guestArr, days);
                                listHasVIP = false;
                            }
                            else
                            {
                                hotel.availableRoom().AddGuests(guestArr, days);
                            }
                        }
                        else
                        {
                            //books single guest InsufficientBalanceException propagated from bookHotel() and handled here
                            try
                            {
                                userGuest.bookHotel(hotel, days);
                            }
                            catch (InsufficientBalanceException ex)
                            {
                                displayField.append("BOOKING FAILED: " + ex.getMessage() + "\n");
                                JOptionPane.showMessageDialog(dashboardPanel, ex.getMessage(), "Insufficient Balance", JOptionPane.ERROR_MESSAGE);
                            }

                        }
                        hotelDropdown.setEnabled(true);
                        modeDropdown.setEnabled(true);
                    }
                    
                } 
                catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(dashboardPanel, "Please enter a valid whole number for days.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    displayField.append("BOOKING CANCELLED: Invalid text entered for days.\n");
                }
            } 
            else 
            {
                // If they hit Cancel on the popup
                displayField.append("BOOKING CANCELLED: User cancelled the days prompt.\n");
            }

            nameField.setText("");
            balanceField.setText("");
            pointsField.setText("");
            typeDropdown.setSelectedIndex(0);
        });

        backButton.addActionListener(e -> cardLayout.show(mainContainer, "MainMenu"));

        controlsPanel.add(nameLabel);       
        controlsPanel.add(nameField);
        controlsPanel.add(guestTypeLabel);  
        controlsPanel.add(typeDropdown);
        controlsPanel.add(balanceLabel);    
        controlsPanel.add(balanceField);
        controlsPanel.add(pointsLabel);     
        controlsPanel.add(pointsField);
        controlsPanel.add(hotelLabel);
        controlsPanel.add(hotelDropdown);
        controlsPanel.add(new JLabel(""));
        controlsPanel.add(buttonPanel);

        dashboardPanel.add(controlsPanel, BorderLayout.CENTER);
        dashboardPanel.add(displayPane, BorderLayout.SOUTH);

        return dashboardPanel;
    }
}
