package api.bridge.insertion;

public interface INode
{
    long getUID();

    INode getPrevious();

    INode getNext();
}
