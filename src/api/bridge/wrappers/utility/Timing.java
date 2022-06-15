package api.bridge.wrappers.utility;

import api.interfaces.generics.Condition;

import java.util.Random;

public class Timing
{
    public static final Random random = new Random();

    public static int random(int min, int max)
    {
        return random.nextInt((max - min) + 1) + min;
    }

    public static void sleep(int min, int max)
    {
        try
        {
            Thread.sleep(random(min, max));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void sleep(int duration)
    {
        try
        {
            Thread.sleep(duration);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static boolean sleep(Condition c, int waitPerLoop, int timeout) {
        Timer t = new Timer(timeout);
        while (t.isRunning()) {
            if (c.isValid()) {
                return true;
            }
            sleep(waitPerLoop);
        }
        return false;
    }



}
