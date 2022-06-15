package api.input;

import api.bridge.wrappers.utility.Timing;

import java.awt.*;
import java.awt.event.MouseEvent;

public class VirtualMouse {

    private final Component target;

    public VirtualMouse(Component target) {
        this.target = target;
    }


    public synchronized void moveMouse(int x, int y) {


        target.dispatchEvent(new MouseEvent(target,
                MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, x,
                y, 0, false));


    }

    public synchronized void clickMouse(int x, int y){
        target.dispatchEvent(new MouseEvent(target,
                MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, x,
                y, 0, false, MouseEvent.BUTTON1));
    }

    public void releaseMouse(int x, int y){
        target.dispatchEvent(new MouseEvent(target,
                MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, x,
                y, 0, false, MouseEvent.BUTTON1));
    }

    public void pressMouse(int x, int y){
        target.dispatchEvent(new MouseEvent(target,
                MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, x,
                y, 0, false, MouseEvent.BUTTON1));
    }

    public synchronized void clickMouse1(int x, int y){
        target.dispatchEvent(new MouseEvent(target,
                MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, x,
                y, 0, false, MouseEvent.BUTTON2));
    }

    public void releaseMouse1(int x, int y){
        target.dispatchEvent(new MouseEvent(target,
                MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, x,
                y, 0, false, MouseEvent.BUTTON2));
    }

    public void pressMouse1(int x, int y){
        target.dispatchEvent(new MouseEvent(target,
                MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, x,
                y, 0, false, MouseEvent.BUTTON2));
    }

    public void click(final int x, final int y) {

        moveMouse(x, y);
        Timing.sleep(50, 200);
        pressMouse(x, y);
        Timing.sleep(10, 100);
        releaseMouse(x, y);
        Timing.sleep(10, 100);
        clickMouse(x, y);
    }

    public void click1(final int x, final int y) {

        moveMouse(x, y);
        Timing.sleep(50, 200);
        pressMouse1(x, y);
        Timing.sleep(10, 100);
        releaseMouse1(x, y);
        Timing.sleep(10, 100);
        clickMouse1(x, y);
    }

}
