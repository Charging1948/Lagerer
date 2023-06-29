package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class StatusPanel extends JPanel {
    private String status;
    private JLabel statusLabel = new JLabel();

    public StatusPanel(String status) {
        setStatus(status);
        add(statusLabel);
    }

    public void setStatus(String status) {
        this.status = status;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                statusLabel.setText(status);
                revalidate();
                repaint();
            }
        });
    }

    public String getStatus() {
        return status;
    }
}
