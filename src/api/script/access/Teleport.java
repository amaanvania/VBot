package api.script.access;

import api.BotContext;
import api.bridge.wrappers.game.NPC;
import api.bridge.wrappers.utility.Timing;

public class Teleport {

    public static void teleportCave(){
        if(NPCs.getAll() != null) {
            for (NPC n : NPCs.getAll()) {
                if (n.getDefinition().getID() == 7307) {
                    Timing.sleep(100);
                    Packets.sendAction(20, n.getIndex(), 0, 0);
                    Timing.sleep(()->  BotContext.client.getOpenInterfaceID() == 20080, 200, 10000);
                    Packets.sendAction(315, 319, 215, 20084);
                    Timing.sleep(500);
                    Packets.sendAction(315, 320, 164, 20100);
                    return;
                }
            }
        }

    }

    public static void teleportSlayer(){
        if(NPCs.getAll() != null) {
            for (NPC n : NPCs.getAll()) {
                if (n.getDefinition().getID() == 7307) {
                    Timing.sleep(100);
                    Packets.sendAction(20, n.getIndex(), 0, 0);
                    Timing.sleep(2000,5000);
                    Packets.sendAction(315, 320, 164, 20097);
                    Timing.sleep(1000);
                    return;
                }
            }
        }

    }
}
