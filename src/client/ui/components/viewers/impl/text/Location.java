package client.ui.components.viewers.impl.text;

import api.script.access.Players;
import client.ui.components.viewers.TextViewer;


public class Location extends TextViewer
{
    @Override
    public String refresh()
    {
        return "Location: " + Players.getLocal().getLocation();
    }
}
