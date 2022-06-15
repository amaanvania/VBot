package api.script.access;

import api.BotContext;

public class Packets
{
    public static int lastAction1;
    public static int lastAction2;
    public static int lastAction3;
    public static int lastActionID;
    public static int index;


    public static void sendAction(int action, int cmd1, int cmd2, int cmd3)
    {
        sendAction(action, cmd1, cmd2, cmd3, 1);
    }

    public static void sendAction(int action, int cmd1, int cmd2, int cmd3, int index)
    {
        sendAction(action, cmd1, cmd2, cmd3, 0, index);
    }

    public static void sendAction(int action, int cmd1, int cmd2, int cmd3, int cmd4, int index)
    {
        BotContext.client.setMenuAction1(cmd1, index);
        BotContext.client.setMenuAction2(cmd2, index);
        BotContext.client.setMenuAction3(cmd3, index);
        BotContext.client.setMenuActionId(action, index);
        BotContext.client.doAction(index);
    }

    public static void sendAction(int action, int cmd1, int cmd2, int cmd3, int index, String str){
        if(index >= 500){
            System.out.println("Invalid Packet");
            return;
        }

        BotContext.client.setMenuAction1(cmd1, index);
        BotContext.client.setMenuAction2(cmd2, index);
        BotContext.client.setMenuAction3(cmd3, index);
        BotContext.client.setMenuActionId(action, index);
        BotContext.client.setMenuActionString(str, index);
        BotContext.client.doAction(index);
    }

}
