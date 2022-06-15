package api.bridge.insertion;

public interface INodeSub extends INode
{
    INodeSub getPrevious();

    INodeSub getNext();
}
