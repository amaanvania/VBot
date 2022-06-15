package client.ui.components.viewers.impl;

import api.BotContext;
import api.bridge.wrappers.game.GameObject;
import api.bridge.wrappers.utility.Calculations;
import api.bridge.wrappers.utility.Tile;
import api.script.access.Players;
import api.script.access.Scene;
import client.ui.components.viewers.Viewer;

import java.awt.*;
import java.util.Set;

public class GameObjects extends Viewer
{
        @Override
        public void render(Graphics2D g) {
        final Tile reference = Players.getLocal().getLocation();
        final FontMetrics metrics = g.getFontMetrics();

        for (int x = reference.getRegionX() - 15; x < reference.getRegionX() + 15; x++) {
            for (int y = reference.getRegionY() - 15; y < reference.getRegionY() + 15; y++) {

                final Set<GameObject> objects = Scene.getAllObjectsAt(x,y);
                if (objects.size() > 0) {

                    final Tile location = new Tile(x + BotContext.client.getBaseX(), y + BotContext.client.getBaseY());
                    final Point point = Calculations.tileToScreen(location);

                    if (Calculations.GAMESCREEN.contains(point)) {
                        g.setColor(Color.GREEN);
                        g.fillOval(point.x, point.y, 5, 5);

                        int offset = point.y - 5;
                        for (GameObject item : objects) {
                            g.setColor(Color.WHITE);
                            String details = "Id: " + item.getID();
                            g.drawString(details, point.x - metrics.stringWidth(details) / 2, offset);
                        }
                    }
                }
            }
        }
    }
}

