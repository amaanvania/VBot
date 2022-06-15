package api.bridge.wrappers.utility;

import api.script.access.Players;

public class Area {


    //north western most point
    private Tile topLeft;

    //south eastern most point
    private Tile bottomRight;


    public Area(Tile tl, Tile br){
        this.topLeft = tl;
        this.bottomRight = br;
    }

    public boolean inArea(){
        Tile localTile = Players.getLocal().getLocation();
        int x = localTile.getX();
        int y = localTile.getY();

        boolean validX =
                //west point
                x >= topLeft.getX() &&
                x <= bottomRight.getX();

        boolean validY =
                //north
                y <= topLeft.getY() &&
                y >= bottomRight.getY();

        return validX && validY;
    }

}
