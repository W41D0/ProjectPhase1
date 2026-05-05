import javax.swing.*;
import java.awt.*;

public class MenuDemoGUI {

    public static void main(String[] args) {
        // 1. Create the Main Window
        JFrame frame = new JFrame("Nested Menu GUI Demo");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2. Setup the CardLayout Container
        // This is the "deck" that holds all our different menu screens
        CardLayout cardLayout = new CardLayout();
        JPanel mainContainer = new JPanel(cardLayout);

        // ==========================================
        // CARD 1: The Main Menu
        // ==========================================
        JPanel mainMenuPanel = new JPanel();
        
        JLabel welcomeLabel = new JLabel("Welcome to the Main Menu!");
        JButton goToOption1Button = new JButton("Go to Data Entry (Option 1)");
        
        mainMenuPanel.add(welcomeLabel);
        mainMenuPanel.add(goToOption1Button);

        // ==========================================
        // CARD 2: The Data Entry Screen (Option 1)
        // ==========================================
        JPanel dataEntryPanel = new JPanel();
        
        JLabel promptLabel = new JLabel("Enter your name:");
        JTextField nameInputField = new JTextField(15); // Width of 15 columns
        JButton submitButton = new JButton("Submit");
        JTextArea outputArea = new JTextArea(5, 20); // 5 rows, 20 columns
        outputArea.setEditable(false); // Make it so the user can't type in the output box
        JButton backButton = new JButton("Back to Main Menu");

        dataEntryPanel.add(promptLabel);
        dataEntryPanel.add(nameInputField);
        dataEntryPanel.add(submitButton);
        dataEntryPanel.add(outputArea);
        dataEntryPanel.add(backButton);

        // ==========================================
        // Add both panels to the CardLayout "Deck"
        // ==========================================
        // We give each card a String name so we can call it later
        mainContainer.add(mainMenuPanel, "MainMenu");
        mainContainer.add(dataEntryPanel, "DataEntryMenu");

        // ==========================================
        // Add The Event Listeners (The Navigation & Logic)
        // ==========================================
        
        // Navigation: Main Menu -> Data Entry
        goToOption1Button.addActionListener(e -> cardLayout.show(mainContainer, "DataEntryMenu"));

        // Navigation: Data Entry -> Main Menu
        backButton.addActionListener(e -> {
            cardLayout.show(mainContainer, "MainMenu");
        });

        // Logic: Grabbing input and showing output
        submitButton.addActionListener(e -> {
            // Grab the text from the JTextField (Like scanner.nextLine())
            String userInput = nameInputField.getText();
            
            if (!userInput.trim().isEmpty()) {
                // Print it to the JTextArea (Like System.out.println())
                outputArea.append("Hello, " + userInput + "!\n");
                
                // Clear the input box for the next entry
                nameInputField.setText("");
            }
        });

        // ==========================================
        // Final Setup
        // ==========================================
        frame.add(mainContainer);
        frame.setVisible(true);
    }
}