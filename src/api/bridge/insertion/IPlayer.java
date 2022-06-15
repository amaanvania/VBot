package api.bridge.insertion;

public interface IPlayer extends ICharacter
{
    String getName();

    int getCombatLevel();

    int getTotalLevel();

    int getHeadIcon();

    int getTeam();

    int getCurrentHealth();

    int[] getEquipment();

    INPCDefinition getNpcDefinition();

    IUserAgent getUserAgent();

}
