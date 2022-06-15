package client.ui.components.viewers;

import java.awt.*;

public abstract class TextViewer extends Viewer
{
    @Override
    public void render(Graphics2D g)
    {
    }

    public abstract String refresh();
}
