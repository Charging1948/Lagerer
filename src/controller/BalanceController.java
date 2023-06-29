package controller;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import model.BalanceChange;
import model.Order;
import model.Product;
import view.BalancePanel;

public class BalanceController implements IController {
    private int balance;
    private List<BalanceChange> balanceChangeList;
    private BalancePanel balancePanel;

    public BalanceController() {
        this.balance = 0;
        this.balanceChangeList = new ArrayList<BalanceChange>();
        this.balancePanel = new BalancePanel();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
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
        this.balancePanel.setBalanceChangeList(this.balanceChangeList);
    }

    public BalancePanel getBalancePanel() {
        return balancePanel;
    }


}