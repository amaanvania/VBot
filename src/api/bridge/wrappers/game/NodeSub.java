package api.bridge.wrappers.game;

import api.bridge.insertion.INodeSub;

public class NodeSub extends Node
{
    private INodeSub cacheableNode;

    public NodeSub(INodeSub reference)
    {
        super(reference);
        this.cacheableNode = reference;
    }

    INodeSub getPrevious()
    {
        return cacheableNode.getPrevious();
    }

    INodeSub getNext()
    {
        return cacheableNode.getNext();
    }

    public INodeSub getReference()
    {
        return cacheableNode;
    }
}
