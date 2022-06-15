package client.ui.components.viewers.impl;

import api.bridge.wrappers.game.Item;
import client.ui.components.viewers.Viewer;

import java.awt.*;

public class Inventory extends Viewer
{
    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.GREEN);
        for (Item item : api.script.access.Inventory.getAll())
        {
            if (item != null && item.getID() != -1)
            {
                Point loc = new Point(item.getSlot() % 4 * 42 + 570, item.getSlot() / 4 * 36 + 232);
                g.drawString(String.valueOf(item.getID()), loc.x, loc.y);
            }
        }
    }
}
