package client.ui.components.viewers.impl;

import api.BotContext;
import client.ui.components.viewers.Viewer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;

public class Cursor extends Viewer
{
    private long angle = 0;

    @Override
    public void render(Graphics2D g)
    {
        AffineTransform old = g.getTransform();

        Point position = BotContext.engine.getAppletController().getMouseListener().getMousePosition();

        g.setColor(Color.WHITE);
        g.drawLine(position.x - 3, position.y - 3, position.x + 2, position.y + 2);
        g.drawLine(position.x - 3, position.y + 2, position.x + 2, position.y - 3);

        g.rotate(Math.toRadians(angle += 2), position.x, position.y);

        g.setColor(new Color(0, 0, 0, 225));
        g.draw(new Arc2D.Double(position.x - 10, position.y - 10, 20, 20, 0, 90, Arc2D.OPEN));
        g.draw(new Arc2D.Double(position.x - 10, position.y - 10, 20, 20, 180, 90, Arc2D.OPEN));

        g.setColor(new Color(0, 255, 0, 225));
        g.draw(new Arc2D.Double(position.x - 10, position.y - 10, 20, 20, 90, 90, Arc2D.OPEN));
        g.draw(new Arc2D.Double(position.x - 10, position.y - 10, 20, 20, 270, 90, Arc2D.OPEN));

        g.setTransform(old);
    }
}


