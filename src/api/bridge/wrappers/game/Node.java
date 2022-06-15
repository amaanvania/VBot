package api.bridge.wrappers.game;

import api.bridge.insertion.INode;

public class Node
{
    private INode node;

    public Node(INode reference)
    {
        this.node = reference;
    }

    public long getUID()
    {
        return node.getUID();
    }

    INode getPrevious()
    {
        return node.getPrevious();
    }

    INode getNext()
    {
        return node.getNext();
    }

    public INode getReference()
    {
        return node;
    }
}
