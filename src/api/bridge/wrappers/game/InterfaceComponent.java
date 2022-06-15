package api.bridge.wrappers.game;

import api.bridge.insertion.IInterfaceComponent;

import java.util.ArrayList;

public class InterfaceComponent
{
    private IInterfaceComponent component;
    private int container;

    public InterfaceComponent(IInterfaceComponent component, int container)
    {
        this.component = component;
        this.container = container;
    }

    public String getText()
    {
        return component.getText();
    }

    public boolean getInterfaceShown(){
        return component.getInterfaceShown();
    }

    public String getTooltipText()
    {
        return component.getTooltipText();
    }

    public int getIndex()
    {
        return container;
    }

    public int[] getInventoryIds()
    {
        return component.getItemIDs();
    }

    public int[] getInventoryStackSizes()
    {
        return component.getInvStackSizes();
    }

    public int getPaddingX() { return component.getPaddingX(); }

    public int getPaddingY() { return component.getPaddingY(); }


    public boolean hasChildren()
    {
        return component.getChildren().length > 0;
    }

    public InterfaceComponent[] getChildren()
    {
        ArrayList<InterfaceComponent> list = new ArrayList();
        IInterfaceComponent[] children = component.getChildren();

        for (int i = 0; i < children.length; i++)
        {
            if (children[i] != null)
            {
                list.add(new InterfaceComponent(children[i], i));
            }
        }
        return list.toArray(new InterfaceComponent[list.size()]);
    }

    public InterfaceComponent getChild(int index)
    {
        InterfaceComponent[] children = getChildren();

        if (index <= children.length && children[index] != null)
        {
            return children[index];
        }
        return null;
    }
}
