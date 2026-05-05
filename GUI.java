import javax.swing.*;
import java.awt.event.*;

public class GUI 
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Mongus");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton button = new JButton("Click Me!");

           

            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)   
                {
                    JOptionPane.showMessageDialog(frame, "yo");
                } 
            });

            frame.getContentPane().add(button);



        frame.setVisible(true);
    }
}
