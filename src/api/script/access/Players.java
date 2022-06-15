package api.script.access;

import api.BotContext;
import api.bridge.insertion.IPlayer;
import api.bridge.wrappers.game.Player;
import api.interfaces.Locatable;
import api.interfaces.generics.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Players
{
    public static Player getLocal()
    {
        return new Player(BotContext.client.getLocalPlayer(), 2047);
    }

    public static Player[] getAll()
    {
        IPlayer[] players = BotContext.client.getCachedPlayers();
        ArrayList<Player> list = new ArrayList<>();
        for (int i = 0; i < players.length - 1; i++)
        {
            IPlayer player = players[i];
            if (player != null)
            {
                list.add(new Player(player, i));
            }
        }
        return list.toArray(new Player[list.size()]);
    }

    public static Player[] get(Filter<Player> filter)
    {
        IPlayer[] players = BotContext.client.getCachedPlayers();
        ArrayList<Player> list = new ArrayList<>();
        for (int i = 0; i < players.length - 1; i++)
        {
            IPlayer player = players[i];
            if (player != null)
            {
                Player playerWrapper = new Player(player, i);
                if (filter.accept(playerWrapper))
                {
                    list.add(playerWrapper);
                }
            }
        }
        return list.toArray(new Player[list.size()]);
    }

    /**
     * Returns all players accepted by filter argument (sorted from local player)
     *
     * @return Player[]
     */
    public static Player[] getSorted(Filter<Player> filter)
    {
        Player[] players = get(filter);
        Arrays.sort(players, Locatable.NEAREST);
        return players;
    }

    /**
     * returns all Players whose name equals names[n] (sorted from local player)
     *
     * @return Player local
     */
    public static Player[] get(String... names)
    {
        List<String> acceptedNames = Arrays.asList(names);
        return getSorted(new Filter<Player>()
        {
            @Override
            public boolean accept(Player player)
            {
                return acceptedNames.contains(player.getName());
            }
        });
    }


    public static Player get(String name){

        Player[] arr = getAll();
        if(arr == null) return null;

        for(Player pp : getAll()){
            if(pp.getName().equals(name)) return pp;
        }

        return null;
    }
}
