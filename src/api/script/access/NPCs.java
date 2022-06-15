package api.script.access;

import api.BotContext;
import api.bridge.insertion.INPC;
import api.bridge.wrappers.game.NPC;
import api.interfaces.Locatable;
import api.interfaces.generics.Filter;

import java.util.ArrayList;
import java.util.Arrays;

public class NPCs
{
    public static NPC[] getAll()
    {
        INPC[] npcs = BotContext.client.getCachedNPCs();
        ArrayList<NPC> list = new ArrayList<>();
        for (int i = 0; i < npcs.length; i++)
        {
            INPC npc = npcs[i];
            if (npc != null)
            {
                list.add(new NPC(npc, i));
            }
        }
        return list.toArray(new NPC[list.size()]);
    }

    public static NPC[] get(Filter<NPC> filter)
    {
        INPC[] npcs = BotContext.client.getCachedNPCs();
        ArrayList<NPC> list = new ArrayList<>();
        for (int i = 0; i < npcs.length - 1; i++)
        {
            INPC npc = npcs[i];
            if (npc != null)
            {
                NPC npcWrapper = new NPC(npc, i);
                if (filter.accept(npcWrapper))
                {
                    list.add(npcWrapper);
                }
            }
        }
        return list.toArray(new NPC[list.size()]);
    }

    /**
     * Returns all NPCs accepted by filter argument (sorted from local player)
     *
     * @return Player[]
     */
    public static NPC[] getSorted(Filter<NPC> filter)
    {
        NPC[] npcs = get(filter);
        if (npcs.length > 1)
        {
            Arrays.sort(npcs, Locatable.NEAREST);
            return npcs;
        }
        return null;
    }

    public static NPC getNearest(Filter<NPC> filter)
    {
        NPC[] npcs = getSorted(filter);
        if (npcs != null && npcs.length > 1 && npcs[0] != null)
        {
            return npcs[0];
        }
        return null;
    }
}
