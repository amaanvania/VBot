package api.script.access;

import api.BotContext;
import api.bridge.wrappers.utility.Timing;

public class Prayer {

    public static boolean isProtectItem(){
        return BotContext.client.getVariousSettings()[91] == 1;
    }



    public static void protectItem(int first, int second){
        if(!isProtectItem()){
            Packets.sendAction(169 ,first, second, 25020);
            Timing.sleep(300,400);
        }

    }
    public static void protectMelee(int first, int second){
        if(!isProtectMelee()){
            Packets.sendAction(169 ,first, second, 25036);
            Timing.sleep(300,400);
        }

    }
    public static boolean isProtectMelee(){
        return BotContext.client.getVariousSettings()[97] == 1;
    }

    public static void unProtectMelee(int first, int second){
        if(isProtectMelee())
            Packets.sendAction(169 ,first, second, 25036);
    }


    public static boolean isProtectRange(){
        return BotContext.client.getVariousSettings()[96] == 1;
    }

    public static void protectRange(int first, int second){

        if(!isProtectRange()){
            Packets.sendAction(169 ,first, second, 25034);
            Timing.sleep(200,250);
        }

    }

    public static void unProtectRange(int first, int second){
        if(isProtectRange()){
            Packets.sendAction(169 ,first, second, 25034);
            Timing.sleep(100,200);
        }
    }

    public static boolean isProtectMage(){
        return BotContext.client.getVariousSettings()[95] == 1;
    }

    public static void protectMage(int first, int second){
        if(!isProtectRange()){
            Packets.sendAction(169 ,first, second, 25032);
            Timing.sleep(300,400);
        }

    }

    public static void unProtectMage(int first, int second){
        if(isProtectRange()){
            Packets.sendAction(169 ,first, second, 25032);
            Timing.sleep(100,200);
        }
    }

    public static  void activePiety(int first, int second){
        if(!isPiety()){
            Packets.sendAction(169 ,first, second, 25050);
            Timing.sleep(300,400);
        }

    }
    public static void deActivateRigour(int first, int second){
        if(isPiety()){
            Packets.sendAction(169 ,first, second, 25050);
            Timing.sleep(100,200);
        }

    }
    public static  boolean isPiety(){
        return (BotContext.client.getVariousSettings()[620] == 1);
    }




}
