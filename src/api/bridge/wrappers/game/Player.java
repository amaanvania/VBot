package api.bridge.wrappers.game;

import api.bridge.insertion.INPCDefinition;
import api.bridge.insertion.IPlayer;
import api.bridge.insertion.IUserAgent;

public class Player extends Character
{
    private IPlayer player;

    public Player(IPlayer reference, int index)
    {
        super(reference, index);
        this.player = reference;
    }

    public String getName()
    {
        return player.getName();
    }

    public int getCombatLevel()
    {
        return player.getCombatLevel();
    }

    public int getTotalLevel()
    {
        return player.getTotalLevel();
    }

    public int getHeadIcon()
    {
        return player.getHeadIcon();
    }

    public int getTeam()
    {
        return player.getTeam();
    }


    public boolean isClanMember(){
        return player.getUserAgent().getIsClanMember();
    }

    public INPCDefinition getNpcDefinition(){
        return player.getNpcDefinition();
    }

    public int getHealthLevel() { return player.getHealthLevel(); }

    public int[] getEquipment()
    {
        int[] equipment = player.getEquipment();
        int[] res = equipment.clone();
        for(int i = 0; i < res.length; i++){
            if(res[i] >= 512) res[i] -= 512;
        }
        return res;
    }

    public IUserAgent getUserAgent() { return player.getUserAgent(); }

    public IPlayer getReference()
    {
        return player;
    }
}
