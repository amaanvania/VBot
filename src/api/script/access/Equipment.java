package api.script.access;

import api.BotContext;

public class Equipment
{


    /**
     * Check if the Player is Wearing an Item based on the ID.
     *
     * @param id ID of the item to check for.
     * @return True if the Player is wearing the Item.
     */
    public static boolean isWearing(int id)
    {
        for (Slot slot : Slot.values())
        {
            if (slot.id() == id)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Get's the item ID in slot.
     *
     * @param slot Slot to get the item ID from.
     * @return Item ID from given slot.
     */
    public static int getItemId(Slot slot) {
        return slot.id();
    }

    /**
     * Check's if there is an item in the given slot.
     *
     * @param slot Slot to check for Item.
     * @return True if there is an Item in the given slot.
     */
    public static boolean hasItem(Slot slot) {
        return getItemId(slot) != 0;
    }

    /**
     * Check's if the given slot is empty.
     *
     * @param slot Slot to check for Item.
     * @return True if the given slot is empty.
     */
    public static boolean isEmpty(Slot slot) {
        return getItemId(slot) == 0;
    }

    public enum Slot {
        HEAD(0),
        CAPE(1),
        AMULET(2),
        WEAPON(3),
        BODY(4),
        SHIELD(5),
        LEGS(7),
        GLOVES(9),
        BOOTS(10),
        RING(12),
        AMMO(13);

        private final int INTERFACE_ID = 1688;
        private int slot;

        Slot(int slot) {
            this.slot = slot;
        }

        /**
         * Get's the ID of the item in slot.
         *
         * @return ID of the item in slot.
         */
        public int id()
        {
            return BotContext.client.getInterfaceCache()[INTERFACE_ID].getItemIDs()[slot];
        }
    }
}
