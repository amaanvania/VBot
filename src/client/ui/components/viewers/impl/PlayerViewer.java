package client.ui.components.viewers.impl;

import api.bridge.wrappers.game.Player;
import api.script.access.Players;
import client.ui.components.viewers.Viewer;

import java.awt.*;

public class PlayerViewer extends Viewer
{
    @Override
    public void render(Graphics2D g)
    {
        FontMetrics metrics = g.getFontMetrics();

        for (Player player: Players.getAll())
        {
            if (player != null)
            {
                Point position = player.getScreenPosition();
                if (position.x > -1)
                {
                    g.setColor(Color.RED);
                    g.fillOval(position.x, position.y, 3, 3);

                    g.setColor(Color.YELLOW);
                    String id = String.valueOf(player.getAnimation());

                    g.drawString(id, position.x - metrics.stringWidth(id) / 2, position.y - 10);
                }
            }
        }
    }
}
