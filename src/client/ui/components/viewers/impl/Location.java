package client.ui.components.viewers.impl;

import api.script.access.Players;
import client.ui.components.viewers.Viewer;

import java.awt.*;

public class Location extends Viewer {

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.drawString(Players.getLocal().getLocation().getX() + "," + Players.getLocal().getLocation().getY() + "," + Players.getLocal().getLocation().getPlane(), 50,50);
        g.drawString(Players.getLocal().getAnimation() + "", 50, 70);
    }
}
