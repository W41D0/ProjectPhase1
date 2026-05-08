import javax.swing.*;
import java.awt.*;

public class HotelSystemGUI {

    // Declare layout and main container at the class level so listeners can access them
    private static CardLayout cardLayout = new CardLayout();
    private static JPanel mainContainer = new JPanel(cardLayout);
    private static JTextArea outputLog = new JTextArea(8, 40);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hotel Management System");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Allows us to pin the log to the bottom

        // Setup the universal output log (Replaces System.out.println)
        outputLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputLog); // Adds a scrollbar
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Build the Menu Cards
        JPanel mainMenu = createMainMenu();
        JPanel managerMenu = createManagerMenu();
        JPanel guestMenu = createGuestMenu();

        // Add cards to the deck
        mainContainer.add(mainMenu, "Main");
        mainContainer.add(managerMenu, "Manager");
        mainContainer.add(guestMenu, "Guest");

        // Add the deck to the center of the window
        frame.add(mainContainer, BorderLayout.CENTER);

        log("System Started. Welcome to the Hotel Management System.");
        frame.setVisible(true);
    }

    // ==========================================
    // CARD 1: MAIN MENU
    // ==========================================
    private static JPanel createMainMenu() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 col, with gaps
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adds padding

        JLabel title = new JLabel("Choose your Option:", SwingConstants.CENTER);
        JButton managerBtn = new JButton("1. Travel Manager");
        JButton guestBtn = new JButton("2. Sign up as a Guest");

        managerBtn.addActionListener(e -> cardLayout.show(mainContainer, "Manager"));
        guestBtn.addActionListener(e -> cardLayout.show(mainContainer, "Guest"));

        panel.add(title);
        panel.add(managerBtn);
        panel.add(guestBtn);
        return panel;
    }

    // ==========================================
    // CARD 2: MANAGER MENU
    // ==========================================
    private static JPanel createManagerMenu() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Manager Portal", SwingConstants.CENTER);
        
        // JComboBox is a dropdown menu!
        String[] properties = {"Hilton Hotel", "Burj Al Arab Hotel", "Rosewood Resort"};
        JComboBox<String> propertyDropdown = new JComboBox<>(properties);

        JButton displayBtn = new JButton("Display Details");
        JButton addRoomBtn = new JButton("Add a Room");
        JButton backBtn = new JButton("Back to Main Menu");

        // Action: Display
        displayBtn.addActionListener(e -> {
            String selected = (String) propertyDropdown.getSelectedItem();
            log("Displaying details for: " + selected);
            // IN YOUR CODE: if selected == "Hilton", call Hilton.Display()
        });

        // Action: Add Room using OptionPanes (Popups) instead of new cards
        addRoomBtn.addActionListener(e -> {
            String selected = (String) propertyDropdown.getSelectedItem();
            if (selected.contains("Resort")) {
                log("Cannot add rooms to a Resort from this menu.");
                return;
            }

            // Popup 1: Choose Type
            String[] options = {"Standard", "Suite"};
            int typeChoice = JOptionPane.showOptionDialog(panel, "Select Room Type:", "Add Room",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            // Popup 2: Enter Size
            String sizeInput = JOptionPane.showInputDialog(panel, "Enter Room Size:");
            
            if (sizeInput != null && typeChoice >= 0) {
                String roomType = options[typeChoice];
                log("Added a " + roomType + " room of size " + sizeInput + " to " + selected);
                // IN YOUR CODE: Parse sizeInput to double, then chosenHotel.AddRoom(new StandardRoom(size))
            }
        });

        backBtn.addActionListener(e -> cardLayout.show(mainContainer, "Main"));

        panel.add(title);
        panel.add(propertyDropdown);
        panel.add(displayBtn);
        panel.add(addRoomBtn);
        panel.add(backBtn);
        return panel;
    }

    // ==========================================
    // CARD 3: GUEST SIGNUP MENU
    // ==========================================
    private static JPanel createGuestMenu() {
        // Using a GridLayout to make a neat form
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form Fields
        JTextField nameField = new JTextField();
        JTextField balanceField = new JTextField();
        JTextField pointsField = new JTextField();
        JTextField nightsField = new JTextField();
        
        String[] guestTypes = {"Regular", "VIP", "MVP"};
        JComboBox<String> typeDropdown = new JComboBox<>(guestTypes);
        
        String[] hotels = {"Hilton Hotel", "Burj Al Arab Hotel"};
        JComboBox<String> hotelDropdown = new JComboBox<>(hotels);

        JButton bookBtn = new JButton("Book Stay");
        JButton backBtn = new JButton("Back");

        // Add components to panel (Label on left, Input on right)
        panel.add(new JLabel("Name:"));             panel.add(nameField);
        panel.add(new JLabel("Guest Type:"));       panel.add(typeDropdown);
        panel.add(new JLabel("Balance ($):"));      panel.add(balanceField);
        panel.add(new JLabel("Loyalty Points:"));   panel.add(pointsField);
        panel.add(new JLabel("Select Hotel:"));     panel.add(hotelDropdown);
        panel.add(new JLabel("Number of Nights:")); panel.add(nightsField);
        panel.add(backBtn);                         panel.add(bookBtn);

        // Action: Book
        bookBtn.addActionListener(e -> {
            String name = nameField.getText();
            String type = (String) typeDropdown.getSelectedItem();
            String hotel = (String) hotelDropdown.getSelectedItem();
            String nights = nightsField.getText();
            
            log("Booking Confirmed! " + name + " (" + type + ") booked " + nights + " nights at " + hotel);
            // IN YOUR CODE: 
            // 1. Grab balance/points text, convert to numbers (Double.parseDouble)
            // 2. Create your Guest object
            // 3. Call userGuest.bookHotel()
            
            // Clear fields after booking
            nameField.setText(""); balanceField.setText(""); pointsField.setText(""); nightsField.setText("");
        });

        backBtn.addActionListener(e -> cardLayout.show(mainContainer, "Main"));
        return panel;
    }

    // Helper method to easily print to our GUI console
    private static void log(String message) {
        outputLog.append("> " + message + "\n");
        // Auto-scroll to the bottom
        outputLog.setCaretPosition(outputLog.getDocument().getLength());
    }
}