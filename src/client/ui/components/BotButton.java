package client.ui.components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BotButton extends JButton
{
    public BotButton(Icon icon, String toolTipText)
    {
        super(icon);

        this.setOpaque(false);
        this.setFocusable(false);

        this.setToolTipText(toolTipText);
    }

    public void addNewActionListener(ActionListener listener)
    {
        this.addActionListener(listener);
    }
}
