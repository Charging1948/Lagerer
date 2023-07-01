package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import model.BalanceChange;
import model.helpers.HTMLRenderer;

public class BalancePanel extends JPanel {
    private int balance = 0;
    private JList<BalanceChange> balanceChangeJList;
    private JLabel balanceLabel;
    

    public BalancePanel(DefaultListModel<BalanceChange> balanceChangeListModel) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 200));
        balanceLabel = new JLabel();
        setBalanceInLabel(balance);
        add(balanceLabel, BorderLayout.SOUTH);

        balanceChangeJList = new JList<BalanceChange>(balanceChangeListModel);
        balanceChangeJList.setCellRenderer(new HTMLRenderer());
        add(balanceChangeJList, BorderLayout.CENTER);

    }

    public void setBalance(int balance) {
        this.balance = balance;
        setBalanceInLabel(balance);
        revalidate();
        repaint();
    }

    public void setBalanceInLabel(int balance) {
        this.balanceLabel.setText("<html><body><h2>Balance: <strong>" + balance + " €</strong></h2></body></html>");
        revalidate();
        repaint();
    }
}