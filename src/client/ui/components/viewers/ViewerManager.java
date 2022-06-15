package client.ui.components.viewers;

import api.BotContext;
import api.interfaces.listeners.PaintListener;
import api.script.Script;
import client.ui.components.ViewerObject;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewerManager implements PaintListener
{

    public ViewerManager()
    {
    }

    @Override
    public void repaint(Graphics2D g)
    {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Script running = BotContext.engine.getRunningScript();
        if (running != null && running instanceof PaintListener)
        {
            ((PaintListener) running).repaint(g);
        }

        int y = 30;
        for (ViewerObject viewer : BotContext.engine.getFrameViewers())
        {
            if (viewer.getPaintObject().isEnabled())
            {
                if (viewer.getPaintObject() instanceof TextViewer)
                {
                    g.setColor(Color.GREEN);
                    g.drawString(((TextViewer) viewer.getPaintObject()).refresh(), 15, y);
                    y += 25;
                }
                else
                {
                    try{
                        viewer.getPaintObject().render(g);
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
