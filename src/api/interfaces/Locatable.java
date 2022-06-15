package api.interfaces;

import api.bridge.wrappers.utility.Tile;

import java.util.Comparator;

public interface Locatable
{
    public Tile getLocation();

    public double distanceTo();

    public int distanceBetween(Tile t);

    /**
     * Comparator for Locatable instance
     *
     * compares shortest distance
     */
    public Comparator<Locatable> NEAREST = new Comparator<Locatable>()
    {
        @Override
        public int compare(Locatable from, Locatable to)
        {
            return (int) from.distanceTo() - (int) to.distanceTo();
        }
    };
}
