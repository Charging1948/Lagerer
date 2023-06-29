package view;

import model.Position;
import model.Product;

import javax.swing.*;

import interfaces.WarehousePanelDelegate;

import java.awt.*;
import java.awt.event.*;

public class WarehousePanel extends JPanel {

    private static final String UNOCCUPIED_SPACE = "<html><body><h1>Empty</h1></body></html>";
    private JButton[][] grid;
    private WarehousePanelDelegate delegate;

    public WarehousePanel(int height, int width) {
        this.setLayout(new GridLayout(height, width));
        this.grid = new JButton[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                JButton button = new JButton();
                button.setText(UNOCCUPIED_SPACE);
                this.grid[i][j] = button;
                this.add(button);
            }
        }
    }

    public void setDelegate(WarehousePanelDelegate delegate) {
        this.delegate = delegate;
    }

    // This method accepts a Product or null Object and x and y coordinates
    public void updateGridSpot(Product product, Position position) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JButton button = grid[position.getX()][position.getY()];

                if (product != null) {
                    button.setText(product.toHTMLString());
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (delegate != null) {
                                delegate.confirmRemoveProduct(product, position);
                            }
                        }
                    });

                } else {
                    button.setText(UNOCCUPIED_SPACE);
                    for (ActionListener al : button.getActionListeners()) {
                        button.removeActionListener(al);
                    }
                }
                revalidate();
                repaint();
            }
        });
    }
}