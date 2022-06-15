package api.bridge.insertion;

public interface IInterfaceComponent
{
    String getText();

    String getTooltipText();

    int[] getItemIDs();

    int[] getInvStackSizes();

    int[] getItemQuantities();

    IInterfaceComponent[] getChildren();

    boolean getInterfaceShown();

    int getPaddingX();

    int getPaddingY();

}
