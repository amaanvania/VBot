package client.ui.components.viewers.impl.text;

import api.BotContext;
import client.ui.components.viewers.TextViewer;


public class MapBase extends TextViewer
{

    @Override
    public String refresh()
    {
        return "Map base: (" + BotContext.client.getBaseX() + ", " + BotContext.client.getBaseY() + ", " + BotContext.client.getPlane() + ")";
    }
}
