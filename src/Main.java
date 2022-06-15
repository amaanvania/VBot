import api.BotContext;
import client.BotEngine;


import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String... args)
    {
        new BotContext();

        SwingUtilities.invokeLater(() ->
        {
            try
            {

                BotContext.engine.setupWindow();

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });
        BotContext.engine.parseArguments(args);
        BotContext.engine.downloadClient();

        BotContext.engine.injectClient();
        BotContext.engine.init();

        BotContext.createClient();
    }
}
