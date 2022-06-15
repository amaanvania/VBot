package client.ui.components.viewers.impl.text;

import api.script.access.Packets;
import client.ui.components.viewers.TextViewer;


public class Packet extends TextViewer
{

    @Override
    public String refresh()
    {
        return "Packet: " + String.format("[index: %d, action: %d, cmd1: %d, cmd2: %d, cmd3: %d]", Packets.index, Packets.lastActionID, Packets.lastAction1, Packets.lastAction2, Packets.lastAction3);
    }
}
