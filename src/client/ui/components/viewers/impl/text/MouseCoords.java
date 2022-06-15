package client.ui.components.viewers.impl.text;

import api.BotContext;
import client.ui.components.viewers.TextViewer;

public class MouseCoords extends TextViewer
{

    @Override
    public String refresh()
    {
        return "Mouse: " + BotContext.engine.getAppletController().getMouseListener().getMousePosition().toString();
    }
}

