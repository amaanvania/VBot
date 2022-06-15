package api.bridge.wrappers.game;

import api.bridge.insertion.IItem;
import api.bridge.wrappers.utility.Tile;
import api.interfaces.Interactable;
import api.script.access.Packets;

public class GroundItem implements Interactable
{
    public Tile t;
    public IItem i;
    public GroundItem(IItem i, Tile t)
    {
        this.i = i;
        this.t = t;
    }

    @Override
    public boolean interact(String action)
    {
        return true;
    }

    @Override
    public boolean interact(int actionID)
    {
        Packets.sendAction(234, i.getID(), t.getRegionX(),t.getRegionY());
        return true;
    }
}
