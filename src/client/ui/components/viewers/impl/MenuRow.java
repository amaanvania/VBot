package client.ui.components.viewers.impl;

import api.BotContext;
import client.ui.components.viewers.Viewer;

import java.awt.*;

public class MenuRow extends Viewer {
    @Override
    public void render(Graphics2D g) {
        int action = BotContext.client.getMenuActionRow() + 1;
        g.drawString(BotContext.client.getMenuActionID()[action]+ "",20,100);
        g.drawString(BotContext.client.getMenuAction1()[action] + "",20,120);
        g.drawString(BotContext.client.getMenuAction2()[action] + "",20,140);
        g.drawString(BotContext.client.getMenuAction3()[action] + "",20,160);
        g.drawString(BotContext.client.getMenuActionHash()[action] + "",20,180);
        g.drawString(action + "",20,200);
        if(action < 500)
            g.drawString(BotContext.client.getMenuActionCommands()[action] + "",20,220);


    }
}
