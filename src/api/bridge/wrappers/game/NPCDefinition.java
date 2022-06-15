package api.bridge.wrappers.game;

import api.bridge.insertion.INPCDefinition;

public class NPCDefinition
{
    private INPCDefinition definition;

    public NPCDefinition(INPCDefinition reference)
    {
        this.definition = reference;
    }

    public int getID()
    {
        return definition.getID();
    }

    public String getName()
    {
        return definition.getName();
    }

    public int getStandingAnimation(){
        return definition.getStandingAnimation();
    }

    public int getWalkingAnimation(){
        return definition.getWalkingAnimation();
    }

    public String[] getActions()
    {
        return definition.getActions();
    }

    public INPCDefinition getReference()
    {
        return definition;
    }
}
