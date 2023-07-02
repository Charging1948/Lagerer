package model;

import javax.swing.*;

public class BalanceChangeList extends BaseModel {
    private DefaultListModel<BalanceChange> balanceChangeListModel;

    public BalanceChangeList() {
        super();
        this.balanceChangeListModel = new DefaultListModel<>();
    }

    public void add(BalanceChange balanceChange) {
        this.balanceChangeListModel.addElement(balanceChange);
        firePropertyChange("balancechangelist_add", null, balanceChange);
    }

    public DefaultListModel<BalanceChange> getBalanceChangeList() {
        return this.balanceChangeListModel;
    }

    public int getSumOfCosts() {
        int sum = 0;
        for (int i = 0; i < this.balanceChangeListModel.size(); i++) {
            if (!this.balanceChangeListModel.get(i).isFromSuccessfulOrder())
                sum += this.balanceChangeListModel.get(i).getOrder().getReward();
        }
        return sum;
    }

    public int getSumOfIncome() {
        int sum = 0;
        for (int i = 0; i < this.balanceChangeListModel.size(); i++) {
            if (this.balanceChangeListModel.get(i).isFromSuccessfulOrder())
                sum += this.balanceChangeListModel.get(i).getOrder().getReward();
        }
        return sum;
    }
}
