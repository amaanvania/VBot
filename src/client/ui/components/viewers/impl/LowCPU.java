package client.ui.components.viewers.impl;


import client.ui.components.viewers.Viewer;

import java.awt.*;

import static api.bridge.wrappers.utility.Timing.sleep;

public class LowCPU extends Viewer {

    @Override
    public void render(Graphics2D g) {
        //sleep here
        sleep(200);
    }
}
