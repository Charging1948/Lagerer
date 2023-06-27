package view;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class BalanceDialog extends JDialog {

    public BalanceDialog(JFrame parentFrame) {
        super(parentFrame, "Bilanz", true);
        setSize(500, 500);
        setLocationRelativeTo(parentFrame);
        // add components to display the detailed balance
    }
}
