package client.ui.components.viewers;

import java.awt.*;

public abstract class Viewer
{
    private boolean enabled;

    public Viewer()
    {
        enabled = false;
    }

    public abstract void render(Graphics2D g);

    public boolean isEnabled()
    {
        return enabled;
    }

    public void toggle()
    {
        enabled = !enabled;
    }
}

