package view;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Lageristen-Spiel");
        setSize(1440, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // center the frame
    }
}