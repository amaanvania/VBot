package client.ui.components;

import client.ui.components.viewers.Viewer;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;

public class ViewerObject extends JCheckBoxMenuItem
{
    private Viewer paintObject;

    public ViewerObject(String text, Viewer object)
    {
        super(text);
        this.paintObject = object;

        this.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                paintObject.toggle();
            }
        });
    }

    @Override
    protected void processMouseEvent(MouseEvent evt)
    {
        if (evt.getID() == MouseEvent.MOUSE_RELEASED && contains(evt.getPoint()))
        {
            doClick();
            setArmed(true);
        }
        else
        {
            super.processMouseEvent(evt);
        }
    }

    public Viewer getPaintObject()
    {
        return paintObject;
    }
}
