package api.bridge.wrappers.game;

import api.bridge.insertion.IRenderable;

public class Renderable extends NodeSub
{
    private IRenderable renderable;

    public Renderable(IRenderable reference)
    {
        super(reference);
        this.renderable = reference;
    }

    public int getModelHeight()
    {
        return renderable.getModelHeight();
    }

    public IRenderable getReference()
    {
        return renderable;
    }
}
