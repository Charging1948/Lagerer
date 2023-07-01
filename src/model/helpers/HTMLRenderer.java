package model.helpers;

import java.awt.Component;

import javax.swing.*;

import model.*;

public class HTMLRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        if (value instanceof Order) {
            Order order = (Order) value;
            value = order.toHTMLString();
        } else if (value instanceof Product) {
            Product product = (Product) value;
            value = product.toHTMLString();
        } else if (value instanceof BalanceChange) {
            BalanceChange balanceChange = (BalanceChange) value;
            value = balanceChange.toHTMLString();
        } else if (value instanceof String) {
            value = "<html><body><h1>" + value + "</h1></body></html>";
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}
