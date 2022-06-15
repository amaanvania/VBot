package api;

import api.bridge.wrappers.game.Client;
import client.BotEngine;

public class BotContext
{
    public static Client client;
    public static BotEngine engine;

    public BotContext()
    {
        engine = new BotEngine();
    }

    public static void createClient()
    {
        client = new Client(engine.getClient());
    }

}
