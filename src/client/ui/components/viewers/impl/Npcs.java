package client.ui.components.viewers.impl;

import api.bridge.wrappers.game.NPC;
import api.script.access.NPCs;
import client.ui.components.viewers.Viewer;

import java.awt.*;

public class Npcs extends Viewer
{
    @Override
    public void render(Graphics2D g)
    {
        FontMetrics metrics = g.getFontMetrics();
        int height = metrics.getHeight();

        for (NPC npc: NPCs.getAll())
        {
            if (npc != null)
            {
                Point position = npc.getScreenPosition();
                if (position.x > -1)
                {
                    g.setColor(Color.BLACK);
                    g.fillOval(position.x, position.y, 3, 3);

                    g.setColor(Color.YELLOW);
                    String id = String.valueOf(npc.getDefinition().getName() + "," + npc.getDefinition().getID() + "," + npc.getIndex());

                    int x = position.y - height / (2 * 15);
                    int y = position.x - metrics.stringWidth(id) / 2;

                    g.drawString(id, x, y);
                }
            }
        }
    }
}
