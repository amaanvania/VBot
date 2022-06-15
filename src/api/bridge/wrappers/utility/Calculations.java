package api.bridge.wrappers.utility;

import api.BotContext;
import api.script.access.Players;

import java.awt.*;

public final class Calculations
{
    public static Rectangle GAMESCREEN = new Rectangle(4, 4, 512, 336);

    public static int[] SINE = new int[2048];


    public static int[] COSINE = new int[2048];

    static
    {
        for (int i = 0; i < SINE.length; i++)
        {
            SINE[i] = (int) (65536.0D * Math.sin((double) i * 0.0030679615D));
            COSINE[i] = (int) (65536.0D * Math.cos((double) i * 0.0030679615D));
        }
    }
    /**
     * Distance formula derive from Pythagorean theorem
     * @param from
     * @param to
     * @return
     */
    public static double distanceBetween(Tile from, Tile to)
    {
        int x = to.getX() - from.getX();
        int y = to.getY() - from.getY();
        return Math.sqrt((x * x) + (y * y));
    }

    /**
     * Distance formula derive from Pythagorean theorem
     * @param from
     * @param to
     * @return
     */
    public static double distanceBetween(int x1, int y1, int x2, int y2)
    {
        int x = x1 - x2;
        int y = y1 - y2;
        return Math.sqrt((x * x) + (y * y));
    }

    public static double distanceTo(Tile tile)
    {
        return distanceBetween(tile, Players.getLocal().getLocation());
    }

    public static int getTileHeight(int paramInt1, int paramInt2)
    {
        int[][][] data = BotContext.client.getTileHeights();
        int i = paramInt1 >> 7;
        int j = paramInt2 >> 7;
        if ((i < 0) || (j < 0) || (i > 103) || (j > 103)) {
            return 0;
        }
        int k = BotContext.client.getPlane();
        if ((k < 3) && ((BotContext.client.getTileSettings()[1][i][j] & 0x2) == 2)) {
            k++;
        }
        int m = paramInt1 & 0x7F;
        int n = paramInt2 & 0x7F;
        int i1 = data[k][i][j] * (128 - m) + data[k][(i + 1)][j] * m >> 7;
        int i2 = data[k][i][(j + 1)] * (128 - m) + data[k][(i + 1)][(j + 1)] * m >> 7;
        return i1 * (128 - n) + i2 * n >> 7;
    }

    public static Point worldToScreen(int x, int y, int height)
    {
        if (x < 128 || y < 128 || x > 13056 || y > 13056)
        {
            return new Point(-1, -1);
        }

        int z = getTileHeight(x, y) - height;
        x -= BotContext.client.getCameraX();
        z -= BotContext.client.getCameraZ();
        y -= BotContext.client.getCameraY();

        int sinePitch = SINE[BotContext.client.getCameraPitch()];
        int cosinePitch = COSINE[BotContext.client.getCameraPitch()];
        int sineYaw = SINE[BotContext.client.getCameraYaw()];
        int cosineYaw = COSINE[BotContext.client.getCameraYaw()];

        int angle = y * sineYaw + x * cosineYaw >> 16;

        y = y * cosineYaw - x * sineYaw >> 16;
        x = angle;
        angle = z * cosinePitch - y * sinePitch >> 16;
        y = z * sinePitch + y * cosinePitch >> 16;


        if (y >= 50)
        {
            int xx = 256 + (x << 9) / y;
            int yy = (angle << 9) / y + 167;
            return new Point(xx, yy);
        }

        return new Point(-1, -1);
    }

    public static Point tileToScreen(double d, double e, int height)
    {
        d -= BotContext.client.getBaseX();
        e -= BotContext.client.getBaseY();
        return worldToScreen((int) ((d + 0.5D) * 128), (int) ((e + 0.5D) * 128), height);
    }

    public static Point tileToScreen(int x, int y)
    {
        return tileToScreen(x, y, 0);
    }

    public static Point tileToScreen(Tile tile)
    {
        return tileToScreen(tile.getX(), tile.getY());
    }

    public static Point groundToScreen(int x, int y, int height)
    {
        if ((x < 128.0D) || (y < 128.0D) || (x > 13056.0D) || (y > 13056.0D))
        {
            return new Point(-1, -1);
        }
        int z = getTileHeight(x, y) - height;
        return worldToScreen(x, z, y);
    }

    public static Point slotToPoint(int slot){
        int BASE_X = 540;
        int BASE_Y = 215;
        int STEP_X = 50;
        int STEP_Y = 38;
        int row = slot / 4;
        int col = slot % 4;
        int x = BASE_X + (STEP_X * col);
        int y = BASE_Y + (STEP_Y * row);

        return new Point(x + Timing.random(0,3), y + Timing.random(0,3));
    }
}
