package controller;

import interfaces.BalancePanelDelegate;
import model.BalanceChange;
import model.BalanceChangeList;
import model.Order;
import model.Product;
import model.Order.OrderType;
import services.ServiceInjector;
import view.BalanceDialog;
import view.BalancePanel;

public class BalanceController implements BalancePanelDelegate {
    private int balance;
    private BalanceChangeList balanceChangeList;
    private BalancePanel balancePanel;
    private BalanceDialog balanceDialog;

    public BalanceController() {
        this.balance = 0;
        this.balanceChangeList = new BalanceChangeList();
        this.balancePanel = new BalancePanel();
        this.balancePanel.setDelegate(this);

        ServiceInjector serviceInjector = new ServiceInjector();

        this.balanceDialog = new BalanceDialog(serviceInjector.getMainFrame(),
                this.balanceChangeList.getBalanceChangeList());
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

    /*
     * This method is called when an order is trashed. It subtracts 300 from the balance.
     */
    public void trashPenalty(Order order) {
        if (order != null)
            this.balance -= order.getReward();
        this.balanceChangeList.add(new BalanceChange(order, false));
        this.balancePanel.setBalance(this.balance);
    }

    public void trashPenalty(Product product) {
        this.balance -= 300;
        this.balanceChangeList.add(new BalanceChange(new Order(product, 300, OrderType.OUTBOUND), false));
        this.balancePanel.setBalance(this.balance);
    }

    public BalancePanel getBalancePanel() {
        return balancePanel;
    }

    @Override
    public void showBalanceDialog() {
        this.balanceDialog.setSumOfCosts(this.balanceChangeList.getSumOfCosts());
        this.balanceDialog.setSumOfIncome(this.balanceChangeList.getSumOfIncome());
        this.balanceDialog.setVisible(true);
    }
}