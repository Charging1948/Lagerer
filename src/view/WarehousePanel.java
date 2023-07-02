package view;

import model.Position;
import model.Product;
import view.Components.ColorButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import exceptions.InvalidProductPlacementException;
import interfaces.WarehousePanelDelegate;

import java.awt.*;
import java.awt.event.*;

public class WarehousePanel extends JPanel {

    private static final String UNOCCUPIED_SPACE = "<html><body><h1>Empty</h1></body></html>";
    private ColorButton[][] grid;
    private JPanel gridPanel;
    private ColorButton trashProductButton;
    private WarehousePanelDelegate delegate;
    private boolean isOrderSelectMode = false;

    public WarehousePanel(int height, int width) {
        this.setBorder(new EmptyBorder(50, 0, 50, 0));
        this.setLayout(new BorderLayout());
        this.gridPanel = new JPanel();
        // trashProductButton on bottom and gridPanel centered to fill the rest of the
        // space

        this.trashProductButton = new ColorButton("Trash Product");
        this.trashProductButton.addActionListener(e -> {
            System.out.println("Trash product button pressed");
            highlightAllProductLocations();
        });
        this.trashProductButton.setButtonActive();

        gridPanel.setLayout(new GridLayout(height, width));
        this.grid = new ColorButton[height][width];

        for (int i = 0; i < height; i++) { // start from the last row
            for (int j = 0; j < width; j++) {

                ColorButton button = new ColorButton(UNOCCUPIED_SPACE);
                this.grid[i][j] = button;
                gridPanel.add(button);
            }
        }
        this.add(gridPanel, BorderLayout.CENTER);
        this.add(trashProductButton, BorderLayout.PAGE_END);
    }

    /*
     * This method is called when the user selects an outgoing order from the
     * OrderPanel list.
     * It highlights the locations of the matching products in the warehouse.
     * It also removes the highlight from any other locations.
     */
    public void highlightMatchingProductLocations(Product product) {
        boolean hasMatchingProduct = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ColorButton button = grid[i][j];
                removeActionListeners(button);
                if (button.getText().equals(product.toHTMLString())) {
                    hasMatchingProduct = true;
                    button.setButtonActive();
                    button.addActionListener(getProductShipOutListener(product, new Position(i, j)));
                } else {
                    button.setButtonInactive();
                }
            }
        }
        this.isOrderSelectMode = hasMatchingProduct;
        revalidate();
        repaint();
    }

    public void highlightAllProductLocations() {
        boolean hasAnyProduct = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ColorButton button = grid[i][j];
                removeActionListeners(button);
                if (!button.getText().equals(UNOCCUPIED_SPACE)) {
                    hasAnyProduct = true;
                    button.setButtonActive();
                    button.addActionListener(getProductRemoveListener(new Position(i, j)));
                } else {
                    button.setButtonInactive();
                }
            }
        }
        this.isOrderSelectMode = hasAnyProduct;
        revalidate();
        repaint();
    }

    public void highlightEmptyLocations(Product product) {
        boolean hasEmptyLocations = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ColorButton button = grid[i][j];
                removeActionListeners(button);
                try {
                    product.isValidPlacement(i, j);
                } catch (InvalidProductPlacementException e) {
                    button.setButtonInactive();
                    continue;
                }
                if (button.getText().equals(UNOCCUPIED_SPACE)) {

                    hasEmptyLocations = true;
                    button.setButtonActive();
                    button.addActionListener(getProductStoreListener(product, new Position(i, j)));
                } else {
                    button.setButtonInactive();
                }
            }
        }
        this.isOrderSelectMode = hasEmptyLocations;
        revalidate();
        repaint();
    }

    public void removeHighlights() {
        for (ColorButton[] row : grid) {
            for (ColorButton button : row) {
                button.setButtonInactive();
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

    private ActionListener getProductRemoveListener(Position position) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (delegate != null) {
                    delegate.confirmRemoveProduct(position);
                }
            }
        };
    }

    private ActionListener getProductShipOutListener(Product product, Position position) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (delegate != null) {
                    delegate.shipOutProduct(product, position);
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