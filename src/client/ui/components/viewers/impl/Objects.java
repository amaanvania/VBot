package client.ui.components.viewers.impl;

import api.BotContext;
import api.bridge.wrappers.game.GroundItem;
import api.bridge.wrappers.utility.Calculations;
import api.bridge.wrappers.utility.Tile;
import api.script.access.GroundItems;
import api.script.access.Players;
import client.ui.components.viewers.Viewer;

import java.awt.*;
import java.util.Set;


public class Objects extends Viewer
{

    @Override
    public void render(Graphics2D g) {
        final Tile reference = Players.getLocal().getLocation();
        final FontMetrics metrics = g.getFontMetrics();

        for (int x = reference.getRegionX() - 15; x < reference.getRegionX() + 15; x++) {
            for (int y = reference.getRegionY() - 15; y < reference.getRegionY() + 15; y++) {

                final Set<GroundItem> items = GroundItems.getAllAt(x, y);
                if (items.size() > 0) {

                    final Tile location = new Tile(x + BotContext.client.getBaseX(), y + BotContext.client.getBaseY());
                    final Point point = Calculations.tileToScreen(location);

                    if (Calculations.GAMESCREEN.contains(point)) {
                        g.setColor(Color.GREEN);
                        g.fillOval(point.x, point.y, 5, 5);

                        int offset = point.y - 5;
                        for (GroundItem item : items) {
                            g.setColor(Color.WHITE);
                            String details = "Id: " + item.i.getID();
                            g.drawString(details, point.x - metrics.stringWidth(details) / 2, offset);
                            offset -= 15;
                        }
                    }
                }
            }
        }
    }
}
