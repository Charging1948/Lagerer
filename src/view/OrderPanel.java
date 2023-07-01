package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import interfaces.OrderPanelDelegate;
import model.Order;
import model.helpers.HTMLRenderer;

public class OrderPanel extends JPanel implements ListSelectionListener {
    private JList<Order> orderJList;
    private JButton getNewOrderButton;
    private Order selectedOrder;
    private OrderPanelDelegate delegate;

    public OrderPanel(DefaultListModel<Order> orderListModel) {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        orderJList = new JList<Order>(orderListModel);
        orderJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderJList.addListSelectionListener(this);
        orderJList.setCellRenderer(new HTMLRenderer());
        JScrollPane listScrollPane = new JScrollPane(orderJList);

        getNewOrderButton = new JButton("Get New Order");
        getNewOrderButton.addActionListener(e -> {
            System.out.println("Get new order button pressed");
            if (delegate != null) {
                delegate.addGeneratedOrder();
            }
        });

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 800));
        JLabel headLabel = new JLabel("<html><body><h1>Orders</h1></body></html>");
        this.add(headLabel, BorderLayout.NORTH);
        this.add(listScrollPane, BorderLayout.CENTER);
        this.add(getNewOrderButton, BorderLayout.SOUTH);
    }

    public void setDelegate(OrderPanelDelegate delegate) {
        this.delegate = delegate;
    }

    public void addOrder(Order order) {
        orderListModel.addElement(order);
        revalidate();
        repaint();
    }

    public void fromOrderList(Order[] orders) {
        orderListModel.clear();
        for (Order order : orders) {
            orderListModel.addElement(order);
        }
        revalidate();
        repaint();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selectedOrder = orderJList.getSelectedValue();
            getNewOrderButton.setEnabled(selectedOrder != null);
            firePropertyChange("order_selected", null, selectedOrder);
        }
    }
}
