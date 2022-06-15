package loader.modifiers.replacements;

import client.ui.AppletController;

import java.applet.Applet;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class BotFocusListener implements FocusListener
{
    private Applet targetApplet;
    private FocusListener[] gameFocusListener;

    public BotFocusListener(AppletController target)
    {
        this.targetApplet = target.getApplet();
        this.gameFocusListener = targetApplet.getFocusListeners();
        for (FocusListener listener : gameFocusListener)
        {
            targetApplet.removeFocusListener(listener);
        }
        targetApplet.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e)
    {
        for (FocusListener listener : gameFocusListener)
        {
            listener.focusGained(e);
        }
    }

    @Override
    public void focusLost(FocusEvent e)
    {
    }
}