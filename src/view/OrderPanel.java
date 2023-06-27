package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OrderPanel extends JPanel {

    public OrderPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // add components to represent orders
        // update these components as new orders are created
    }

    public JButton getNewOrderButton() {
        return null;
    }

    public void updateOrders(Object orders) {
    }
}
