package api.script.access;

import api.BotContext;

public class Health {

    public static boolean extremelyLowHealth(){
        return BotContext.client.getCurrentHP() > 0 && BotContext.client.getCurrentHP() <= 70;
    }

    public static boolean veryLowHealth(){
        return BotContext.client.getCurrentHP() <= 80 && BotContext.client.getCurrentHP()> 70;
    }

    public static boolean lowHealth(){
        return BotContext.client.getCurrentHP() < 99 && BotContext.client.getCurrentHP() > 80;
    }

}
