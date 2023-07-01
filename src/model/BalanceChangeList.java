package model;

import javax.swing.*;

public class BalanceChangeList extends BaseModel{
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
}
