package api.script.access;

import api.bridge.wrappers.game.InterfaceComponent;
import api.bridge.wrappers.game.Item;
import api.interfaces.generics.Filter;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory
{
    public static Item[] getAll()
    {
        InterfaceComponent inventory = Interfaces.getInterface(3214);

        int[] ids = inventory.getInventoryIds();
        int[] stackSizes = inventory.getInventoryStackSizes();

        int length = ids.length;
        ArrayList<Item> items = new ArrayList<>(length);

        for (int slot = 0; slot < length; slot++)
        {
            if (ids[slot] > 0)
            {
                items.add(new Item(ids[slot], stackSizes[slot], slot));
            }
        }
        return items.toArray(new Item[items.size()]);
    }

    /**
     * returns all widget items accepted by filter argument
     *
     * @return WidgetItem[]
     */
    public static Item[] get(Filter<Item> filter)
    {
        return Arrays.stream(getAll()).filter(widgetItem -> widgetItem != null && filter.accept(widgetItem)).toArray(size -> new Item[size]);
    }

    /**
     * returns all widget items whose id equals ids[n]
     *
     * @return WidgetItem[]
     */
    public static Item[] get(int... ids)
    {
        ArrayList<Item> res = new ArrayList();
        for(Item i : getAll()){
            for(int id : ids){
                if(i.getID() == id){
                    res.add(i);
                }
            }
        }
        Item[] result = new Item[res.size()];
        for(int i = 0 ; i  <result.length; i++){
            result[i] = res.get(i);
        }
        return result;
    }

    public static Item getAny(int... ids) {
        for (Item item : getAll()) {
            for (int id : ids) {
                if (id == item.getID()) {
                    return item;
                }
            }
        }
        return null;
    }


    /**
     * returns inventory count (all items inclusive)
     *
     * @return inventory count
     */
    public static int getCount()
    {
        return getAll().length;
    }

    /**
     * returns inventory count of item ids
     *
     * @return inventory count
     */
    public static int getCount(int... ids)
    {
        return get(ids).length;
    }

    /**
     * @return existence of item ids in inventory
     *
     * note: inventory must contain at least one of each item id
     */
    public static boolean contains(int... id)
    {
        return getCount(id) > 0;
    }

    /**
     * @return true/false inventory full
     */
    public static Boolean isFull()
    {
        return getCount() == 28;
    }

    /**
     * @return true/false inventory empty
     */
    public static Boolean isEmpty()
    {
        return getCount() == 0;
    }
}