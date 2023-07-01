package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.BalanceChange;
import model.BalanceChangeList;
import model.Order;
import view.BalancePanel;

public class BalanceController implements PropertyChangeListener {
    private int balance;
    private BalanceChangeList balanceChangeList;
    private BalancePanel balancePanel;

    public BalanceController() {
        this.balance = 0;
        this.balanceChangeList = new BalanceChangeList();
        this.balanceChangeList.addPropertyChangeListener(this);
        this.balancePanel = new BalancePanel(this.balanceChangeList.getBalanceChangeList());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
    }

    public void updateBalance(Order order, boolean successfulOrder) {
        
        if (successfulOrder) {
            this.balance += order.getReward();
        } else {
            this.balance -= order.getReward();
        }

        BalanceChange balanceChange = new BalanceChange(order, successfulOrder);
        this.balanceChangeList.add(balanceChange);

        this.balancePanel.setBalance(this.balance);
    }

    public BalancePanel getBalancePanel() {
        return balancePanel;
    }
}