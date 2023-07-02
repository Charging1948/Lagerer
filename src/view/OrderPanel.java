package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import interfaces.OrderPanelDelegate;
import model.Order;
import model.helpers.HTMLRenderer;
import view.Components.ColorButton;

public class OrderPanel extends JPanel implements ListSelectionListener {
    private JList<Order> orderJList;
    private ColorButton getNewOrderButton;
    private ColorButton trashOrderButton;
    private Order selectedOrder;
    private OrderPanelDelegate delegate;

    public OrderPanel(DefaultListModel<Order> orderListModel) {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        orderJList = new JList<Order>(orderListModel);
        orderJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderJList.addListSelectionListener(this);
        orderJList.setCellRenderer(new HTMLRenderer());
        JScrollPane listScrollPane = new JScrollPane(orderJList);

        getNewOrderButton = new ColorButton("Get New Order");
        getNewOrderButton.addActionListener(e -> {
            System.out.println("Get new order button pressed");
            if (delegate != null) {
                delegate.addGeneratedOrder();
            }
        });
        getNewOrderButton.setButtonActive();

        trashOrderButton = new ColorButton("Trash Order");
        trashOrderButton.addActionListener(e -> {
            System.out.println("Trash order button pressed");
            if (delegate != null) {
                delegate.trashSelectedOrder(selectedOrder);
            }
        });

        trashOrderButton.setEnabled(false);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 700));
        JLabel headLabel = new JLabel("<html><body><h1>Orders</h1></body></html>");
        // set Label to be on top, getneworder and trashorder to be on bottom and list in center taking up all space left
        this.add(headLabel, BorderLayout.PAGE_START);
        this.add(listScrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(getNewOrderButton);
        buttonPanel.add(trashOrderButton);
        this.add(buttonPanel, BorderLayout.PAGE_END);
    }

    public void setDelegate(OrderPanelDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selectedOrder = orderJList.getSelectedValue();

            if (selectedOrder == null) {
                trashOrderButton.setEnabled(false);
                trashOrderButton.setButtonInactive();
            } else {
                trashOrderButton.setEnabled(true);
                trashOrderButton.setButtonActive();
            }

            firePropertyChange("order_selected", null, selectedOrder);
        }
    }
}
