package view;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.BalanceChange;

public class BalancePanel extends JPanel {
    private int balance;
    private List<BalanceChange> balanceChangeList;
    

    public BalancePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // add components to represent the balance
        // update these components as the balance changes
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setBalanceChangeList(List<BalanceChange> balanceChangeList) {
        this.balanceChangeList = balanceChangeList;
    }


}