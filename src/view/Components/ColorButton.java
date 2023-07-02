package view.Components;

import java.awt.Color;

import javax.swing.JButton;

public class ColorButton extends JButton{
    private static final Color SELECT_FG_COLOR = Color.WHITE;
    private static final Color SELECT_BG_COLOR = Color.darkGray;

    public ColorButton(String text) {
        super(text);
        setForeground(null);
        setBackground(null);
        setOpaque(false);
    }

    public void setButtonActive() {
        setForeground(SELECT_FG_COLOR);
        setBackground(SELECT_BG_COLOR);
        setOpaque(true);
    }

    public void setButtonInactive() {
        setForeground(null);
        setBackground(null);
        setOpaque(false);
    }
}
