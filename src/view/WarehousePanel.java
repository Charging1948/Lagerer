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
    private boolean isOrderSelectMode = false;

    public WarehousePanel(int height, int width) {
        this.setLayout(new GridLayout(height, width));
        this.grid = new JButton[height][width];

        for (int i = 0; i < height; i++) { // start from the last row
            for (int j = 0; j < width; j++) {

                JButton button = new JButton();
                button.setText(UNOCCUPIED_SPACE);
                this.grid[i][j] = button;
                this.add(button);
            }
        }
    }

    /*
     * This method is called when the user selects an outgoing order from the OrderPanel list.
     * It highlights the locations of the matching products in the warehouse.
     * It also removes the highlight from any other locations.
     */
    public void highlightMatchingProductLocations(Product product) {
        boolean hasMatchingProduct = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                JButton button = grid[i][j];
                if (button.getText().equals(product.toHTMLString())) {
                    hasMatchingProduct = true;
                    button.setOpaque(true);
                    button.setBackground(Color.lightGray); // highlight color
                    removeActionListeners(button);
                    button.addActionListener(getProductRemoveListener(product, new Position(i, j)));
                } else {
                    button.setOpaque(false);
                    button.setBackground(null); // default color
                }
            }
        }
        this.isOrderSelectMode = hasMatchingProduct;
        revalidate();
        repaint();
    }

    public void highlightEmptyLocations(Product product) {
        boolean hasEmptyLocations = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                JButton button = grid[i][j];
                if (button.getText().equals(UNOCCUPIED_SPACE)) {
                    hasEmptyLocations = true;
                    button.setOpaque(true);
                    button.setBackground(Color.lightGray); // highlight color
                    removeActionListeners(button);
                    button.addActionListener(getProductStoreListener(product, new Position(i, j)));
                } else {
                    button.setOpaque(false);
                    button.setBackground(null); // default color
                }
            }
        }
        this.isOrderSelectMode = hasEmptyLocations;
        revalidate();
        repaint();
    }

    public void removeHighlights() {
        for (JButton[] row : grid) {
            for (JButton button : row) {
                button.setOpaque(false);
                button.setBackground(null); // default color
                removeActionListeners(button);
            }
        }
        this.isOrderSelectMode = false;
        revalidate();
        repaint();
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
                    removeActionListeners(button);
                    if (isOrderSelectMode)
                        button.addActionListener(getProductStoreListener(product, position));
                } else {
                    button.setText(UNOCCUPIED_SPACE);
                    removeActionListeners(button);
                    button.addActionListener(getProductRemoveListener(product, position));
                }
                revalidate();
                repaint();
            }
        });
    }

    private ActionListener getProductStoreListener(Product product, Position position) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (delegate != null) {
                    delegate.placeProduct(product, position);
                }
            }
        };
    }

    private ActionListener getProductRemoveListener(Product product, Position position) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (delegate != null) {
                    delegate.confirmRemoveProduct(product, position);
                }
            }
        };
    }

    private void removeActionListeners(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }
}