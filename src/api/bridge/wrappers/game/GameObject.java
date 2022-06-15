package api.bridge.wrappers.game;

import api.BotContext;
import api.bridge.insertion.IFloorDecoration;
import api.bridge.insertion.IGameObject;
import api.bridge.insertion.IWall;
import api.bridge.insertion.IWallDecoration;
import api.bridge.wrappers.utility.Tile;
import api.interfaces.Interactable;
import api.script.access.Packets;
import api.script.access.Scene;

public class GameObject implements Interactable
{
    private Scene.OBJECT_TYPE type;
    private int id, x, y;
    private long uid;

    public GameObject(long uid)
    {
        this.uid = uid;
        this.id = (int) (uid >>> 32) & Integer.MAX_VALUE;
        x = (int) uid & 0x7F;
        y = (int) uid >> 7 & 0x7F;
    }

    public GameObject(IGameObject object)
    {
        this(object.getUID());
        type = Scene.OBJECT_TYPE.INTERACTABLE;
    }

    public GameObject(IFloorDecoration object)
    {
        this(object.getUID());
        type = Scene.OBJECT_TYPE.FLOOR_DECORATION;
    }

    public GameObject(IWall object)
    {
        this(object.getUID());
        type = Scene.OBJECT_TYPE.WALL;
    }

    public GameObject(IWallDecoration object)
    {
        this(object.getUID());
        type = Scene.OBJECT_TYPE.WALL_DECORATION;
    }

    public int getLocalX()
    {
        return x;
    }

    public int getLocalY()
    {
        return y;
    }

    public int getID()
    {
        return id;
    }

    public Tile getLocation()
    {
        return new Tile(getLocalX() + BotContext.client.getBaseX(), getLocalY() + BotContext.client.getBaseY(), BotContext.client.getPlane());
    }

    public Scene.OBJECT_TYPE getType()
    {
        return type;
    }

    @Override
    public boolean interact(String s)
    {
        return true;
    }

    @Override
    public boolean interact(int menuIndex)
    {
        int actionId = 502;
        switch (menuIndex)
        {
            case 0:
                actionId = 1107;
                break;
            case 1:
                actionId = 1226;
                break;
            case 2:
                actionId = 113;
                break;
            case 3:
                actionId = 900;
                break;
            case 4:
                actionId = 502;
        }
        Packets.sendAction(actionId, this.id, x, y, id, BotContext.client.getMenuActionRow() - 1);
        return true;
    }
}