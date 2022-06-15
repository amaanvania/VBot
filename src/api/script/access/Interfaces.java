package api.script.access;

import api.BotContext;
import api.bridge.insertion.IInterfaceComponent;
import api.bridge.wrappers.game.InterfaceComponent;

import java.util.Arrays;
import java.util.Objects;

public class Interfaces
{
    public static InterfaceComponent[] getCache()
    {
        return Arrays.stream(BotContext.client.getInterfaceCache())
                .filter(Objects::nonNull)
                .toArray(InterfaceComponent[]::new);

    }

    public static InterfaceComponent[] getAll(){
        IInterfaceComponent[] iic = BotContext.client.getInterfaceCache();
        InterfaceComponent[] arr = new InterfaceComponent[iic.length];
        for(int i = 0 ; i < iic.length; i++){
            if(iic != null){
                arr[i] = new InterfaceComponent(iic[i], 1);
            }
        }
        return arr;
    }
    public static InterfaceComponent getInterface(int index)
    {
        return new InterfaceComponent(BotContext.client.getInterfaceCache()[index], index);
    }

    public static InterfaceComponent getInterface(int container, int index)
    {
        return getInterface(container).getChild(index);
    }
}
