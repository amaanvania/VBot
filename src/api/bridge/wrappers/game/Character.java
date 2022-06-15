package api.bridge.wrappers.game;

import api.BotContext;
import api.bridge.insertion.ICharacter;
import api.bridge.wrappers.utility.Calculations;
import api.bridge.wrappers.utility.Tile;
import api.interfaces.Interactable;
import api.interfaces.Locatable;
import api.script.access.Packets;
import api.script.access.Players;

import java.awt.*;


public class Character extends Renderable implements Interactable, Locatable
{
    private ICharacter character;
    private int index;

    public Character(ICharacter reference, int index)
    {
        super(reference);
        this.character = reference;
        this.index = index;
    }

    public int getIndex()
    {
        if(index >= 32768) return index - 32768;
        if(index > 30720 && index < 32768) return index - 30720;
        return index;
    }

    public int getLoopCycle()
    {
        return character.getLoopCycle();
    }

    public boolean isUnderAttack()
    {
        return character.getLoopCycle() > BotContext.client.getLoopCycle();
    }

    public int getCurrentHealth()
    {
        return character.getHealthLevel();
    }

    public int getCurrentPray() { return character.getCurrentPray(); }

    public boolean isDead()
    {
        return character.getLoopCycle() > BotContext.client.getLoopCycle() && character.getHealthLevel() == 0;
    }

    public int getInteractingIndex()
    {
        return character.getInteractingIndex();
    }

    public int getAnimation()
    {
        return character.getAnimation();
    }

    public int getLocalX()
    {
        return character.getSceneX() >> 7;
    }

    public int getLocalY()
    {
        return character.getSceneY() >> 7;
    }

    public int[] getQueueX()
    {
        return character.getQueueX();
    }

    public int[] getQueueY()
    {
        return character.getQueueY();
    }

    public Character getInteractingCharacter()
    {
        int raw = character.getInteractingIndex();
        int myIndex = BotContext.client.getUnknownInt();

        if(raw < 30720 && raw == myIndex) return Players.getLocal();

        if(raw > 30720 && raw < 32768){
            if(raw - 30720 == myIndex) return Players.getLocal();
        }

        if(raw >= 32768){
            if(raw - 32768 == myIndex) return Players.getLocal();
        }

        if (raw != -1 && raw >= 32768)
         {
            raw -= 32768;


            for (Player p: Players.getAll())
            {
                if (p != null && (p.getIndex() == raw || p.getInteractingIndex() == raw))
                {
                    return p;
                }

            }
        }
        return null;
    }

    public Point getScreenPosition()
    {
        return Calculations.tileToScreen(getLocation());
    }

    @Override
    public boolean interact(int menuIndex)
    {
        if (character != null)
        {
            int actionId = 502;
            switch (menuIndex)
            {
                case 0:
                    actionId = 1107;
                    break;
                case 1:
                    actionId = 2729;
                    break;
                case 2:
                    actionId = 2577;
                    break;
                case 3:
                    actionId = 516;
                    break;
                case 4:
                    actionId = 27;
            }
            Packets.sendAction(actionId, index, 0, 0);
        }
        return false;
    }

    @Override
    public boolean interact(String actionID)
    {

        Packets.sendAction(actionID.length(), index, 0, 0);
        return false;
    }

    @Override
    public Tile getLocation()
    {
        return new Tile(getLocalX() + BotContext.client.getBaseX(), getLocalY() + BotContext.client.getBaseY(), BotContext.client.getPlane());
    }

    @Override
    public double distanceTo()
    {
        return Calculations.distanceTo(getLocation());
    }

    @Override
    public int distanceBetween(Tile t)
    {
        return (int) Calculations.distanceBetween(getLocation(), t);
    }
}
