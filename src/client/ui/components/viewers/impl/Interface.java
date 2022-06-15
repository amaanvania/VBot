package client.ui.components.viewers.impl;

import api.BotContext;
import client.ui.components.viewers.Viewer;

import java.awt.*;

public class Interface extends Viewer {
    int[] initial;
    public void findChanges() {
        int[] after = (int[]) BotContext.client.getVariousSettings().clone();
        for (int i = 0; i < after.length; i++) {
            if(this.initial == null) break;
            if (this.initial[i] != after[i])
                System.out.println("Index: " + i + "Value Changed from: " + this.initial[i] + "to " + after[i]);
        }
        this.initial = (int[])after.clone();
    }
    @Override
    public void render(Graphics2D g) {
        g.drawString("Dialog id: " +  BotContext.client.getOpenInterfaceID(),20,100);
        g.drawString("Back Dialog id: " +  BotContext.client.getBackDialogID(),20,120);
        findChanges();
    }
}
