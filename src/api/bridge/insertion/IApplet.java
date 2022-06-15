package api.bridge.insertion;

import api.BotContext;
import api.bridge.wrappers.utility.Timing;

import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;

public class IApplet extends Applet
{
    private final BufferedImage gameBuffer, botBuffer;
    private final int width, height;
    private int refreshRate;

    public IApplet()
    {
        this.width = 765;
        this.height = 503;
        this.gameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.botBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        refreshRate = 0;
    }

    @Override
    public Graphics getGraphics()
    {
        Graphics2D g = (Graphics2D) botBuffer.getGraphics();
        g.drawImage(gameBuffer, 0, 0, null);

        if (BotContext.engine.getAppletController() != null)
        {
            BotContext.engine.getAppletController().getViewerManager().repaint(g);
        }

        g.dispose();

        super.repaint();
        super.getGraphics().drawImage(botBuffer, 0, 0, null);

        Timing.sleep(refreshRate);
        return gameBuffer.getGraphics();
    }

    public void setRefreshRate(int rate)
    {
        this.refreshRate = rate;
    }
}
