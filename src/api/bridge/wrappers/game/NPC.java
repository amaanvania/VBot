package api.bridge.wrappers.game;

import api.bridge.insertion.INPC;

public class NPC extends Character
{
    private INPC npc;
    private NPCDefinition definition;

    public NPC(INPC reference, int index)
    {
        super(reference, index);
        this.npc = reference;
        this.definition = new NPCDefinition(reference.getDefinition());
    }

    public NPCDefinition getDefinition()
    {
        return definition;
    }

    public INPC getReference()
    {
        return npc;
    }
}
