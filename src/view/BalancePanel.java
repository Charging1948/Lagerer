package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import interfaces.BalancePanelDelegate;
import view.Components.ColorButton;

public class BalancePanel extends JPanel {
    private int balance = 0;
    private JLabel balanceLabel;
    private ColorButton balanceButton;
    private BalancePanelDelegate delegate;

    public void setDelegate(BalancePanelDelegate delegate) {
        this.delegate = delegate;
    }
    

    public BalancePanel() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 100));
        balanceButton = new ColorButton("Show Balance");
        balanceButton.addActionListener(e -> {
            showBalanceDialog();
        });
        balanceButton.setButtonActive();

        balanceLabel = new JLabel();
        setBalanceInLabel(balance);
        add(balanceButton, BorderLayout.SOUTH);
        add(balanceLabel, BorderLayout.CENTER);
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

    public void showBalanceDialog() {
        if (delegate != null) {
            delegate.showBalanceDialog();
        }
    }
}