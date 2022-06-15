package api.script.access;

import api.bridge.wrappers.utility.Tile;

import java.util.ArrayList;
import java.util.List;

public class MapNetwork {


    public static List<Tile> nodes = new ArrayList();

    public static void buildMap(){

        Tile[] path = {
                new Tile(3251, 10217, 0).randomize(1, 1),
                new Tile(3258, 10211, 0).randomize(1, 1),
                new Tile(3258, 10204, 0).randomize(1, 1),
                new Tile(3256, 10194, 0).randomize(1, 1),
                new Tile(3255, 10183, 0).randomize(1, 1),
                new Tile(3256, 10171, 0),
                new Tile(3254, 10163, 0),
                new Tile(3248, 10157, 0),
                new Tile(3227, 10202, 0).randomize(1, 1),
                new Tile(3217, 10195, 0).randomize(1, 1),
                new Tile(3220, 10186, 0).randomize(1, 1),
                new Tile(3229, 10180, 0).randomize(1, 1),
                new Tile(3232, 10172, 0).randomize(1, 1),
                new Tile(3249, 10152, 0).randomize(1, 1),
        };
        for(Tile t : path)
            nodes.add(t);

    }

    public static Tile getNearestNode(){
        if(nodes.isEmpty()) buildMap();
        double best = Double.MAX_VALUE;
        Tile res = null;
        for(Tile t : nodes){
            if(t.distanceTo() < best && t.distanceTo() > 3){
                best = t.distanceTo();
                res = t;
            }
        }

        return res;
    }

    public static Tile getNearestSouthernNode(){
        if(nodes.isEmpty()) buildMap();
        double best = Double.MAX_VALUE;
        Tile res = null;
        for(Tile t : nodes){
            if(t.distanceTo() < best && t.distanceTo() > 5 && t.getY() <= Players.getLocal().getLocation().getY()){
                best = t.distanceTo();
                res = t;
            }
        }

        return res;
    }

}
