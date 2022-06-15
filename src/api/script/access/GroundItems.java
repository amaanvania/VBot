package api.script.access;

import api.BotContext;
import api.bridge.insertion.IItem;
import api.bridge.insertion.INode;
import api.bridge.insertion.INodeList;
import api.bridge.wrappers.game.GroundItem;
import api.bridge.wrappers.utility.Tile;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GroundItems {

    public static Set<GroundItem> getAllAt(int x, int y) {
        final Set<GroundItem> groundItems = new LinkedHashSet<>();
        if ((x > 0 && x < 104) && ((y > 0) && y < 104)) {

            final int plane = BotContext.client.getPlane();

            final INodeList list = BotContext.client.getGroundItems().getGroundItems()[plane][x][y];

            if (list != null) {
                final INode head = list.getHead();
                INode current = head.getNext();
                while (current != null && current != head) {
                    final IItem item = ((IItem) current);
                    if (item != null) {
                        final GroundItem groundItem = new GroundItem(item, new Tile(x + BotContext.client.getBaseX(), y + BotContext.client.getBaseY(), plane));
                        groundItems.add(groundItem);
                    }
                    current = current.getNext();
                }
            }

        }
        return groundItems;
    }





    public static List<GroundItem> getAll() {
        final List<GroundItem> groundItems = new ArrayList<>();
        for (int x = 0; x < 104; x++) {
            for (int y = 0; y < 104; y++) {
                groundItems.addAll(getAllAt(x, y));
            }
        }
        return groundItems;
    }
}
