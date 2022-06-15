package api.bridge.insertion;

public interface IClient
{
    boolean getLoggedIn();

    int getBackDialogID();

    int getOpenInterfaceID();

    int getBaseX();

    int getBaseY();

    int getDestinationX();

    int getDestinationY();

    int getPlane();

    int getLoopCycle();

    int getCameraX();

    int getCameraY();

    int getCameraZ();

    int getCameraPitch();

    int getCameraYaw();

    int getUniqueIndex();

    int[] getMenuActionID();

    int[] getMenuAction1();

    int[] getMenuAction2();

    int[] getMenuAction3();

    int getUnknownInt();

    long[] getMenuActionHash();

    int getMenuActionRow();

    String[] getMenuActionCommands();

    int[] getBitSettings();

    int[] getCurrentStats();

    int[] getMaxStats();

    int[] getSkillExperiences();

    int[] getVariousSettings();

    byte[][][] getTileSettings();

    int[][][] getTileHeights();

    IPlayer getLocalPlayer();

    IPlayer[] getCachedPlayers();

    INPC[] getCachedNPCs();

    ISceneGraph getScene();

    INodeList getProjectiles();


    IInterfaceComponent[] getInterfaceCache();

    IGroundItems getGroundItems();

    String getLoginBigMessage();

    int getCurrentHP();

    void setMyEmail(String email);

    void setMyPassword(String password);

    int getInt986();

    int getFreezeTimer();

    int getVengTimer();

    boolean getSpecToggled();

    double getSpecPercent();

}
