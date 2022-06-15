package api.bridge.wrappers.game;

import api.BotContext;
import api.bridge.insertion.*;
import api.bridge.wrappers.utility.Timing;


public class Client
{
    private IClient client;

    public Client(IClient reference)
    {
        this.client = reference;
    }

    /**
     * Returns whether or not local player is logged into game
     *
     * @return a boolean representing being logged in (true if so, false if not)
     */
    public boolean isLoggedIn()
    {
        return client.getLoggedIn();
    }

    public int getBackDialogID()
    {
        return client.getBackDialogID();
    }

    public int getOpenInterfaceID(){
        return client.getOpenInterfaceID();
    }
    public int getBaseX()
    {
        return client.getBaseX();
    }

    public int getBaseY()
    {
        return client.getBaseY();
    }

    public int getPlane()
    {
        return client.getPlane();
    }

    public String getLoginBigMessage(){
        return client.getLoginBigMessage();
    }

    public void setMyEmail(String email){
        client.setMyEmail(email);
    }

    public void setMyPassword(String password){
        client.setMyPassword(password);
    }

    public int getLoopCycle()
    {
        return client.getLoopCycle();
    }

    public int getCameraPitch()
    {
        return (int) BotContext.engine.getClassLoader().getFieldValue("rw.aN", "a", null);
    }

    public int getCameraYaw()
    {
        return (int) BotContext.engine.getClassLoader().getFieldValue("rw.aN", "b", null);
    }

    public double getCameraZoom(){
        return (double) BotContext.engine.getClassLoader().getFieldValue("rw.ej", "k", null);
    }
    public int getCameraX()
    {
        return client.getCameraX();
    }

    public int getCameraY()
    {
        return client.getCameraY();
    }

    public int getCameraZ()
    {
        return client.getCameraZ();
    }

    public int[] getCurrentStats() {
        return client.getCurrentStats();
    }
    public int getCurrentHP() {return getCurrentStats()[3]; }

    public int getUniqueIndex()
    {
        return client.getUniqueIndex();
    }

    public int[] getMenuActionID()
    {
        return client.getMenuActionID();
    }

    public int[] getMenuAction1()
    {
        return client.getMenuAction1();
    }

    public int[] getMenuAction2()
    {
        return client.getMenuAction2();
    }

    public int[] getMenuAction3()
    {
        return client.getMenuAction3();
    }

    public String[] getMenuActionCommands() { return client.getMenuActionCommands(); }

    public boolean getSpecToggled(){
        boolean b = (boolean) BotContext.engine.getClassLoader().getFieldValue("rw.dg", "h", null);
        return b;
    }

    public double getSpecPercent(){
        double d = (double) BotContext.engine.getClassLoader().getFieldValue("rw.dg", "g", null);
        return d;
    }

    public void setMenuAction1(int value, int index)
    {
        client.getMenuAction1()[index] = value;
    }

    public void setMenuAction2(int value, int index)
    {

        client.getMenuAction2()[index] = value;
    }

    public void setMenuAction3(int value, int index)
    {

        client.getMenuAction3()[index] = value;
    }

    public void setMenuActionId(int value, int index)
    {
        client.getMenuActionID()[index] = value;
    }

    public void setMenuActionHash(long value, int index){
        client.getMenuActionHash()[index] = value;
    }

    public void setMenuActionString(String value, int index)
    {
        client.getMenuActionCommands()[index] = value;
    }

    public int getMenuActionRow(){
        return client.getMenuActionRow();
    }

    public int getUnknownInt() { return client.getUnknownInt(); }

    public Object doAction(int action)
    {
        Class<?>[] params = {int.class};

        Object o = BotContext.engine.getClassLoader().invokeMethod("rw.B", "t", params, client, action);
        return o;
    }



    public Object doWalkTo(int i1,int i2,int i3,int i4,int i5,int i6,int i7,int i8,int i9,boolean b, int i10)
    {
        Class<?>[] params = {int.class,int.class,int.class,int.class,int.class,int.class,int.class,int.class,int.class,boolean.class,int.class};
        BotContext.engine.getMouse().moveMouse(584 + Timing.random(0,80), 60 + Timing.random(0,80));
        return BotContext.engine.getClassLoader().invokeMethod("rw.B", "a", params, client, i1,i2,i3,i4,i5,i6,i7,i8,i9,b,i10);
    }

    public long[] getMenuActionHash(){
        return client.getMenuActionHash();
    }

    public int[] getBitSettings()
    {
        return client.getBitSettings();
    }


    public int[] getMaxStats()
    {
        return client.getMaxStats();
    }

    public int[] getSkillExperiences()
    {
        return client.getSkillExperiences();
    }

    public int[] getVariousSettings() {return client.getVariousSettings(); }

    public byte[][][] getTileSettings()
    {
        return client.getTileSettings();
    }

    public int[][][] getTileHeights()
    {
        return client.getTileHeights();
    }

    public IPlayer getLocalPlayer()
    {
        return client.getLocalPlayer();
    }

    public IPlayer[] getCachedPlayers()
    {
        return client.getCachedPlayers();
    }

    public INPC[] getCachedNPCs()
    {
        return client.getCachedNPCs();
    }

    public IGroundItems getGroundItems()
    {
        return client.getGroundItems();
    }

    public ISceneGraph getScene()
    {
        return client.getScene();
    }


    public IInterfaceComponent[] getInterfaceCache()
    {
        return client.getInterfaceCache();
    }

    /**
     * Returns the client interface accessor
     *
     * @return
     */
    public IClient getReference()
    {
        return client;
    }
}
