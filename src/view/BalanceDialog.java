package view;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.BalanceChange;
import model.helpers.HTMLRenderer;

public class BalanceDialog extends JDialog {
    private JList<BalanceChange> balanceChangeJList;
    private JLabel sumOfCostsLabel;
    private JLabel sumOfIncomeLabel;
    private int sumOfCosts = 0;
    private int sumOfIncome = 0;

    public BalanceDialog(JFrame parentFrame, DefaultListModel<BalanceChange> balanceChangeListModel) {
        super(parentFrame, "Bilanz", true);
        setSize(500, 500);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);
        balanceChangeJList = new JList<BalanceChange>(balanceChangeListModel);
        balanceChangeJList.setCellRenderer(new HTMLRenderer());
        JScrollPane listScrollPane = new JScrollPane(balanceChangeJList);
        add(listScrollPane);

        JPanel sumPanel = new JPanel();
        sumOfCostsLabel = new JLabel();
        sumOfCostsLabel.setText("<html><body><h2>Costs: <strong>" + sumOfCosts + " €</strong></h2></body></html>");
        sumOfIncomeLabel = new JLabel();
        sumOfIncomeLabel.setText("<html><body><h2>Income: <strong>" + sumOfIncome + " €</strong></h2></body></html>");

        //Display them on the same line at the bottom of the dialog
        sumPanel.add(sumOfCostsLabel);
        sumPanel.add(sumOfIncomeLabel);
        this.add(sumPanel, "South");

    }

    public void setSumOfCosts(int sumOfCosts) {
        this.sumOfCosts = sumOfCosts;
        sumOfCostsLabel.setText("<html><body><h2>Costs: <strong>" + sumOfCosts + " €</strong></h2></body></html>");
        revalidate();
        repaint();
    }

    public void setSumOfIncome(int sumOfIncome) {
        this.sumOfIncome = sumOfIncome;
        sumOfIncomeLabel.setText("<html><body><h2>Income: <strong>" + sumOfIncome + " €</strong></h2></body></html>");
        revalidate();
        repaint();
    }
}
