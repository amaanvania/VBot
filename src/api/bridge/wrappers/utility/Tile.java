package api.bridge.wrappers.utility;

import api.BotContext;
import api.script.access.Players;

public class Tile
{
    private int x, y;
    private int plane;

    public Tile(int x, int y, int plane)
    {
        this.x = x;
        this.y = y;
        this.plane = plane;
    }

    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.plane = BotContext.client.getPlane();
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getRegionX()
    {
        return x - BotContext.client.getBaseX();
    }

    public int getRegionY()
    {
        return y - BotContext.client.getBaseY();
    }

    public int getPlane()
    {
        return plane;
    }

    public Tile randomize(int xOffset, int yOffset) {
        xOffset = Timing.random(-xOffset, xOffset);
        yOffset = Timing.random(-yOffset, yOffset);
        return new Tile(x + xOffset, y + yOffset, plane);
    }

    public static void walkTo(Tile to) {
        final Tile from = Players.getLocal().getLocation();
        BotContext.client.doWalkTo(0,0,0,0,from.getRegionY(),0,0,to.getRegionY(),from.getRegionX(),true,to.getRegionX());
    }

    public final int distanceTo()
    {
        return (int) Calculations.distanceTo(this);
    }

    @Override
    public Tile clone()
    {
        return new Tile(x, y, plane);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) return false;
        Tile o = (Tile) other;

        return (o.x == this.x) && (o.y == this.y) && (o.plane == this.plane);
    }

    public static boolean isWithin(Tile obj, Tile lower, Tile upper){
        return  obj.getX() >= lower.getX() &&
                obj.getX() <= upper.getX() &&
                obj.getY() >= lower.getY() &&
                obj.getY() <= upper.getY() && obj.getPlane() == lower.getPlane();
    }

    public static boolean isWithin(Tile lower, Tile upper){
        Tile obj = Players.getLocal().getLocation();
        return  obj.getX() >= lower.getX() &&
                obj.getX() <= upper.getX() &&
                obj.getY() >= lower.getY() &&
                obj.getY() <= upper.getY() && obj.getPlane() == lower.getPlane();
    }

    @Override
    public String toString()
    {
        return "(" + getX() + ", " + getY() + ", " + getPlane() + ")";
    }
}
