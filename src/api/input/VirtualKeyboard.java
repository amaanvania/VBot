package api.input;


import api.bridge.wrappers.utility.Timing;

import java.awt.*;
import java.awt.event.KeyEvent;

public class VirtualKeyboard {

    private final Component target;

    public VirtualKeyboard(Component target) {
        this.target = target;
    }

    public synchronized void typeText(String text, int delayBetweenStrokes, boolean enter) {
        for (char character : text.toCharArray()) {
            typeKey(character, false);
            Timing.sleep((int) ((Math.random() * 0.1 + 1) * delayBetweenStrokes));
        }
        if (enter) pressEnter();
    }

    public synchronized void typeKey(char character, boolean enter) {
        int code = character;
        if ((character >= 'a') && (character <= 'z')) {
            code -= 32;
        }
        long time = System.currentTimeMillis();
        target.dispatchEvent(new KeyEvent(target, KeyEvent.KEY_PRESSED, time, 0, code, character, KeyEvent.KEY_LOCATION_STANDARD));
        target.dispatchEvent(new KeyEvent(target, KeyEvent.KEY_TYPED, time, 0, 0, character, KeyEvent.KEY_LOCATION_UNKNOWN));
        Timing.sleep((int) ((Math.random() * 0.1 + 25)));
        target.dispatchEvent(new KeyEvent(target, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, code, character, KeyEvent.KEY_LOCATION_STANDARD));
        if (enter) pressEnter();
    }

    public synchronized void typeKey(int code) {

        long time = System.currentTimeMillis();
        target.dispatchEvent(new KeyEvent(target, KeyEvent.KEY_PRESSED, time, 0, code, ' ', KeyEvent.KEY_LOCATION_STANDARD));
        target.dispatchEvent(new KeyEvent(target, KeyEvent.KEY_TYPED, time, 0, 0, ' ', KeyEvent.KEY_LOCATION_UNKNOWN));
        Timing.sleep((int) ((Math.random() * 0.1 + 25)));
        target.dispatchEvent(new KeyEvent(target, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, code, ' ', KeyEvent.KEY_LOCATION_STANDARD));
    }


    public synchronized void pressEnter() {
        typeKey('\n', false);
    }
}