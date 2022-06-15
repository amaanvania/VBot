package api.bridge.wrappers.game;

import api.bridge.wrappers.utility.Calculations;
import api.interfaces.Interactable;
import api.script.access.Packets;
import client.BotEngine;

import java.awt.*;

public class Item implements Interactable
{
    private int id, slot, stackSize;

    public Item(int id, int stackSize, int slot)
    {
        this.id = id;
        this.stackSize = stackSize;
        this.slot = slot;
    }

    public int getID()
    {
        return id;
    }

    public int getSlot()
    {
        return slot;
    }

    public int getStackSize()
    {
        return stackSize;
    }

    @Override
    public boolean interact(String action)
    {
      //  Packets.sendAction(actionID, id - 1, slot, 3214);
        return true;
    }

    @Override
    public boolean interact(int actionID)
    {
        Packets.sendAction(actionID, id - 1, slot, 3214, 4);
        return true;
    }


    public boolean interact(int actionID, int idx)
    {
        Point p = Calculations.slotToPoint(slot);
        BotEngine.getMouse().moveMouse((int) p.getX(), (int) p.getY());
        Packets.sendAction(actionID, id - 1, slot, 3214, idx);
        return true;
    }
}
