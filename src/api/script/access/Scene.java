package api.script.access;

import api.BotContext;
import api.bridge.insertion.IGameObject;
import api.bridge.insertion.ISceneTile;
import api.bridge.wrappers.game.GameObject;
import api.interfaces.generics.Filter;

import java.util.LinkedHashSet;
import java.util.Set;

public class Scene
{
    public enum OBJECT_TYPE
    {
        INTERACTABLE,
        WALL,
        WALL_DECORATION,
        FLOOR_DECORATION
    }

    public static Set<GameObject> getAllObjectsAt(int x, int y)
    {
        Set<GameObject> objects = new LinkedHashSet<GameObject>();

        if (x > 0 && y > 0)
        {
            ISceneTile tile = BotContext.client.getScene().getSceneTiles()[BotContext.client.getPlane()][x][y];

            if (tile != null)
            {
                for (IGameObject object: tile.getInteractiveObjects())
                {
                    if (object != null)
                    {
                        GameObject objectWrapper = new GameObject(object);
                            objects.add(objectWrapper);
                    }
                }
            }
        }

        return objects;
    }


    public static Set<GameObject> getAllObjectsAt(int x, int y, Filter<GameObject> filter)
    {
        Set<GameObject> objects = new LinkedHashSet<GameObject>();
        ISceneTile tile = BotContext.client.getScene().getSceneTiles()[BotContext.client.getPlane()][x][y];

        if (tile != null)
        {
            for (IGameObject object: tile.getInteractiveObjects())
            {
                if (object != null)
                {
                    GameObject objectWrapper = new GameObject(object);
                    if (filter.accept(objectWrapper))
                    {
                        objects.add(objectWrapper);
                    }
                }
            }
        }

        return objects;
    }

    public static GameObject[] getAllObjects()
    {
        Set<GameObject> objects = new LinkedHashSet<GameObject>();
        for (int x = 0; x < 104; x++)
        {
            for (int y = 0; y < 104; y++)
            {
                objects.addAll(getAllObjectsAt(x, y));

            }
        }
        return objects.toArray(new GameObject[objects.size()]);
    }

    public static GameObject[] getAllObjects(Filter<GameObject> filter)
    {
        Set<GameObject> objects = new LinkedHashSet<GameObject>();
        for (int x = 0; x < 104; x++)
        {
            for (int y = 0; y < 104; y++)
            {
                for (GameObject object : getAllObjectsAt(x, y))
                {
                    if (object != null && filter.accept(object))
                    {
                        objects.add(object);
                    }
                }
            }
        }
        return objects.toArray(new GameObject[objects.size()]);
    }
}
